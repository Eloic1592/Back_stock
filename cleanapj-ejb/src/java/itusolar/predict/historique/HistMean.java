package itusolar.predict.historique;

import itusolar.predict.etatconsommation.*;
import itusolar.simulation.SimulData;
import itusolar.simulation.material.Material;
import itusolar.simulation.material.NegativeConsumptionException;
import itusolar.source.SectionCapacity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

public class HistMean extends HistFinalPred {

    Date date;
    double tempMean,puissanceentree,puissancesortie,reste,restePlus;

    SectionCapacity capacity;

    @Override
    public String toString() {
        return "HistMean{" +
                "date=" + date +
                ", tempMean=" + tempMean +
                ", puissanceentree=" + puissanceentree +
                ", puissancesortie=" + puissancesortie +
                ", reste=" + reste +
                ", restePlus=" + restePlus +
                ", heure=" + heure +
                ", lieuid=" + lieuid +
                ", section=" + section +
                ", yprediction=" + yprediction +
                '}';
    }

    public SectionCapacity getCapacity() {
        return capacity;
    }

    public void setCapacity(SectionCapacity capacity) {
        this.capacity = capacity;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public double getRestePlus() {
        return restePlus;
    }

    public void setRestePlus(double restePlus) {
        this.restePlus = restePlus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTempMean() {
        return tempMean;
    }

    public void setTempMean(double tempMean) {
        this.tempMean = tempMean;
    }

    public double getPuissanceentree() {
        return puissanceentree;
    }

    public void setPuissanceentree(double puissanceentree) {
        this.puissanceentree = puissanceentree;
    }

    public double getPuissancesortie() {
        return puissancesortie;
    }

    public void setPuissancesortie(double puissancesortie) {
        this.puissancesortie = puissancesortie;
    }

    public void configReste(HistMean hist, ConsumptionStateEvaluatorSignature consumptionState, SimulData data, double reste) throws ExcesException, NegativeConsumptionException {
        if (reste < 0.)
            throw new NegativeConsumptionException();
        if (reste <= this.getCapacity().getCapacite()) {
            this.setReste(reste);
        } else {
            data = new SimulData(data);
            double libre = this.getCapacity().getCapacite()-this.getReste(); // prend l'espace libre
            long fin = consumptionState.estimeFin(this,data,libre,true); // estime le temps de remplissage maximum
            this.setReste(this.getCapacity().getCapacite());
            ConsumptionState etatConsommation = new Excess();
            Timestamp debut = new Timestamp(fin);
            etatConsommation.setDebut(debut);
            etatConsommation.setFin(data.getFin());
            Material material = new Material();
            material.setConsommation(hist.getPuissanceentree());
            data.setAppareil(material);
//            data.getAppareil().setConsommation(hist.getPuissanceentree());
            data.setNb(1);
            data.setDebut(debut);
            data.evalConsommation(-1);
            System.out.println(data);
            System.out.println("ConfigReste : conso -> "+data.getConsommation());
            etatConsommation.setConso(data.getConsommation());
            ExcesException e = new ExcesException();
            e.setExces(etatConsommation);
            throw e;
        }
    }
    public void toSimulData(SimulData data, int nb, Vector<SimulData> stock) {
        SimulData result = new SimulData(data);
        result.setNb(nb);
        stock.add(result);
    }
}
