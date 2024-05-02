package itusolar.simulation;

import itusolar.jirama.Tarif;
import itusolar.predict.PredictionParameter;
import itusolar.prepare.HServiceManager;
import itusolar.prepare.schedule.Schedule;
import itusolar.simulation.consumption.ConsumptionEvaluatorSignature;
import itusolar.simulation.preparation.DataPreparatorSignature;
import itusolar.simulation.preparation.DataPrepared;
import itusolar.simulation.preparation.DatePreparatorSignature;
import itusolar.simulation.response.SimulPred;

import java.sql.Connection;

/**
 * The type Simulation manager.
 */
public  class SimulationManager extends HServiceManager implements SimulationSignature{
    ConsumptionEvaluatorSignature consumptionEvaluator;
    DataPreparatorSignature preparatory;

    DatePreparatorSignature datePreparatory;

    public SimulationManager(ConsumptionEvaluatorSignature consumptionEvaluator, DataPreparatorSignature preparatory,
                             DatePreparatorSignature datePreparatory) {
        this.consumptionEvaluator = consumptionEvaluator;
        this.preparatory = preparatory;
        this.datePreparatory = datePreparatory;
    }

    @Override
    public SimulPred prepareToPredict(PredictionParameter datas, Tarif tarif, Connection connection) throws Exception {
        DateConcern[] concerns = this.datePreparatory.filterConcern(datas); // Filtre les dates concernées durant la simulation
        return this.prepareToPredict(datas, concerns, tarif,connection);
    }

    public SimulPred prepareToPredict(PredictionParameter datas, DateConcern[] concerns,Tarif tarif, Connection connection) throws Exception{
        DataPrepared dataPrepared= this.preparatory.prepare(datas.getDatas());
        return new SimulPred(this.consumptionEvaluator,dataPrepared.getSolaires(),dataPrepared.getJirama(),concerns,tarif);
    }

}
