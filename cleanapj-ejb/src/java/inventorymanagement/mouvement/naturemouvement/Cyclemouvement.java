package inventorymanagement.mouvement.naturemouvement;

import itusolar.prepare.MappedInteger;

public class Cyclemouvement extends MappedInteger {
    int annee;
    int mois;
    String mois_nom;
    double entree;
    double sortie;
    String idnaturemouvement;
    String naturemouvement;

    public Cyclemouvement() {
        setNomTable("cycle_mouvement");
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

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public String getMois_nom() {
        return mois_nom;
    }

    public void setMois_nom(String mois_nom) {
        this.mois_nom = mois_nom;
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }
}
