package inventorymanagement.facture.facture;


import inventorymanagement.facture.detailfacture.Detailsfacturemateriel;

import java.sql.Connection;

public interface FacturematerielManagerSignature {
   public  Detailsfacturemateriel[] detailsfacture(Connection connection)throws Exception;
    public Facturemateriel facture(Facturemateriel facture, Connection connection) throws  Exception;
    public void create(Facturemateriel facture, Connection connection) throws  Exception;
    public void create(Detailsfacturemateriel[] detailFactures, Connection connection) throws  Exception;
    public  Facturemateriel[] getall(Connection connection)throws Exception;
    public  FacturePageList contentlist(Connection connection)throws Exception;

}
