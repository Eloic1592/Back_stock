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
    UtilisationMateriel [] utilisationMateriels;
    Listemateriel listemateriel;
    double count_materiel;



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

    public double getCount_materiel() {
        return count_materiel;
    }

    public void setCount_materiel(double count_materiel) {
        this.count_materiel = count_materiel;
    }
}
