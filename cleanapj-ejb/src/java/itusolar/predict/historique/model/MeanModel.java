package itusolar.predict.historique.model;

import itusolar.historique.HistEnergyCalc;
import itusolar.predict.historique.HistFinalPred;
import itusolar.predict.historique.HistMean;
import itusolar.predict.historique.classificater.HistoriqueClassificatorSignature;
import itusolar.lieu.Section;

import java.util.Vector;

public class MeanModel implements HistoriqueModel{

    HistoriqueClassificatorSignature classificator;
    HistoriqueFilterSignature historiqueFilter;

    public MeanModel(HistoriqueClassificatorSignature classificator, HistoriqueFilterSignature historiqueFilter) {
        this.classificator = classificator;
        this.historiqueFilter = historiqueFilter;
    }

    @Override
    public HistFinalPred[] fit(HistEnergyCalc[] historiques, Section[] lieux) {
        HistMean[] histMeans = this.predictWithDate(historiques);
        HistFinalPred[] histFinalPred = this.predFinal(histMeans);
        return this.leftJoin(histFinalPred,lieux);
    }

    public HistFinalPred[] predFinal(HistMean[] hist) {
        HistMean[][] histGrouped = this.group(hist);
        HistFinalPred[] result = new HistFinalPred[histGrouped.length];
        int index = 0;
        for ( HistMean[] histG : histGrouped) {
            result[index] = this.mean(histG);
            index++;
        }
        return result;
    }
    public HistFinalPred mean(HistMean[] hists) {
        HistFinalPred result = new HistFinalPred();
        double predict = 0.;
        for (HistMean hist : hists) {
            predict += hist.getYprediction();
        }
        predict = predict / hists.length;
        result.setYprediction(predict);
        if (hists.length > 0) {
            result.setLieuid(hists[0].getLieuid());
            result.setHeure(hists[0].getHeure());
            result.setSection(hists[0].getSection());
        }
        return result;
    }
    public HistMean[][] group(HistMean[] hists) {
        System.out.println("Mean model, hists: " + hists.length);
        Vector<Vector<HistMean>> temps = new Vector<>();
        int temoin = 0;
        for(HistMean hist : hists) {
            temoin = 0;
            for (Vector<HistMean> temp : temps) {
                boolean verif = temp.get(0).getHeure() == hist.getHeure();
                verif &= temp.get(0).getSection() == hist.getSection();
                if ( verif ) {
                    temp.add(hist);
                    temoin = 1;
                    break;
                }
            }
            if (temoin == 0) {
                Vector<HistMean> newHist = new Vector<>();
                newHist.add(hist);
                temps.add(newHist);
            }
        }
        HistMean[][] result = new HistMean[temps.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = new HistMean[temps.get(i).size()];
            temps.get(i).copyInto(result[i]);
        }
        return result;
    }

    public HistFinalPred[] leftJoin(HistFinalPred[] hist, Section[] sections)  {
        int hour = 24;
        int index = 0;
        HistFinalPred[] result = new HistFinalPred[hour * sections.length];
        for (Section lieu : sections) {
            for (int i = 0; i < hour; i++) {
                HistFinalPred pred = null;
                try {
                    pred = (HistFinalPred) this.historiqueFilter.filter(hist, i, lieu.getId());
                } catch (Exception e) {
                    pred = new HistFinalPred();
                    pred.setHeure(i);
                    pred.setSection(lieu.getId());
                    pred.setYprediction(0.);
                }
                result[index] = pred;
                index++;
            }
        }
        return result;
    }

    public HistMean[] predictWithDate(HistEnergyCalc[] historiques) {
        this.rapport(historiques);
//        System.out.println(this.classificator);
        return this.predictWithDate(this.classificator.classify(historiques));
    }

    public HistMean[] predictWithDate(HistEnergyCalc[][] historiques) {
        HistMean[] result = new HistMean[historiques.length];
        for (int i = 0; i < historiques.length; i++) {
            result[i] = this.mean(historiques[i]);
        }
        return result;
    }

    public HistMean mean(HistEnergyCalc[] historiques) {
        double predict = 0.;
        for (HistEnergyCalc hist : historiques) {
            predict += hist.getPredict();
        }
        predict = predict / ((double)historiques.length);
        HistMean result = new HistMean();
        result.setYprediction(predict);
        if (historiques.length > 0) {
            result.setDate(historiques[0].createDate());
            result.setSection(historiques[0].getSection());
            result.setHeure(historiques[0].getHr());
        }
        return result;
    }

    public void rapport(HistEnergyCalc[] historiques) {
        for (HistEnergyCalc hist:historiques) {
            hist.rapport();
        }
    }
    public HistoriqueClassificatorSignature getClassificator() {
        return classificator;
    }

    public void setClassificator(HistoriqueClassificatorSignature classificator) {
        this.classificator = classificator;
    }
}
