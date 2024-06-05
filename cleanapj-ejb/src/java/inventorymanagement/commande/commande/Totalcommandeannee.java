package inventorymanagement.commande.commande;

import itusolar.prepare.MappedInteger;

public class Totalcommandeannee extends MappedInteger {
    int annee;
    int mois;
    String moisnom;
    double totalcommandes;


    public Totalcommandeannee() {
        setNomTable("total_commande_annee");
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

    public String getMoisnom() {
        return moisnom;
    }

    public void setMoisnom(String moisnom) {
        this.moisnom = moisnom;
    }

    public double getTotalcommandes() {
        return totalcommandes;
    }

    public void setTotalcommandes(double totalcommandes) {
        this.totalcommandes = totalcommandes;
    }
}
