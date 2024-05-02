package itusolar.eltricSensor.stat.evolution.sensor;

import itusolar.eltricSensor.log.ElectricLogMessage;
import itusolar.eltricSensor.stat.collector.MultipleCollectorSignature;

public class OutagePerMonthEvolution extends OutagePerDayEvolution{
    public OutagePerMonthEvolution(MultipleCollectorSignature collector) {
        super(collector);
    }

    @Override
    public String getKey(ElectricLogMessage message) {
        return message.getMonthKey();
    }
}
