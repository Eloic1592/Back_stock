package inventorymanagement.article;

import itusolar.prepare.MappedInteger;

public class Listearticle extends MappedInteger {
    String idarticle;
    String idtypemateriel;
    String typemateriel;
    String marque;
    String modele;
    String codearticle;
    String description;
    double prix;
    double quantitestock;
    String val;
    double stocksecurite;

    public Listearticle() {
        super.setNomTable("liste_article");
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

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        if(marque==null || marque==""){
        this.marque="Aucune marque specifiee";
        }
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        if(modele==null || modele==""){
            this.modele="Aucun modele specifie";
        }
        this.modele = modele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description==null || description==""){
            this.description="Aucune description specifiee";
        }
        this.description = description;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getQuantitestock() {
        return quantitestock;
    }

    public void setQuantitestock(double quantitestock) {
        this.quantitestock = quantitestock;
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

    public double getStocksecurite() {
        return stocksecurite;
    }

    public void setStocksecurite(double stocksecurite) {
        this.stocksecurite = stocksecurite;
    }
}
