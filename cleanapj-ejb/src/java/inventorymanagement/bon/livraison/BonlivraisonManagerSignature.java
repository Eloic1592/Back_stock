package inventorymanagement.bon.livraison;

import java.sql.Connection;

public interface BonlivraisonManagerSignature {
    public Clientlivraison[] getall(Connection connection) throws Exception;
    public void createlivraison(Bonlivraison[] bonlivraisons, Connection connection)throws Exception;
}
