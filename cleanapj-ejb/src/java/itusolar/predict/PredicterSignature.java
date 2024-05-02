package itusolar.predict;

import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;

import java.sql.Connection;

public interface PredicterSignature {
    public SimulPred predict(PredictionParameter data, Connection connection) throws Exception;
}
