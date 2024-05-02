package inventorymanagement.devis.proforma;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class ProformaManager extends HServiceManager implements  ProformaManagerSignature {


    @Override
    public void createproforma(Proforma []proforma, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Proforma proforma1 :proforma) {
            if (proforma1.getIdproforma() != null) {
                proforma1.updateToTable(connection);
            }
            proforma1.construirePK(connection);
            CGenUtil.save(proforma1, connection);
        }
    }

    @Override
    public void createsingleproforma(Proforma proforma, Connection connection) throws Exception {
        connection = this.getConnection(connection);
            if (proforma.getIdproforma() != null) {
                proforma.updateToTable(connection);
            }
            proforma.construirePK(connection);
            CGenUtil.save(proforma, connection);
    }

    @Override
    public ClientProforma[] getallproforma(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new ClientProforma(), new String[0], new String[0], connection, "");
        return castproforma(data);
    }


    public ClientProforma[] castproforma(Object[] datas) {
        ClientProforma[] clientProformas = new ClientProforma[datas.length];
        for (int i = 0; i < datas.length; i++) {
            clientProformas[i] = (ClientProforma) datas[i];
        }
        return clientProformas;
    }


}
