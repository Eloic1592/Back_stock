package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Quantitevendumois extends MappedInteger {
    int annee;
    int mois;
    double quantitevendue;
    double prixtotalvendus;

    public Quantitevendumois() {
        setNomTable("quantite_vendu_Mois");
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getQuantitevendue() {
        return quantitevendue;
    }

    public void setQuantitevendue(double quantitevendue) {
        this.quantitevendue = quantitevendue;
    }

    public double getPrixtotalvendus() {
        return prixtotalvendus;
    }

    public void setPrixtotalvendus(double prixtotalvendus) {
        this.prixtotalvendus = prixtotalvendus;
    }
}
