package itusolar.predict.etatconsommation;

import itusolar.simulation.SimulData;

public class Deficit extends ConsumptionState {
    private SimulData[] consommation;
    public Deficit() {
        this.setType(ConsumptionType.Deficit.getValue());
    }

    public Deficit(SimulData[] consommation) {
        this();
        this.setConsommation(consommation);
    }

    public SimulData[] getConsommation() {
        return consommation;
    }

    public void setConsommation(SimulData[] consommation) {
        this.consommation = consommation;
    }

    @Override
    public double getValue() {
        double value = 0.;
        if (this.getConsommation() != null) {
            for (SimulData cons : this.getConsommation())
                value += cons.getVitesse();
        }
        return value;
    }

    @Override
    public String getEtatLabel() {
        return "Déficitaire";
    }

    @Override
    public int getEtat() {
        return ConsumptionState.DEFICIT;
    }

    @Override
    public String getBackgroundColor() {
        return "#f23030";
    }

    @Override
    public int getState() {
        return this.getType();
    }
}
