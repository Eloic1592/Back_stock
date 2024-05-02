package itusolar.eltricSensor.stat.evolution;

import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.Room;

import java.time.LocalDate;

public class OutagePoint {
    ElectricSensor sensor;
    Room room;
    LocalDate date;
    double sum;

    public OutagePoint() {
    }

    public OutagePoint(ElectricSensor sensor, LocalDate date, double sum) throws NegativeValueException {
        this.setSensor(sensor);
        this.setDate(date);
        this.add(sum);
    }

    public void add(double point) throws NegativeValueException {
        double sum = this.getSum()+point;
        if (sum < 0) {
            throw new NegativeValueException(sum);
        }
        this.setSum(sum);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ElectricSensor getSensor() {
        return sensor;
    }

    public void setSensor(ElectricSensor sensor) {
        this.sensor = sensor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    private void setSum(double sum) {
        this.sum = sum;
    }
}
