package itusolar.jirama;

import java.sql.Connection;

public interface HTarifManager {
    public void create(Tarif tarif) throws Exception;
    public void create(Tarif tarif,Connection connection) throws Exception;

    public TarifForm formContent(Connection connection) throws Exception;
    public Tarif getTarif(Connection connection) throws Exception;

    public Tarif getTarifComplete(Connection connection) throws Exception;

    public PageTarifResponse pageTarifContent(Connection connection)throws Exception;
}
