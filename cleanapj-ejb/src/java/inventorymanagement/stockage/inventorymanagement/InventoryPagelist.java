package inventorymanagement.stockage.inventorymanagement;

import inventorymanagement.article.Article;
import inventorymanagement.depot.Depot;
import inventorymanagement.stockage.distribution.Distribution;
import inventorymanagement.stockage.distribution.Vuedistribution;
import inventorymanagement.stockage.inventaire.Inventaire;
import inventorymanagement.stockage.inventaire.Vueinventaire;
import inventorymanagement.stockage.stockage.Stockage;
import inventorymanagement.stockage.stockage.Vuestockage;

public class InventoryPagelist {
    Vuedistribution[] vuedistributions;
    Vuestockage[] vuestockages;
    Vueinventaire[] vueinventaires;
    Article[] articles;
    Depot[] depots;
    Distribution distribution;
    Stockage stockage;
    Inventaire inventaire;

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

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public Stockage getStockage() {
        return stockage;
    }

    public void setStockage(Stockage stockage) {
        this.stockage = stockage;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public Depot[] getDepots() {
        return depots;
    }

    public void setDepots(Depot[] depots) {
        this.depots = depots;
    }
}
