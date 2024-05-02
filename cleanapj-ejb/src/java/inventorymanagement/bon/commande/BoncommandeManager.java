package inventorymanagement.bon.commande;

import bean.CGenUtil;
import inventorymanagement.bon.livraison.BonlivraisonManager;
import inventorymanagement.devis.proforma.DetailProforma;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class BoncommandeManager extends HServiceManager implements  BoncommandeSignature {

    CommandeManagerSignature commandeManagerSignature;
    BonlivraisonManager bonlivraisonManager=new BonlivraisonManager();

    public BoncommandeManager() {
        this.commandeManagerSignature = commandeManagerSignature;
    }


    @Override
    public Clientboncommande[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data =CGenUtil.rechercher(new Clientboncommande(), new String[0], new String[0], connection,"");
        return cast(data);
    }


    public DetailProforma[] getdetail(String idproforma, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data =CGenUtil.rechercher(new DetailProforma(), new String[0], new String[0], connection,"AND idproforma='"+idproforma+"'");
        return castdetail(data);
    }

    public Clientboncommande[] cast(Object[] datas) {
        Clientboncommande[] clientcommandes = new Clientboncommande[datas.length];
        for (int i = 0; i < datas.length; i++) {
            clientcommandes[i] = (Clientboncommande) datas[i];
        }
        return clientcommandes;
    }

    public DetailProforma[] castdetail(Object[] datas) {
        DetailProforma[] detailProformas = new DetailProforma[datas.length];
        for (int i = 0; i < datas.length; i++) {
            detailProformas[i] = (DetailProforma) datas[i];
        }
        return detailProformas;
    }

    @Override
    public void createcommande(Boncommande [] boncommandes,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Boncommande boncommande: boncommandes){
            if(boncommande.getIdboncommande()!=null) {
                boncommande.updateToTable(connection);
            }
            boncommande.construirePK(connection);
            CGenUtil.save(boncommande);
        }
    }

    @Override
    public CommandepageList contentlist(String idproforma,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandepageList commandepageList=new CommandepageList();
        commandepageList.setDetailProformas(this.getdetail(idproforma,connection));
        commandepageList.setSomme(commandepageList.sommeproforma());
        commandepageList.setClientlivraisons(bonlivraisonManager.getsinglebon(idproforma,connection));
        return commandepageList;
    }
}
