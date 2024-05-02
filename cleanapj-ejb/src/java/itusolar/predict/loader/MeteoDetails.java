package itusolar.predict.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MeteoDetails {
    String[] time;
    double[] temperaturesValues;
    HashMap<String,Double> response = new HashMap<>();
    boolean init = false;

    @Override
    public String toString() {
        return "MeteoDetails{" +
                "time=" + Arrays.toString(time) +
                ", temperaturesValues=" + Arrays.toString(temperaturesValues) +
                '}';
    }

    public double search(Timestamp searchKey) {
        String timeKeys = this.format(searchKey);
        if (!this.isInit())
            this.init();
        return this.getResponse().get(timeKeys);
    }

    public abstract String format(Timestamp timeKeys);

    public void init() {
        for (int i = 0; i < this.getTime().length; i++) {
            this.getResponse().put(this.getTime()[i], this.getTemperaturesValues()[i]);
        }
        this.setInit(true);
        // hash map
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public double[] getTemperaturesValues() {
        return temperaturesValues;
    }

    public HashMap<String, Double> getResponse() {
        return response;
    }

    public void setResponse(HashMap<String, Double> response) {
        this.response = response;
    }

    public void setTemperaturesValues(double[] temperaturesValues) {
        this.temperaturesValues = temperaturesValues;
    }
}
