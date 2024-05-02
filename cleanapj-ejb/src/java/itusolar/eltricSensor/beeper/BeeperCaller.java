package itusolar.eltricSensor.beeper;

import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.call.ElectricTestExecutor;
import itusolar.eltricSensor.call.ElectricTester;

import java.io.IOException;

public class BeeperCaller extends ElectricTester {
    boolean handleStart = false;
    boolean handleStop = false;
    boolean active = true;

    @Override
    public void evaluate(ElectricState[] states) throws IOException {
        if ((!this.isHandleStart() && this.isActive()) || (!this.isHandleStop() && !this.isActive())) {
            super.evaluate(states);
        }
    }

    @Override
    public ElectricTestExecutor toExecutor(ElectricState state) {
        OneBeeper beeper = new OneBeeper(state);
        beeper.setActive(this.isActive());
        return beeper;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isHandleStart() {
        return handleStart;
    }

    public void setHandleStart(boolean handleStart) {
        this.handleStart = handleStart;
    }

    public boolean isHandleStop() {
        return handleStop;
    }

    public void setHandleStop(boolean handleStop) {
        this.handleStop = handleStop;
    }
}
