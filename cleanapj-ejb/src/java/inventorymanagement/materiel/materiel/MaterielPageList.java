package inventorymanagement.materiel.materiel;

import inventorymanagement.materiel.categoriemateriel.Categoriemateriel;
import inventorymanagement.materiel.typemateriel.Typemateriel;
import inventorymanagement.article.Article;

public class MaterielPageList {
    Materiel[] materiels;
    Article[] articles;
    Typemateriel[] typemateriels;
    Categoriemateriel [] categoriemateriels;
    Listemateriel[] listemateriels;
    Stockmateriel [] stockmateriels;
    Stocktypemateriel [] stocktypemateriels;
    UtilisationMateriel [] utilisationMateriels;
    Listemateriel listemateriel;
    double countmateriel;
    double countmateriellibre;
    double countmaterieloccupe;


    public MaterielPageList() {
    }

    public Materiel[] getMateriels() {
        return materiels;
    }

    public void setMateriels(Materiel[] materiels) {
        this.materiels = materiels;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public Typemateriel[] getTypemateriels() {
        return typemateriels;
    }

    public void setTypemateriels(Typemateriel[] typemateriels) {
        this.typemateriels = typemateriels;
    }

    public Categoriemateriel[] getCategoriemateriels() {
        return categoriemateriels;
    }

    public void setCategoriemateriels(Categoriemateriel[] categoriemateriels) {
        this.categoriemateriels = categoriemateriels;
    }

    public Listemateriel[] getListemateriels() {
        return listemateriels;
    }

    public void setListemateriels(Listemateriel[] listemateriels) {
        this.listemateriels = listemateriels;
    }

    public Stockmateriel[] getStockmateriels() {
        return stockmateriels;
    }

    public void setStockmateriels(Stockmateriel[] stockmateriels) {
        this.stockmateriels = stockmateriels;
    }

    public UtilisationMateriel[] getUtilisationMateriels() {
        return utilisationMateriels;
    }

    public void setUtilisationMateriels(UtilisationMateriel[] utilisationMateriels) {
        this.utilisationMateriels = utilisationMateriels;
    }

    public Listemateriel getListemateriel() {
        return listemateriel;
    }

    public void setListemateriel(Listemateriel listemateriel) {
        this.listemateriel = listemateriel;
    }

    public double getCountmateriel() {
        return countmateriel;
    }

    public void setCountmateriel(double countmateriel) {
        this.countmateriel = countmateriel;
    }

    public double getCountmateriellibre() {
        return countmateriellibre;
    }

    public void setCountmateriellibre(double countmateriellibre) {
        this.countmateriellibre = countmateriellibre;
    }

    public double getCountmaterieloccupe() {
        return countmaterieloccupe;
    }

    public void setCountmaterieloccupe(double countmaterieloccupe) {
        this.countmaterieloccupe = countmaterieloccupe;
    }

    public Stocktypemateriel[] getStocktypemateriels() {
        return stocktypemateriels;
    }

    public void setStocktypemateriels(Stocktypemateriel[] stocktypemateriels) {
        this.stocktypemateriels = stocktypemateriels;
    }
}
