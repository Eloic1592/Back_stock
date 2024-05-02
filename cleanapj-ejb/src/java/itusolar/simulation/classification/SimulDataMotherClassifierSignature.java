package itusolar.simulation.classification;

import itusolar.simulation.SimulData;

public interface SimulDataMotherClassifierSignature {
    public SimulData[] classify(SimulData[] datas);

    public int count(SimulData[] datas);
}
