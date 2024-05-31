package inventorymanagement.emplacement;

import inventorymanagement.etudiant.ListeEtudiant;

import java.sql.Connection;

public interface EmplacementManagerSignature {
    public Listeemplacement[] getall(Connection connection)throws Exception;
    public EmplacementPageList contentlist(Connection connection)throws Exception;
    public Emplacement getOne(String idarticle,Connection connection)throws Exception;
    public void create(Emplacement emplacement,Connection connection) throws Exception;
}
