package itusolar.eltricSensor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;
import java.util.Vector;

public class Line extends Room {
    ElectricSensor[] sensors;
    @JsonIgnore
    Vector<ElectricSensor> sensorsTemp;
    public Line() {
        super();
        this.setNomTable("line");
    }

    @Override
    public void load() {
        this.setSensors(new ElectricState[this.sensorsTemp.size()]);
        this.getSensorsTemp().copyInto(this.getSensors());
    }

    @Override
    public void add(ElectricState state, Map<Integer, Line> lines) throws CloneNotSupportedException {
        if (this.getSensorsTemp() == null) {
            this.setSensorsTemp(new Vector<>());
        }
        this.getSensorsTemp().add(state);
    }

    public ElectricSensor[] getSensors() {
        return sensors;
    }

    public void setSensors(ElectricSensor[] sensors) {
        this.sensors = sensors;
    }

    public Vector<ElectricSensor> getSensorsTemp() {
        return sensorsTemp;
    }

    public void setSensorsTemp(Vector<ElectricSensor> sensorsTemp) {
        this.sensorsTemp = sensorsTemp;
    }

    @Override
    public Room clone() {
        return super.clone();
    }

}
