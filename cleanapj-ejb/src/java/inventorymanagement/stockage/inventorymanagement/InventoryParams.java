package inventorymanagement.stockage.inventorymanagement;

public class InventoryParams {
    String idstockage;
    String iddistribution;
    String idinventaire;

    public InventoryParams() {
    }

    public String getIdstockage() {
        return idstockage;
    }

    public void setIdstockage(String idstockage) {
        this.idstockage = idstockage;
    }

    public String getIddistribution() {
        return iddistribution;
    }

    public void setIddistribution(String iddistribution) {
        this.iddistribution = iddistribution;
    }

    public String getIdinventaire() {
        return idinventaire;
    }

    public void setIdinventaire(String idinventaire) {
        this.idinventaire = idinventaire;
    }
}
