package inventorymanagement.materiel.typemateriel;
import java.sql.Connection;

public interface TypematerielManagerSignature {
    public void create(Typemateriel typemateriel, Connection connection) throws Exception;
    public Typemateriel[] getall(Connection connection) throws Exception;
    public TypematerielPageList contentlist(Connection connection) throws Exception;
}
