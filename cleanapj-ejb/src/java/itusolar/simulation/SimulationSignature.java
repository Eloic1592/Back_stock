package itusolar.simulation;

import itusolar.jirama.Tarif;
import itusolar.predict.PredictionParameter;
import itusolar.prepare.schedule.Schedule;
import itusolar.simulation.response.SimulPred;

import java.sql.Connection;

public interface SimulationSignature {


    /**
     * Separe les données de simulation par interval.
     *
     * @param datas the datas
     * @return the simul data [ ]
     */


    public SimulPred prepareToPredict(PredictionParameter datas, Tarif tarif, Connection connection) throws Exception;

}
