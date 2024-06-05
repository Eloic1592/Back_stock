package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Etatdetailstockannee extends MappedInteger {
    String idtypemateriel;
    String typemateriel;
    String val;
    int annee;
    int mois;
    String moisnom;
    double quantitetotale;
    double articleabime;
    double totalprixabime;
    double articlebonetat;
    double totalprixbonetat;

    public Etatdetailstockannee() {
        setNomTable("etat_detailstock_annee");
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
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

    public double getTotalprixabime() {
        return totalprixabime;
    }

    public void setTotalprixabime(double totalprixabime) {
        this.totalprixabime = totalprixabime;
    }

    public double getArticlebonetat() {
        return articlebonetat;
    }

    public void setArticlebonetat(double articlebonetat) {
        this.articlebonetat = articlebonetat;
    }

    public double getTotalprixbonetat() {
        return totalprixbonetat;
    }

    public void setTotalprixbonetat(double totalprixbonetat) {
        this.totalprixbonetat = totalprixbonetat;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
