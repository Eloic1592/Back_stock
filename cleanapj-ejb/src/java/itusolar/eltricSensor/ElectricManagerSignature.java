package itusolar.eltricSensor;

import java.sql.Connection;

public interface ElectricManagerSignature {

    public ElectricPage pageContent(Connection connection) throws Exception;
    public ElectricState[] evaluate(Connection connection) throws Exception;
}
