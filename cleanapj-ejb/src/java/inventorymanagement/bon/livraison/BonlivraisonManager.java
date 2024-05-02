package inventorymanagement.bon.livraison;

import bean.CGenUtil;
import inventorymanagement.devis.devis.Clientdevis;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class BonlivraisonManager extends HServiceManager implements BonlivraisonManagerSignature {
    @Override
    public Clientlivraison[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Clientlivraison(), new String[0], new String[0], connection, "");
        return cast(data);
    }
    public Clientlivraison[] cast(Object[] datas) {
        Clientlivraison[] clientlivraisons = new Clientlivraison[datas.length];
        for (int i = 0; i < datas.length; i++) {
            clientlivraisons[i] = (Clientlivraison) datas[i];
        }
        return clientlivraisons;
    }

    @Override
    public void createlivraison(Bonlivraison[] bonlivraisons, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Bonlivraison bonlivraison:bonlivraisons){
            if(bonlivraison.idbonlivraison!=null){
                bonlivraison.updateToTable(connection);
            }
            bonlivraison.construirePK(connection);
            CGenUtil.save(bonlivraison);
        }
    }

    public Clientlivraison[] getsinglebon(String idproforma, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Clientlivraison(), new String[0], new String[0], connection, "AND idproforma='"+idproforma+"'");
        return castlivraison(data);
    }

    public Clientlivraison[] castlivraison(Object[] datas) {
        Clientlivraison[] clientlivraisons = new Clientlivraison[datas.length];
        for (int i = 0; i < datas.length; i++) {
            clientlivraisons[i] = (Clientlivraison) datas[i];
        }
        return clientlivraisons;
    }



}
