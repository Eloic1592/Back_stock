package inventorymanagement.mouvement.mouvementphysique;

import itusolar.prepare.MappedInteger;

public class totalarticleentree extends MappedInteger {
    double total;
    int mois;
    int annee;
    String nom_mois;


    public totalarticleentree() {
        setNomTable("total_article_entree");
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getNom_mois() {
        return nom_mois;
    }

    public void setNom_mois(String nom_mois) {
        this.nom_mois = nom_mois;
    }
}
