package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Etatstockannee extends MappedInteger {
    int annee;
    int mois;
    String moisnom;
    double quantitetotale;
    double articleabime;
    double totalprixbonetat;
    double articlebonetat;
    double totalprixabime;

    public Etatstockannee() {
        setNomTable("etat_stock_annee");
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

    public double getQuantitetotale() {
        return quantitetotale;
    }

    public void setQuantitetotale(double quantitetotale) {
        this.quantitetotale = quantitetotale;
    }

    public double getArticleabime() {
        return articleabime;
    }

    public void setArticleabime(double articleabime) {
        this.articleabime = articleabime;
    }

    public double getTotalprixbonetat() {
        return totalprixbonetat;
    }

    public void setTotalprixbonetat(double totalprixbonetat) {
        this.totalprixbonetat = totalprixbonetat;
    }

    public double getArticlebonetat() {
        return articlebonetat;
    }

    public void setArticlebonetat(double articlebonetat) {
        this.articlebonetat = articlebonetat;
    }

    public double getTotalprixabime() {
        return totalprixabime;
    }

    public void setTotalprixabime(double totalprixabime) {
        this.totalprixabime = totalprixabime;
    }
}
