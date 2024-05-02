package inventorymanagement.mouvement.naturemouvement;



import java.sql.Connection;

public interface NaturemouvementManagerSignature {
    public void create(Naturemouvement naturemouvement, Connection connection) throws Exception;
    public Naturemouvement[] getall(Connection connection) throws Exception;
    public NaturemouvementPageList contentlist(Connection connection) throws Exception;
}
