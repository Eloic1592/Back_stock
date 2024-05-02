package itusolar.predict.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Daily extends MeteoDetails {
    public void setTemperature_2m_max(double[] values) {
        this.setTemperaturesValues(values);
    }

    @Override
    public String format(Timestamp timeKeys) {
        String result = timeKeys.toString();
        result = result.split("\\.")[0];
        result = result.split(" ")[0];
        return result;
    }
}
