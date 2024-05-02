package itusolar.eltricSensor.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itusolar.eltricSensor.ElectricSensor;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.*;

public class ElectricLogger implements ElectricLoggerSignature {

    public static String signature = "SensorLogger";
    Logger logger;
    ElectricSensor sensor;
    boolean changed = false;
    boolean mailChanged = false;

    public ElectricLogger(ElectricSensor sensor,String name) throws IOException {
        this.setSensor(sensor);
        this.setLogger(Logger.getLogger(sensor.getFileHandler()));
        this.handleChange();
    }

    public void handleChange() throws IOException {
        String path = this.getSensor().getFileHandler();
        FileHandler handler = new FileHandler(path,true);
        this.getLogger().addHandler(handler);
        this.deleteConsoleHandler();
        SimpleFormatter formatter = new SimpleFormatter();
        handler.setFormatter(formatter);
    }

    public void deleteConsoleHandler() {
        for (Handler handler : this.getLogger().getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                this.getLogger().removeHandler(handler);
                break;
            }
        }
    }

    @Override
    public void log(Timestamp date) throws JsonProcessingException {
        date = (date == null)? Timestamp.valueOf(LocalDateTime.now()): date;
        ElectricLogMessage message = new ElectricLogMessage(this.getSensor(), date);
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(message);
        this.getLogger().info(data);
    }

    @Override
    public void log() throws JsonProcessingException {
        this.log(Timestamp.valueOf(LocalDateTime.now()));
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ElectricSensor getSensor() {
        return sensor;
    }

    public void setSensor(ElectricSensor sensor) {
        this.sensor = sensor;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isMailChanged() {
        return mailChanged;
    }

    public void setMailChanged(boolean mailChanged) {
        this.mailChanged = mailChanged;
    }
}
