package inventorymanagement.client;


import java.sql.Connection;

public interface ClientManagerSignature {
    public Client[] getall(Connection connection) throws Exception;
}
