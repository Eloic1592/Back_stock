package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class LIFO extends MappedInteger {
    String idarticle;
    String marque;
    String modele;
    String description;
    double cout_vendu;
    double annee;
    double mois;

    public LIFO() {
        setNomTable("V_LIFO");
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCout_vendu() {
        return cout_vendu;
    }

    public void setCout_vendu(double cout_vendu) {
        this.cout_vendu = cout_vendu;
    }
    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public double getMois() {
        return mois;
    }

    public void setMois(double mois) {
        this.mois = mois;
    }
}
