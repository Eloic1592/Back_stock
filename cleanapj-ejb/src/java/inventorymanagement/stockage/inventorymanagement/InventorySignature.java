package inventorymanagement.stockage.inventorymanagement;

import inventorymanagement.stockage.distribution.Distribution;
import inventorymanagement.stockage.distribution.Vuedistribution;
import inventorymanagement.stockage.inventaire.Inventaire;
import inventorymanagement.stockage.inventaire.Vueinventaire;
import inventorymanagement.stockage.stockage.Stockage;
import inventorymanagement.stockage.stockage.Vuestockage;

import java.sql.Connection;

public interface InventorySignature {
    public void createdistribution(Distribution distribution, Connection connection)throws Exception;
    public void createstockage(Stockage stockage, Connection connection)throws Exception;
    public void createinventaire (Inventaire inventaire, Connection connection)throws Exception;
    public Vuedistribution[] listdistribution(Connection connection)throws Exception;
    public Vuestockage[] liststockage(Connection connection)throws Exception;
    public Vueinventaire[] listinventaire(Connection connection)throws Exception;

}
