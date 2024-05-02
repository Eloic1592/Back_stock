package itusolar.prepare.schedule.predict;

import java.sql.Connection;
import java.sql.Date;

public interface SchedulePredictorSignature {
    public SchedulePage predict(Date start, Date end, Connection connection) throws Exception;

    public SchedulePage predict(ScheduleParams params, Connection connection) throws Exception;
}
