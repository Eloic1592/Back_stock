package itusolar.predict.etatconsommation;

public class Excess extends ConsumptionState {
    public Excess() {
        this.setType(ConsumptionType.Exces.getValue());
    }

    @Override
    public double getValue() {
        return this.conso;
    }

    @Override
    public String getEtatLabel() {
        return "Excés";
    }

    @Override
    public int getEtat() {
        return ConsumptionState.EXCES;
    }


    @Override
    public String getBackgroundColor() {
        return "#00bbc9";
    }

    @Override
    public int getState() {
        return this.getType();
    }
}
