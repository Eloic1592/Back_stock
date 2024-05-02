package itusolar.historique.wait;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.eltricSensor.ElectricState;

import java.time.LocalDateTime;

public class ElectricWaitState extends ElectricState {
    public static int timeLost = 5;

    LocalDateTime current = LocalDateTime.now();

    public void lost() {
        LocalDateTime temp = LocalDateTime.now();
        temp = temp.minusSeconds(ElectricWaitState.timeLost);
        this.setCurrent(temp);
    }

    @Override
    public boolean isOpen() {
        return this.isOpen(ElectricWaitState.timeLost);
    }

    @Override
    public boolean isOpen(int laps) {
        LocalDateTime temp = this.current.plusSeconds(laps);
        return temp.isAfter(LocalDateTime.now());
    }

    public void reload() {
        this.setCurrent(LocalDateTime.now());
    }

    @JsonIgnore
    public LocalDateTime getCurrent() {
        return current;
    }

    public void setCurrent(LocalDateTime current) {
        this.current = current;
    }
}
