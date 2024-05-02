package itusolar.eltricSensor.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.eltricSensor.ElectricSensor;

import java.sql.Timestamp;
import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElectricLogMessage  {
    ElectricSensor sensor;
    Timestamp date;

    @JsonIgnore
    public String getDayKey() {
        LocalDate date = this.toDate();
        String sensorKey = String.format("%s_%s_", this.getSensor().getId(), this.getSensor().getTitle());
        return String.format("%s_%s_%s_%s", sensorKey, date.getDayOfMonth(), date.getMonth().getValue(),
                date.getYear());
    }

    @JsonIgnore
    public String getMonthKey() {
        LocalDate date = this.toDate();
        String sensorKey = String.format("%s_%s_", this.getSensor().getId(), this.getSensor().getTitle());
        return String.format("%s_%s_%s", sensorKey, date.getMonth().getValue(),
                date.getYear());
    }

    @JsonIgnore
    public String getRoomDayKey() {
        return String.format("%s_%s", this.getDayKey(), this.getSensor().getRoomId());
    }

    @JsonIgnore
    public String getRoomMonthKey() {
        return String.format("%s_%s", this.getMonthKey(), this.getSensor().getRoomId());
    }

    public LocalDate toDate() {
        return this.getDate().toLocalDateTime().toLocalDate();
    }

    public ElectricLogMessage() {
    }

    public ElectricLogMessage(ElectricSensor sensor, Timestamp date) {
        this.setSensor(sensor);
        this.setDate(date);
    }

    public ElectricSensor getSensor() {
        return sensor;
    }

    public void setSensor(ElectricSensor sensor) {
        this.sensor = sensor;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
