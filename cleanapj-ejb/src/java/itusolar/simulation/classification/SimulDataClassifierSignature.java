package itusolar.simulation.classification;

import itusolar.simulation.SimulData;

public interface SimulDataClassifierSignature {
    public SimulData[] classifySolar(SimulData[] datas);
    public SimulData[] classifyJirama(SimulData[] datas);
}
