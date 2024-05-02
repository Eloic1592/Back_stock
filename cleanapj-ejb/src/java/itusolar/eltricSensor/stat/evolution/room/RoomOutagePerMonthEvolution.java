package itusolar.eltricSensor.stat.evolution.room;

import itusolar.eltricSensor.log.ElectricLogMessage;
import itusolar.eltricSensor.roomLoad.RoomAccessorSignature;
import itusolar.eltricSensor.stat.collector.MultipleCollectorSignature;

public class RoomOutagePerMonthEvolution extends RoomOutagePerDayEvolution {
    public RoomOutagePerMonthEvolution(MultipleCollectorSignature collector, RoomAccessorSignature roomAccessor) {
        super(collector, roomAccessor);
    }

    @Override
    public String getKey(ElectricLogMessage message) {
        return message.getRoomMonthKey();
    }
}
