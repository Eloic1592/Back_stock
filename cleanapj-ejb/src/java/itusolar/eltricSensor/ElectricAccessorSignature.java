package itusolar.eltricSensor;

import java.sql.Connection;

public interface ElectricAccessorSignature {
    public ElectricState[] getAll(ElectricSensor sensor,Connection connection) throws Exception;
}
