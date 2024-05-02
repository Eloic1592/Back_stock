package itusolar.predict.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoData {
    HourLy hourly;
    Daily daily;

    public HourLy getHourly() {
        return hourly;
    }

    public void setHourly(HourLy hourly) {
        this.hourly = hourly;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "MeteoData{" +
                "hourly=" + hourly +
                ", daily=" + daily +
                '}';
    }
}
