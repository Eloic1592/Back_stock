package itusolar.prepare.schedule.predict;

import itusolar.predict.PredictionParameter;
import itusolar.simulation.response.ScheduleRenderingSignature;
import itusolar.simulation.response.SimulPred;

public class SchedulePage {
    PredictionParameter parameter;
    SimulPred prediction;

    public PredictionParameter getParameter() {
        return parameter;
    }

    public void setParameter(PredictionParameter parameter) {
        this.parameter = parameter;
    }

    public SimulPred getPrediction() {
        return prediction;
    }

    public void setPrediction(SimulPred prediction) {
        this.prediction = prediction;
    }
    public ScheduleRenderingSignature[] getSchedules() {
        ScheduleRenderingSignature[] consumptionStates =this.getPrediction().getSchedules();
        ScheduleRenderingSignature[] parameterDatas = this.getParameter().getDatas();
        ScheduleRenderingSignature[] results = new ScheduleRenderingSignature[consumptionStates.length+parameterDatas.length];
        int index = 0;
        index = this.add(results, index,consumptionStates);
        this.add(results, index,parameterDatas);
        return results;
    }

    public int add(ScheduleRenderingSignature[] results, int index, ScheduleRenderingSignature[] datas) {
        for (ScheduleRenderingSignature data : datas) {
            results[index] = data;
            index++;
        }
        return index;
    }
}
