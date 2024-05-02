package inventorymanagement.historique;

import inventorymanagement.facture.facture.Facturemateriel;
import inventorymanagement.mouvement.mouvementstock.Mouvementstock;

import java.sql.Timestamp;

public class Historique  {
    Mouvementstock[] stock;
    Facturemateriel[] materiel;

    public Historique() {

    }

    public Mouvementstock[] getStock() {
        return stock;
    }

    public void setStock(Mouvementstock[] stock) {
        this.stock = stock;
    }

    public Facturemateriel[] getMateriel() {
        return materiel;
    }

    public void setMateriel(Facturemateriel[] materiel) {
        this.materiel = materiel;
    }
}
