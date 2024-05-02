package inventorymanagement.mouvement.naturemouvement;

import itusolar.prepare.MappedInteger;

public class Statnaturemouvement extends MappedInteger {
    int annee;
    int mois;
    String mois_nom;
    double gain;
    double depense;
    double benefice;
    String idnaturemouvement;
    String naturemouvement;

    public Statnaturemouvement() {
        setNomTable("stat_naturemouvement");
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

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getDepense() {
        return depense;
    }

    public void setDepense(double depense) {
        this.depense = depense;
    }

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
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
