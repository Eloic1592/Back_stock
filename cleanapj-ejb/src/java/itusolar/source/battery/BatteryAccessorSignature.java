package itusolar.source.battery;

import java.sql.Connection;

public interface BatteryAccessorSignature {
    public void save(Battery battery, Connection connection) throws Exception;
    public BatteryForm formContent(Battery battery,Connection connection) throws Exception;
    public Battery[] getAll(Connection connection) throws Exception;
    public Battery[] search(String where,Connection connection) throws Exception;
    public BatteryList listContent(Connection connection) throws Exception;
}
