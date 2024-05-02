package itusolar.eltricSensor.stat.collector;

import itusolar.eltricSensor.ElectricSensor;

import java.io.IOException;
import java.time.LocalDate;

public interface SingleCollectorSignature {
    public CollectedDatas collect(ElectricSensor sensor, LocalDate date) throws IOException;
    public CollectedDatas collect(ElectricSensor sensor, LocalDate start, LocalDate end) throws IOException, DateNotNormalException;
}
