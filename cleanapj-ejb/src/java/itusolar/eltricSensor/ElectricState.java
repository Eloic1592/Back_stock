package itusolar.eltricSensor;

public class ElectricState extends ElectricSensor{
    boolean open = true;

    public ElectricState() {
        super();
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isOpen(int laps) {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
