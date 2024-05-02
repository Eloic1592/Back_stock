package itusolar.eltricSensor.beeper;

import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.ElectricState;

import java.sql.Connection;

public class Beeper extends ElectricState {
    public Beeper() {
        this.setNomTable("beeper");
    }


    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqBeeper");
        this.setId(Integer.parseInt(makePK(c)));
    }
}
