package inventorymanagement.article;

public class Reapprovisionnement extends Stockarticle {
    String idarticle;
    String marque;
    String modele;
    String codearticle;
    double quantitestock;
    double stocksecurite;
    String idtypemateriel;
    String val;
    String typemateriel;
    double stockrecharge;

    public Reapprovisionnement() {
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

    public double getQuantitestock() {
        return quantitestock;
    }

    public void setQuantitestock(double quantitestock) {
        this.quantitestock = quantitestock;
    }

    public double getStocksecurite() {
        return stocksecurite;
    }

    public void setStocksecurite(double stocksecurite) {
        this.stocksecurite = stocksecurite;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public double getStockrecharge() {
        return stockrecharge;
    }

    public void setStockrecharge(double stockrecharge) {
        this.stockrecharge = stockrecharge;
    }
}
