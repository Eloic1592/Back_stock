package inventorymanagement.article;

import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;

public class Stockarticleinventaire  extends MappedInteger {
    String idarticle;
    String marque;
    String modele;
    String codearticle;
    String typemateriel;
    double quantitereel;
    double articleabime;
    double articlebonetat;
    Timestamp dateinventaire;
    int statut;
    String etat;

    public Stockarticleinventaire() {
        setNomTable("stock_article_inventaire");
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

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }

    public double getArticleabime() {
        return articleabime;
    }

    public void setArticleabime(double articleabime) {
        this.articleabime = articleabime;
    }

    public double getArticlebonetat() {
        return articlebonetat;
    }

    public void setArticlebonetat(double articlebonetat) {
        this.articlebonetat = articlebonetat;
    }

    public Timestamp getDateinventaire() {
        return dateinventaire;
    }

    public void setDateinventaire(Timestamp dateinventaire) {
        this.dateinventaire = dateinventaire;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public double getQuantitereel() {
        return quantitereel;
    }

    public void setQuantitereel(double quantitereel) {
        this.quantitereel = quantitereel;
    }
}
