package inventorymanagement.dashboard.dashboard;

public class Dashboard {
    FIFO [] fifos;
    LIFO [] lifos;
    Rotationstock [] rotationstocks;
    double rotation_stock;
    double valeur_rotation_stock;

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

}
