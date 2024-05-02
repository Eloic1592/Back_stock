package itusolar.simulation.consumption;

import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;

public interface ConsumptionEvaluatorSignature {

    public SimulData[][] evaluate(SimulPred pred);
}
