package inventorymanagement.article;

import itusolar.prepare.MappedInteger;

public class Stockrupturearticle extends MappedInteger {
    String idarticle;
    String marque;
    String modele;
    String description;
    String codearticle;
    double taux_rupture_stock;

    public Stockrupturearticle() {
        setNomTable("taux_rupture_article");
    }


    @Override
    public String getTuppleID() {
        return idarticle;
    }

    @Override
    public String getAttributIDName() {
        return "idarticle";
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public double getTaux_rupture_stock() {
        return taux_rupture_stock;
    }

    public void setTaux_rupture_stock(double taux_rupture_stock) {
        this.taux_rupture_stock = taux_rupture_stock;
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

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }
}
