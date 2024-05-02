package itusolar.prepare.schedule.predict;

import itusolar.predict.PredictionParameter;

import java.sql.Connection;
import java.sql.Date;

public interface SchedulePreparatorySignature {

    PredictionParameter prepare(PredictionParameter data, Connection connection) throws Exception;
}
