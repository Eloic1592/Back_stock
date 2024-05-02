package itusolar.prepare.schedule.devide;

import com.fasterxml.jackson.annotation.JsonFormat;
import itusolar.prepare.schedule.Schedule;

import java.sql.Timestamp;

public class DividerParams {
    Schedule schedule;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp date;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
