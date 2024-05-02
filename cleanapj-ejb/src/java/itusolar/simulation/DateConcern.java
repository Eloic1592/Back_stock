package itusolar.simulation;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.TimeZone;

public class DateConcern implements MeteoSignature{
    Date date;
    Time time;
    double temperature;
    Timestamp timestamp;

    @Override
    public String toString() {
        return "DateConcern{" +
                "date=" + date +
                ", time=" + time +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                '}';
    }

    public DateConcern() {
    }

    public DateConcern(Timestamp temps) {
        this.setTimestamp(temps);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        this.setDate(new Date(this.getTimestamp().getTime()));
        this.setTime(new Time(this.getTimestamp().getTime()));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getLabel() {
        String[] days = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
        LocalDate lDate = this.getDate().toLocalDate();
        String day = days[lDate.getDayOfWeek().getValue()-1];
        day += ", "+lDate.getDayOfMonth();
        return day;
    }
}
