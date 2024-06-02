package inventorymanagement.commande.commande;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;
import inventorymanagement.client.ClientManager;
import inventorymanagement.commande.detailcommande.Detailcommande;
import inventorymanagement.commande.detailcommande.Detailcommandeview;
import inventorymanagement.commande.reception.Vuereception;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.util.Arrays;

public class CommandeManager extends HServiceManager implements CommandeManagerSignature {

    CommandeManagerSignature commandeManagerSignature;
    ClientManager clientManager= new ClientManager();
    ArticleManager articleManager= new ArticleManager();

    public CommandeManager() {
        this.commandeManagerSignature = commandeManagerSignature;
    }


//    Commande
    @Override
    public void createcommande(Commande commande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (commande.getIdcommande()!=null) {
            commande.updateToTable(connection);
            return;
        }else {
            commande.construirePK(connection);
            Commande commande1 = (Commande) CGenUtil.save(commande, connection);
            System.out.println("Taille du tableau:" +commande1.getDetailcommandes().length);
            if (commande1.getDetailcommandes().length!=0){
                Arrays.stream(commande1.getDetailcommandes()).forEach(item -> item.setIdcommande(commande1.getIdcommande()));
                this.createdetails(commande1.getDetailcommandes(), connection);
            }

        }
    }
    public void createdetails(Detailcommande[] detailcommandes, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Detailcommande detailcommande1 :detailcommandes) {
            if (detailcommande1.getIddetailcommande() != null) {
                detailcommande1.updateToTable(connection);
            }
            detailcommande1.construirePK(connection);
            CGenUtil.save(detailcommande1, connection);
        }
    }

    @Override
    public VueCommande[] getallcommande(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        VueCommande[] data =(VueCommande[]) CGenUtil.rechercher(new VueCommande(), new String[0], new String[0], connection, "");
        return data;
    }

    @Override
    public Detailcommandeview[] getalldetailcommande(String idcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Detailcommandeview[] data =(Detailcommandeview[]) CGenUtil.rechercher(new Detailcommandeview(), new String[0], new String[0], connection, "and idcommande='"+idcommande+"'");
        return data;
    }

    //Details de la commande
    public void createsingledetail(Detailcommande detailcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (detailcommande.getIddetailcommande() != null) {
            detailcommande.updateToTable(connection);
        }else {
            detailcommande.construirePK(connection);
            CGenUtil.save(detailcommande, connection);
        }
    }



    @Override
    public CommandePageList commandcontentpage(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setVueCommandes(this.getallcommande(connection));
        commandePageList.setClients(clientManager.getall(connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    @Override
    public CommandePageList contentform(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setClients(clientManager.getall(connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    @Override
    public Commande getcommande(String idcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Commande[] data =(Commande[]) CGenUtil.rechercher(new Commande(), new String[0], new String[0], connection, "and idcommande='"+idcommande+"'");
        return data[0];
    }

    @Override
    public Detailcommande getdetailcommande(String iddetailcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Detailcommande[] data =(Detailcommande[]) CGenUtil.rechercher(new Detailcommande(), new String[0], new String[0], connection, "and iddetailcommande='"+iddetailcommande+"'");
        return data[0];
    }

    @Override
    public Vuereception[] getallreception(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Vuereception[] data =(Vuereception[]) CGenUtil.rechercher(new Vuereception(), new String[0], new String[0], connection, "");
        return data;
    }


    public CommandePageList detailcontentpage(Connection connection, String idcommande) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setDetailcommandeviews(this.getalldetailcommande(idcommande,connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    public CommandePageList editform(String idcommande,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setCommande(getcommande(idcommande,connection));
        commandePageList.setClients(clientManager.getall(connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    public CommandePageList editformdetails(String iddetailcommande,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setDetailcommande(getdetailcommande(iddetailcommande,connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }
    public CommandePageList reception(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setVuereceptions(this.getallreception(connection));
        return commandePageList;
    }





}
