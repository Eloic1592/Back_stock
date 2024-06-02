package inventorymanagement.stockage.inventorymanagement;

import inventorymanagement.article.Article;
import inventorymanagement.stockage.distribution.Vuedistribution;
import inventorymanagement.stockage.inventaire.Vueinventaire;
import inventorymanagement.stockage.stockage.Vuestockage;

public class InventoryPagelist {
    Vuedistribution[] vuedistributions;
    Vuestockage[] vuestockages;
    Vueinventaire[] vueinventaires;
    Article[] articles;

    public InventoryPagelist() {
    }

    public Vuedistribution[] getVuedistributions() {
        return vuedistributions;
    }

    public void setVuedistributions(Vuedistribution[] vuedistributions) {
        this.vuedistributions = vuedistributions;
    }

    public Vuestockage[] getVuestockages() {
        return vuestockages;
    }

    public void setVuestockages(Vuestockage[] vuestockages) {
        this.vuestockages = vuestockages;
    }

    public Vueinventaire[] getVueinventaires() {
        return vueinventaires;
    }

    public void setVueinventaires(Vueinventaire[] vueinventaires) {
        this.vueinventaires = vueinventaires;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }
}
