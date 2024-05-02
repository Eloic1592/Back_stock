package itusolar.predict.etatconsommation;

public enum ConsumptionType {
    Exces(0), Deficit(50);
    int value;

    ConsumptionType(int value) {
        this.setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
