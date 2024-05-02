package inventorymanagement.materiel.materiel;
import inventorymanagement.article.Listearticle;

import java.sql.Connection;

public interface MaterielManagerSignature {
    public void create(Materiel materiel, Connection connection) throws Exception;
    public Listemateriel[] getall(Connection connection) throws Exception;
    public Materiel[] getallmateriel(Connection connection) throws Exception;
    public MaterielPageList contentlist(Connection connection) throws Exception;
    public MaterielPageList contentform(Connection connection) throws Exception;
    public Listemateriel getOne(String idmateriel, Connection connection) throws Exception;
    public void importexcelfile(Connection connection,Materiel [] materiel) throws  Exception;
}
