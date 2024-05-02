package itusolar.simulation.preparation;

import itusolar.simulation.SimulData;

import java.sql.Timestamp;

public interface DataPreparatorSignature {
    public DataPrepared prepare(SimulData[] data);

    public Timestamp[] splitHours(Timestamp start, Timestamp end);
    public SimulData[] sort(SimulData[] datas);
}
