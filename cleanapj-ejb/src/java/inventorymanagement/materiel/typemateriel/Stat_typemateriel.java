package inventorymanagement.materiel.typemateriel;

import itusolar.prepare.MappedInteger;

public class Stat_typemateriel extends MappedInteger {
    int annee;
    int mois_numero;
    String mois;
    String idtypemateriel;
    String typemateriel;
    String naturemouvement;
    String idnaturemouvement;
    double depense;
    double gain;

    public Stat_typemateriel() {
        setNomTable("stat_typemateriel");
    }

    public int getMois_numero() {
        return mois_numero;
    }

    public void setMois_numero(int mois_numero) {
        this.mois_numero = mois_numero;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public double getDepense() {
        return depense;
    }

    public void setDepense(double depense) {
        this.depense = depense;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }
}
