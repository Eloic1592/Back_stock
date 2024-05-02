package itusolar.simulation.material;

import java.sql.Connection;

public interface MaterialManagerSignature {

    public CompoForm[] searchCompo(String where, Connection connection) throws Exception;
    public CompoForm[] searchIgnore(Material material, Connection connection) throws Exception;
    public Material[] search(String where, Connection connection) throws Exception;
    public MaterialForm formContent(Material material,Connection connection ) throws Exception;
    public MaterialList listContent(Connection connection) throws Exception;
    public Material[] getAll(Connection connection) throws Exception;
    public Material getById(int id, Connection connection) throws Exception;

    public void save(Material material, Connection connection) throws Exception;
}
