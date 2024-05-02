package itusolar.eltricSensor.beeper;

import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.call.ElectricTestExecutor;

public class OneBeeper extends ElectricTestExecutor {
    boolean active = true;
    public OneBeeper(ElectricState state) {
        super(state);
    }

    @Override
    public String getUrlSensor() {
        String response = super.getUrlSensor();
        if (active) {
            response += "/start";
        } else {
            response += "/stop";
        }
        return response;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void open() {
        this.setActive(true);
    }

    public void close() {
        this.setActive(false);
    }
}
