package inventorymanagement.dashboard.dashboard;

public class Dashboard {
    FIFO [] fifos;
    LIFO [] lifos;
    Rotationstock [] rotationstocks;
    double rotation_stock;
    double valeur_rotation_stock;
    Etatstockannee[] etatstockannee;
    Etatdetailstockannee [] etatdetailstockannee;
    double pourcentagebonetatstock;
    double pourcentageabimestock;
    Depensemois [] depensemois;

    public Dashboard() {
    }

    public FIFO[] getFifos() {
        return fifos;
    }

    public void setFifos(FIFO[] fifos) {
        this.fifos = fifos;
    }

    public LIFO[] getLifos() {
        return lifos;
    }

    public void setLifos(LIFO[] lifos) {
        this.lifos = lifos;
    }

    public Rotationstock[] getRotationstocks() {
        return rotationstocks;
    }

    public void setRotationstocks(Rotationstock[] rotationstocks) {
        this.rotationstocks = rotationstocks;
    }

    public double getRotation_stock() {
        return rotation_stock;
    }

    public void setRotation_stock(double rotation_stock) {
        this.rotation_stock = rotation_stock;
    }

    public double getValeur_rotation_stock() {
        return valeur_rotation_stock;
    }

    public void setValeur_rotation_stock(double valeur_rotation_stock) {
        this.valeur_rotation_stock = valeur_rotation_stock;
    }

    public double getPourcentagebonetatstock() {
        return pourcentagebonetatstock;
    }

    public void setPourcentagebonetatstock(double pourcentagebonetatstock) {
        this.pourcentagebonetatstock = pourcentagebonetatstock;
    }

    public double getPourcentageabimestock() {
        return pourcentageabimestock;
    }

    public void setPourcentageabimestock(double pourcentageabimestock) {
        this.pourcentageabimestock = pourcentageabimestock;
    }

    public Etatstockannee[] getEtatstockannee() {
        return etatstockannee;
    }

    public void setEtatstockannee(Etatstockannee[] etatstockannee) {
        this.etatstockannee = etatstockannee;
    }

    public Etatdetailstockannee[] getEtatdetailstockannee() {
        return etatdetailstockannee;
    }

    public void setEtatdetailstockannee(Etatdetailstockannee[] etatdetailstockannee) {
        this.etatdetailstockannee = etatdetailstockannee;
    }

    public Depensemois[] getDepensemois() {
        return depensemois;
    }

    public void setDepensemois(Depensemois[] depensemois) {
        this.depensemois = depensemois;
    }
}
