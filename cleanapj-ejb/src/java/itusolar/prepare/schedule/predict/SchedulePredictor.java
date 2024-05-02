package itusolar.prepare.schedule.predict;

import itusolar.predict.PredicterSignature;
import itusolar.predict.PredictionParameter;
import itusolar.prepare.HServiceManager;
import itusolar.simulation.response.SimulPred;

import java.sql.Connection;
import java.sql.Date;

public class SchedulePredictor extends HServiceManager implements SchedulePredictorSignature {
    SchedulePreparatorySignature preparatory;
    PredicterSignature predictor;

    public SchedulePredictor(SchedulePreparatorySignature preparatory, PredicterSignature predictor) {
        this.preparatory = preparatory;
        this.predictor = predictor;
    }

    @Override
    public SchedulePage predict(Date start, Date end, Connection connection) throws Exception {
//        connection = this.getConnection(connection);
//        PredictionParameter parameter = this.preparatory.prepare(start, end, connection);
//        SimulPred prediction = this.predictor.predict(parameter,connection);
//        SchedulePage page = new SchedulePage();
//        page.setPrediction(prediction);
//        page.setParameter(parameter);
//        return page;
        return null;
    }

    @Override
    public SchedulePage predict(ScheduleParams params, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return this.predict(params.getDebut(),params.getFin(),connection);
    }
}
