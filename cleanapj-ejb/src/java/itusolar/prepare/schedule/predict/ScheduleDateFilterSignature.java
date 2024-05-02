package itusolar.prepare.schedule.predict;

import itusolar.predict.PredictionParameter;

import java.sql.Connection;
import java.sql.Date;

public interface ScheduleDateFilterSignature {
    public ScheduleDetailsCompleted[] search(String where, Connection connection) throws Exception;
    public ScheduleDetailsCompleted[] search(PredictionParameter data, Connection connection) throws Exception;
}
