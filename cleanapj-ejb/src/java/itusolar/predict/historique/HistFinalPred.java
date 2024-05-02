package itusolar.predict.historique;

import itusolar.simulation.HourFilter;

public class HistFinalPred implements HourFilter {
    int heure,lieuid,section;
    double yprediction;

    @Override
    public String toString() {
        return "HistFinalPred{" +
                "heure=" + heure +
                ", lieuid=" + lieuid +
                ", section=" + section +
                ", yprediction=" + yprediction +
                '}';
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public double getYprediction() {
        return yprediction;
    }

    public void setYprediction(double yprediction) {
        this.yprediction = yprediction;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getLieuid() {
        return lieuid;
    }

    public void setLieuid(int lieuid) {
        this.lieuid = lieuid;
    }

    @Override
    public int heur() {
        return this.getHeure();
    }

    @Override
    public int section() {
        return this.getSection();
    }
}
