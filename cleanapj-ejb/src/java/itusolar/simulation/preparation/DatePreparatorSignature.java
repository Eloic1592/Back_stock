package itusolar.simulation.preparation;

import itusolar.predict.PredictionParameter;
import itusolar.simulation.DateConcern;
import itusolar.simulation.SimulData;

public interface DatePreparatorSignature {
    public DateConcern[] filterConcern(PredictionParameter data);
}
