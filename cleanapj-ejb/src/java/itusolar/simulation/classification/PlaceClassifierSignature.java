package itusolar.simulation.classification;

import itusolar.simulation.SimulData;

public interface PlaceClassifierSignature {
    public SimulData[][] split(SimulData[] datas);
}
