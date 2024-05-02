package inventorymanagement.devis.proforma;

import java.sql.Connection;

public interface ProformaManagerSignature {
    public void createproforma(Proforma [] proforma, Connection connection) throws Exception;
    public ClientProforma[] getallproforma(Connection connection) throws Exception;
    public void createsingleproforma(Proforma proforma, Connection connection) throws Exception;
}
