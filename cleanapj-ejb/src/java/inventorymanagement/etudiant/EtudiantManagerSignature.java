package inventorymanagement.etudiant;

import java.sql.Connection;

public interface EtudiantManagerSignature {
    public ListeEtudiant[] getall(Connection connection)throws Exception;
}
