package itusolar.historique;

import itusolar.source.VSource;

public class InverterFormRequirements {
    VSource[] inverters;

    public InverterFormRequirements(VSource[] inverters) {
        this.setInverters(inverters);
    }

    public VSource[] getInverters() {
        return inverters;
    }

    public void setInverters(VSource[] inverters) {
        this.inverters = inverters;
    }
}
