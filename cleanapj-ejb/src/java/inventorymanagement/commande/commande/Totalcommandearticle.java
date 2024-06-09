package inventorymanagement.commande.commande;

import itusolar.prepare.MappedInteger;

public class Totalcommandearticle extends MappedInteger {
        int annee;
        int mois;
        String moisnom;
        String idarticle;
        String marque;
        String modele;
        String typemateriel;
        String codearticle;
        String val;
        double quantitetotale;
        int index;

    public Totalcommandearticle() {
        setNomTable("total_commande_article");
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

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public double getQuantitetotale() {
        return quantitetotale;
    }

    public void setQuantitetotale(double quantitetotale) {
        this.quantitetotale = quantitetotale;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
