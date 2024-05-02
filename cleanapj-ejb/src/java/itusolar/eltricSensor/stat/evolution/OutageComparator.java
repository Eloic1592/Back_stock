package itusolar.eltricSensor.stat.evolution;

import java.util.Comparator;

public class OutageComparator implements Comparator<OutagePoint> {
    @Override
    public int compare(OutagePoint o1, OutagePoint o2) {
        return Double.compare(o1.getSum(), o2.getSum());
    }
}
