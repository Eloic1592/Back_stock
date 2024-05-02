package itusolar.simulation;

import itusolar.jirama.Tarif;
import itusolar.predict.PredictionParameter;
import itusolar.predict.loader.Loader;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.schedule.predict.SchedulePreparatorySignature;
import itusolar.simulation.consumption.ConsumptionEvaluatorSignature;
import itusolar.simulation.preparation.DataPreparatorSignature;
import itusolar.simulation.preparation.DatePreparatorSignature;
import itusolar.simulation.response.SimulPred;

import java.sql.Connection;
import java.sql.Date;

public class SimulationScheduler extends SimulationManager {
    SchedulePreparatorySignature preparatory;
    LoaderSignature loader;
    public SimulationScheduler(Loader loader, SchedulePreparatorySignature schedulePreparatory, ConsumptionEvaluatorSignature consumptionEvaluator, DataPreparatorSignature preparatory, DatePreparatorSignature datePreparatory) {
        super(consumptionEvaluator, preparatory, datePreparatory);
        this.setPreparatory(schedulePreparatory);
        this.setLoader(loader);
    }

    @Override
    public SimulPred prepareToPredict(PredictionParameter data, DateConcern[] concerns, Tarif tarif, Connection connection) throws Exception {
        SimulData[] datas = data.getDatas();
        Date start = concerns[0].getDate();
        Date end = concerns[concerns.length-1].getDate();
        data.setStart(start);
        data.setEnd(end);
        PredictionParameter params = this.preparatory.prepare(data, connection);
        this.getLoader().loadAppareilAndSection(params.getDatas(),connection);
        datas = this.concat(datas, params.getDatas());
        data.setDatas(datas);
        return super.prepareToPredict(data, concerns, tarif,connection);
    }

    public SimulData[] concat(SimulData[] firstSeq, SimulData[] secondSeq) {
        SimulData[] results = new SimulData[firstSeq.length+secondSeq.length];
        int index = this.add(results, firstSeq, 0);
        this.add(results, secondSeq, index);
        return results;
    }

    public int add(SimulData[] results, SimulData[] sequences, int index) {
        for (SimulData data : sequences) {
            results[index] = data;
            index++;
        }
        return index;
    }

    public LoaderSignature getLoader() {
        return loader;
    }

    public void setLoader(LoaderSignature loader) {
        this.loader = loader;
    }

    public SchedulePreparatorySignature getPreparatory() {
        return preparatory;
    }

    public void setPreparatory(SchedulePreparatorySignature preparatory) {
        this.preparatory = preparatory;
    }
}
