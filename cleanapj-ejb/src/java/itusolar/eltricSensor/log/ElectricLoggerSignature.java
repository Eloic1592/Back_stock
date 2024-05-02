package itusolar.eltricSensor.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import itusolar.eltricSensor.ElectricSensor;

import java.sql.Timestamp;

public interface ElectricLoggerSignature {
    public void log( Timestamp date) throws JsonProcessingException;
    public void log() throws JsonProcessingException;
}
