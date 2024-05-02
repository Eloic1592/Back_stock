package itusolar.eltricSensor.stat.evolution;

import itusolar.eltricSensor.stat.collector.CollectedDatas;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDate;

public interface OutageEvolutionSignature {
    public OutagePoint[] evolution(LocalDate start, LocalDate end, Connection connection) throws Exception;
    public OutagePoint[] evolution(Timestamp start, Timestamp end, Connection connection) throws Exception;
    public OutagePoint[] evolution(CollectedDatas datas) throws Exception;
    public OutagePage evolutionPage(Timestamp start, Timestamp end, Connection connection) throws Exception;
    public OutagePage evolutionPage(OutageParams params, Connection connection) throws Exception;
}
