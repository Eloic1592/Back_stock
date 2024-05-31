package inventorymanagement.depot;

import itusolar.prepare.MappedInteger;

public class Stockarticledepot extends MappedInteger {

    double quantite;
    String iddepot;
    String depot;
    String val;

    public Stockarticledepot() {
        setNomTable("stock_article_depot");
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
