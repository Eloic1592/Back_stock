package itusolar.predict.etatconsommation;

public class ExcesException extends Exception {
    ConsumptionState exces;

    public ConsumptionState getExces() {
        return exces;
    }

    public void setExces(ConsumptionState exces) {
        this.exces = exces;
    }
}
