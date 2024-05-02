package itusolar.simulation.material;

public class NegativeConsumptionException extends Exception {
    public NegativeConsumptionException() {
        super("Negative consumption");
    }
}
