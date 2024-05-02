package itusolar.predict.etatconsommation;

import itusolar.predict.historique.HistMean;
import itusolar.predict.historique.classificater.HistoriqueClassificatorSignature;
import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;
import itusolar.simulation.material.Material;
import itusolar.simulation.material.MaterialComposition;
import itusolar.simulation.material.NegativeConsumptionException;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

public class ConsumptionStateEvaluator implements ConsumptionStateEvaluatorSignature {
    HistoriqueClassificatorSignature storyClassifier;

    public ConsumptionStateEvaluator(HistoriqueClassificatorSignature storyClassifier) {
        this.storyClassifier = storyClassifier;
    }

    @Override
    public void consume(SimulPred pred, HistMean[] histMeans) {
        pred.setEtatConsommations(this.consume(pred.getSolaires(), histMeans));
    }

    public ConsumptionState[][] consume(SimulData[][] consumptions, HistMean[] productions) {
        ConsumptionState[][] result = new ConsumptionState[consumptions.length][];
        int index = 0;
        for (SimulData[] consummation : consumptions) {
            result[index] = this.consume(consummation,productions);
            index++;
        }
        return result;
    }

    public ConsumptionState[] consume(SimulData[] consumptions,HistMean[] productions) {
        Vector<ConsumptionState> stockTemp = new Vector<>();
        itusolar.predict.historique.HistMean[] productConcern = new itusolar.predict.historique.HistMean[0];
        if (consumptions != null && consumptions.length > 0) { // pour eviter l'exceptions au cas où l'on reçoi un tableau de consommation null ou vide
            productConcern = this.storyClassifier.filterBySection(productions,consumptions[0].getSection());
        }
        for (int i = 0; i < productConcern.length; i++) {
            stockTemp.addAll(this.consume(productConcern[i],consumptions));
            if (i+1 < productConcern.length) {
                productConcern[i+1].setReste(productConcern[i].getReste()); // ajouter le reste du dernier production au nouveau
            }
        }
        ConsumptionState[] result = new ConsumptionState[stockTemp.size()];
        stockTemp.copyInto(result);
        return result;
    }

    public Vector<ConsumptionState> consume(HistMean histMean, SimulData[] consumptions) {
        Vector<ConsumptionState> result = new Vector<>();
        SimulData[] consoConcern = this.filter(histMean,consumptions);
        if (consoConcern.length == 0) {
            this.consumeNone(histMean, result);
            return result;
        }
        for (SimulData simulData : consoConcern) {
            this.consume(histMean,simulData,result);
        }
        return result;
    }

    public void consumeNone(HistMean histMean, Vector<ConsumptionState> result) {
        double rest = histMean.getReste()+histMean.getPuissanceentree();
        SimulData data = new SimulData();
        Material material = new Material();
        material.setConsommation(histMean.getPuissanceentree());
        data.setAppareil(material);
        data.setNb(1);
        long start = histMean.getDate().getTime();
        long oneHour = 3600000L;
        start += (histMean.getHeure()* oneHour);
        data.setDebut(new Timestamp(start));
        data.setFin(new Timestamp(start+oneHour));
        try {
            histMean.configReste(histMean, this, data, rest);
        } catch (ExcesException e) {
            result.add(e.getExces());
        } catch (NegativeConsumptionException e) {
            throw new RuntimeException(e);
        }
    }

    public void consume(HistMean hist, SimulData data, Vector<ConsumptionState> result) {
        data.evalConsommation(-1);
        try {
            this.consume(hist,data);
        } catch (ExcesException e) {
            result.add(e.getExces());
        } catch (NegativeConsumptionException e) {
            System.out.println(e.getMessage());
            this.consommerPlus(hist,data,result);
        }
    }

    public void consommerPlus(HistMean hist,SimulData data,Vector<ConsumptionState> result) {
        try {
            this.onFinNormal(hist,data,data.getVitesse()-hist.getPuissanceentree());
        } catch (ExcesException e) {
            result.add(e.getExces());
        } catch (NegativeConsumptionException e) {
//            System.out.println(e.getMessage());
            long fin = this.estimeFin(hist,data,hist.getReste()); // calcul de la date d'expiration totale du reste d'energie
            this.onFinBefore(hist,data, result, fin);
        }
    }


    public void onFinBefore(HistMean histMean,SimulData data, Vector<ConsumptionState> result, long fin) {
        histMean.setReste(0.);
        SimulData[] attachments = new SimulData[] {data};
        if (data.getAttachments() != null && data.getAttachments().length > 0) {
            attachments = data.getAttachments();
        }
        SimulData temp = this.loadConsumptionState(histMean, data, attachments, result);
        if (!(temp.getAttachments() != null && temp.getAttachments().length > 0)) {
            Material appareil = new Material();
            appareil.setConsommation(0);
            temp.setAppareil(appareil);
            temp.setNb(0);
        }
        Timestamp tDebut = new Timestamp(fin);
        Timestamp tFin = new Timestamp(data.getFin().getTime());
        temp.setDebut(tDebut);
        temp.setFin(tFin);
        ConsumptionState consumptionState= result.lastElement();
        consumptionState.setDebut(tDebut);
        consumptionState.setFin(tFin);
        consumptionState.setSupport(histMean.getPuissanceentree());
        consumptionState.setConso(data.getVitesse());
        this.consume(histMean,temp, result);
    }

    public SimulData loadConsumptionState(HistMean histMean,SimulData data,SimulData[] attachments, Vector<ConsumptionState> result) {
        SimulData[][] states = this.minimum(histMean,attachments, histMean.getPuissanceentree());
        ConsumptionState consumptionState = new Deficit(states[1]);
        consumptionState.setSection(histMean.getSection());
        result.add(consumptionState);
        SimulData temp = new SimulData(data);
        temp.setAttachments(states[0]);
        return temp;
    }

    public SimulData[][] minimum(HistMean hist,SimulData[] attachments, double maxConso) {
        // Declaration
        Vector<SimulData> supported = new Vector<>(),notSupported = new Vector<>();
        SimulData temp = null, nTemp = null;
        int maxApp = 0;
        double nbConsoPossible = 0.;
        int nbNotSup = 0;
        // extraction de tous les attachments pour trouver la composition d'appareil qui donne le minimum de perte
        attachments = this.extract(attachments);
        Arrays.sort(attachments, (o1, o2) -> {
            int compare = -1;
            double vitesse1 = o1.getAppareil().getConsommation();
            double vitesse2 = o2.getAppareil().getConsommation();
            if ((compare =Double.compare(vitesse2 , vitesse1)) == 0) {
                return Double.compare(o2.getNb(),o1.getNb());
            }
            return compare;
        });
        for (SimulData attachment : attachments) {
            nbConsoPossible = (maxConso / attachment.getAppVitesse());
            maxApp = Math.min(((int) nbConsoPossible), (int) attachment.getNb());
            maxConso -= (attachment.getAppVitesse() * ((double)maxApp));
            if (((int) nbConsoPossible) > 0)
                hist.toSimulData(attachment, maxApp, supported);
            if ((nbNotSup = ((int)attachment.getNb()-maxApp)) > 0)
                hist.toSimulData(attachment,nbNotSup , notSupported);
        }
        SimulData[][] result = new SimulData[2][];
        result[0] = this.extract(supported);
        result[1] = this.extract(notSupported);
        return result;
    }
    public void onFinNormal(HistMean hist,SimulData data, double vitesse) throws ExcesException, NegativeConsumptionException {
        long diff = data.getFin().getTime()-data.getDebut().getTime();
        double duration = ((double)diff) /60000.; // calcul le nombre de minute entre le debut et la fin
        double consumption = vitesse * duration; // energie consommée entre le debut et la fin
        consumption /= 60.;
        consumption = hist.getReste() - consumption;
        hist.configReste(hist,this,data,consumption);
    }

    public long estimeFin(HistMean hist,SimulData data, double conso) {
        return this.estimeFin(hist,data,conso,false);
    }

    public long estimeFin(HistMean hist,SimulData data, double conso,boolean absolute) {
        double vitesse = data.getVitesse()-hist.getPuissanceentree();
        if (absolute && vitesse < 0.)
            vitesse *= (-1);
        vitesse /= 60.;
        double duration = conso / vitesse; // calcul la durrée en minute pour consommer totalement le reste d'energie avec la vitesse de consommation
        long durMinus = ((long)duration)*60000; // convertion en millisecond
        return data.getDebut().getTime() + durMinus;
    }

    public void consume(HistMean hist,SimulData data) throws ExcesException, NegativeConsumptionException {
        Material appareil = new Material();
        appareil.configConsommation(hist.getPuissanceentree()-data.getVitesse());
        SimulData temp = new SimulData();
        temp.setDebut(data.getDebut());
        temp.setFin(data.getFin());
        temp.setAppareil(appareil);
        temp.setNb(1);
        temp.evalConsommation(-1);
        double prod = temp.getConsommation();
        prod += hist.getReste();
        hist.configReste(hist,this,data,prod);
    }

    public SimulData[] filter(HistMean histMean,SimulData[] datas) {
        Vector<SimulData> stockTemp = new Vector<>();
        for(SimulData data : datas) {
            boolean verif = this.sameDate(histMean.getDate(), new Date(data.getDebut().getTime()));
            if (verif && histMean.getHeure() == data.getHeur()) {
                stockTemp.add(data);
            }
        }
        return this.extract(stockTemp);
    }
    public boolean sameDate(Date first, Date second) {
        LocalDate fDate = first.toLocalDate();
        LocalDate fSecond = second.toLocalDate();
        return fDate.isEqual(fSecond);
    }

    public SimulData[] extract(Vector<SimulData> datas) {
        SimulData[] result = new SimulData[datas.size()];
        datas.copyInto(result);
        return result;
    }

    public SimulData[] extract(SimulData[] datas) {
        Vector<SimulData> stockValues = new Vector<>();
        for (SimulData data : datas) {
            this.extract(data, stockValues);
        }
        return this.extract(stockValues);
    }

    public void extract(SimulData data, Vector<SimulData> stockValues) {
        if (data.getAttachments() != null && data.getAttachments().length > 0) {
            for (SimulData attachement : data.getAttachments()) {
                this.extract(attachement, stockValues);
            }
        } else {
            this.finalExtract(data, "" , stockValues);
        }
    }

    public void finalExtract(SimulData data,String title, Vector<SimulData> stockValues) {
        if (data.getAppareil().isComposed()) {
            for (MaterialComposition composition : data.getAppareil().getMaterialCompositions()) {
                SimulData temp = new SimulData(data);
                temp.setNb(composition.getNombre()*data.getNb());
                temp.setAppareilidValue(composition.getAppareilid());
                Material material = new Material(composition.getMaterial());
//                material.setTitre(data.getAppareil().getTitre()+" "+material.getTitre());
                temp.setAppareil(material);
                this.finalExtract(temp, title +" "+data.getAppareil().getTitre(), stockValues);
            }
            return;
        }
        data.getAppareil().setTitre(title+" "+data.getAppareil().getTitre());
        stockValues.add(data);
    }

}
