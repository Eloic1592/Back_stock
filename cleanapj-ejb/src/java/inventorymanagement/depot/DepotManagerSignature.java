package inventorymanagement.depot;

import java.sql.Connection;

public interface DepotManagerSignature {
    public void create(Depot depot, Connection connection) throws Exception;
    public Depot[] getall(Connection connection) throws Exception;
    public DepotPageList contentlist(Connection connection) throws Exception;
}
