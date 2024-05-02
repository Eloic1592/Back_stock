package itusolar.predict.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HourLy extends MeteoDetails {
    public void setTemperature_2m(double[] values) {
        this.setTemperaturesValues(values);
    }

    @Override
    public String format(Timestamp timeKeys) {
        String result = timeKeys.toString();
        result = result.split("\\.")[0];
        result = result.replace(" ", "T");
        String[] splited = result.split(":");
        result = splited[0]+":00";
        return result;
    }
}
