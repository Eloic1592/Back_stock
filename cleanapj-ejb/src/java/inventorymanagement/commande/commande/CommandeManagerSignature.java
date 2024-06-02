package inventorymanagement.commande.commande;

import inventorymanagement.commande.detailcommande.Detailcommande;
import inventorymanagement.commande.detailcommande.Detailcommandeview;
import inventorymanagement.commande.reception.Vuereception;

import java.sql.Connection;

public interface CommandeManagerSignature {
    public void createcommande(Commande commande, Connection connection) throws Exception;
    public VueCommande[] getallcommande(Connection connection) throws Exception;
    public Detailcommandeview[] getalldetailcommande(String idcommande, Connection connection) throws Exception;
    public CommandePageList commandcontentpage(Connection connection) throws Exception;
    public void createdetails(Detailcommande[] detailcommandes, Connection connection) throws Exception;
    public CommandePageList contentform(Connection connection) throws Exception;
    public Commande getcommande(String idcommande,Connection connection) throws  Exception;
    public Detailcommande getdetailcommande(String iddetailcommande,Connection connection) throws  Exception;
    public Vuereception[] getallreception(Connection connection) throws Exception;

}
