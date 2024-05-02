package itusolar.source.battery;

public class BatteryForm {
    Battery battery;

    public BatteryForm(Battery battery) {
        this.setBattery(battery);
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
