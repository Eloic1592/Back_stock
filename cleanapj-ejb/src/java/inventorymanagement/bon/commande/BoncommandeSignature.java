package inventorymanagement.bon.commande;

import java.sql.Connection;

public interface BoncommandeSignature {
    public void createcommande(Boncommande [] boncommande, Connection connection)throws Exception;
    public Clientboncommande[] getall (Connection connection) throws  Exception;
    public CommandepageList contentlist(String idproforma,Connection connection) throws Exception;
}
