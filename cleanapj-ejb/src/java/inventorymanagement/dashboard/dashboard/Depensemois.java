package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Depensemois extends MappedInteger {
    int annee;
    String mois_numero;
    String mois_nom;
    double depenses_mensuelles;

    public Depensemois() {
        setNomTable("vue_depenses_mois");
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getMois_numero() {
        return mois_numero;
    }

    public void setMois_numero(String mois_numero) {
        this.mois_numero = mois_numero;
    }

    public String getMois_nom() {
        return mois_nom;
    }

    public void setMois_nom(String mois_nom) {
        this.mois_nom = mois_nom;
    }

    public double getDepenses_mensuelles() {
        return depenses_mensuelles;
    }

    public void setDepenses_mensuelles(double depenses_mensuelles) {
        this.depenses_mensuelles = depenses_mensuelles;
    }
}
