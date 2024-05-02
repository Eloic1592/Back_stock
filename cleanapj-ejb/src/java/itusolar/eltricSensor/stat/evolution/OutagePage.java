package itusolar.eltricSensor.stat.evolution;

import itusolar.eltricSensor.ElectricSensor;

import java.util.Arrays;

public class OutagePage {
    OutagePoint[] points;
    ElectricSensor[] sensors;

    public OutagePage(OutagePoint[] points) {
        this.points = points;
    }

    public ElectricSensor[] getSensors() {
        return sensors;
    }

    public void setSensors(ElectricSensor[] sensors) {
        this.sensors = sensors;
    }

    public OutagePoint[] getPoints() {
        return points;
    }

    public void setPoints(OutagePoint[] points) {
        Arrays.sort(points, new OutageComparator());
        this.points = points;
    }
}
