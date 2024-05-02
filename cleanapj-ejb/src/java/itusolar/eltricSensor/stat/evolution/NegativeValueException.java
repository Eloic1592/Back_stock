package itusolar.eltricSensor.stat.evolution;

public class NegativeValueException extends Exception{
    double value;

    public NegativeValueException(double value) {
        super("Negative value");
        this.setValue(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
