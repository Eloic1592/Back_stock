package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Stockinitialmois extends MappedInteger {
    int annee;
    int mois;
    String nom_mois;
    double stockinitial;
    double prixinitial;

    public Stockinitialmois() {
        setNomTable("vue_stock_initial_debut_mois");
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

    public double getStockinitial() {
        return stockinitial;
    }

    public void setStockinitial(double stockinitial) {
        this.stockinitial = stockinitial;
    }

    public double getPrixinitial() {
        return prixinitial;
    }

    public void setPrixinitial(double prixinitial) {
        this.prixinitial = prixinitial;
    }

    public String getNom_mois() {
        return nom_mois;
    }

    public void setNom_mois(String nom_mois) {
        this.nom_mois = nom_mois;
    }
}
