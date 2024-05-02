package inventorymanagement.facture.facture;

import bean.CGenUtil;
import inventorymanagement.facture.detailfacture.Detailsfacturemateriel;
import inventorymanagement.mouvement.mouvementstock.Mouvementstock;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.util.Arrays;

public class FactureMaterielManager extends HServiceManager implements FacturematerielManagerSignature {

    @Override
    public void create(Detailsfacturemateriel[] detailFactures, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Detailsfacturemateriel detailFacture :detailFactures) {
            if (detailFacture.getIddetailsfacturemateriel() != null) {
                detailFacture.updateToTable(connection);
            }
            detailFacture.construirePK(connection);
            CGenUtil.save(detailFacture, connection);
        }
    }

    @Override
    public Facturemateriel[] getall(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Facturemateriel(), new String[0], new String[0], connection, "");
        return castfacture(data);
    }


    @Override
    public void create(Facturemateriel facture, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        if (facture.getIdfacturemateriel()!=null) {
            facture.updateToTable(connection);
            return;
        }
        facture.construirePK(connection);
        CGenUtil.save(facture,connection);
        Arrays.stream(facture.getDetailFactures()).forEach(item -> item.setIdfacturemateriel(facture.getIdfacturemateriel()));
        this.create(facture.getDetailFactures(),connection);
    }

    @Override
    public Facturemateriel facture(Facturemateriel facture, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Facturemateriel(), new String[0], new String[0], connection, "");
         Facturemateriel []facturemateriels=castfacture(data);
         return(facturemateriels[0]);
    }

    public Facturemateriel[] castfacture(Object[] datas) {
        Facturemateriel[] facturemateriels = new Facturemateriel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            facturemateriels[i] = (Facturemateriel) datas[i];
        }
        return facturemateriels;
    }

    public Detailsfacturemateriel[] cast(Object[] datas) {
        Detailsfacturemateriel[] detailsfacturemateriels = new Detailsfacturemateriel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            detailsfacturemateriels[i] = (Detailsfacturemateriel) datas[i];
        }
        return detailsfacturemateriels;
    }


    @Override
    public Detailsfacturemateriel[] detailsfacture(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Detailsfacturemateriel(), new String[0], new String[0], connection, "");
        return cast(data);
    }



    @Override
    public FacturePageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        FacturePageList facturePageList= new FacturePageList();
        facturePageList.setFacturemateriels(this.getall(connection));
        return facturePageList;
    }

}
