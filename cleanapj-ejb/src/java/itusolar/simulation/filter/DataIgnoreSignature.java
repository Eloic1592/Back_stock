package itusolar.simulation.filter;

import itusolar.simulation.SimulData;

public interface DataIgnoreSignature {
    public SimulData[] removeNotChanged(SimulData[] datas);
}
