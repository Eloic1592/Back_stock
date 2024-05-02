package itusolar.eltricSensor.stat.collector;

import itusolar.eltricSensor.ElectricSensor;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public interface MultipleCollectorSignature {
    public CollectedDatas collect(LocalDate start, LocalDate end, Connection connection) throws Exception;
    public CollectedDatas collect(Connection connection) throws Exception;
    public CollectedDatas collect(ElectricSensor[] sensors) throws DateNotNormalException, IOException;
    public CollectedDatas collect(ElectricSensor[] sensors, LocalDate start, LocalDate end) throws DateNotNormalException, IOException;
}
