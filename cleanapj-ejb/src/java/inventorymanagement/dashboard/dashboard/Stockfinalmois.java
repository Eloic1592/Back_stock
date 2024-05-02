package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Stockfinalmois extends MappedInteger {
    int annee;
    int mois;
    String nom_mois;
    double stockfinal;
    double prixfinal;

    public Stockfinalmois() {
        setNomTable("vue_stock_final_fin_mois");
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

    public double getStockfinal() {
        return stockfinal;
    }

    public void setStockfinal(double stockfinal) {
        this.stockfinal = stockfinal;
    }

    public double getPrixfinal() {
        return prixfinal;
    }

    public void setPrixfinal(double prixfinal) {
        this.prixfinal = prixfinal;
    }

    public String getNom_mois() {
        return nom_mois;
    }

    public void setNom_mois(String nom_mois) {
        this.nom_mois = nom_mois;
    }
}
