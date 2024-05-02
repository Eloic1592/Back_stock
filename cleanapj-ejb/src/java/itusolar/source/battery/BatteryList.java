package itusolar.source.battery;

public class BatteryList {
    Battery[] batteries;

    public BatteryList(Battery[] batteries) {
        this.setBatteries(batteries);
    }

    public Battery[] getBatteries() {
        return batteries;
    }

    public void setBatteries(Battery[] batteries) {
        this.batteries = batteries;
    }
}
