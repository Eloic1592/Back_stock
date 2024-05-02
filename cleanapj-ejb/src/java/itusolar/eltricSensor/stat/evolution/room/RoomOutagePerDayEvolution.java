package itusolar.eltricSensor.stat.evolution.room;

import itusolar.eltricSensor.Room;
import itusolar.eltricSensor.log.ElectricLogMessage;
import itusolar.eltricSensor.roomLoad.RoomAccessorSignature;
import itusolar.eltricSensor.stat.collector.MultipleCollectorSignature;
import itusolar.eltricSensor.stat.evolution.OutagePage;
import itusolar.eltricSensor.stat.evolution.OutageParams;
import itusolar.eltricSensor.stat.evolution.sensor.OutagePerDayEvolution;

import java.sql.Connection;

public class RoomOutagePerDayEvolution extends OutagePerDayEvolution {
    RoomAccessorSignature roomAccessor;
    public RoomOutagePerDayEvolution(MultipleCollectorSignature collector, RoomAccessorSignature roomAccessor) {
        super(collector);
        this.setRoomAccessor(roomAccessor);
    }

    @Override
    public OutagePage evolutionPage(OutageParams params, Connection connection) throws Exception {
        OutagePage page =  super.evolutionPage(params, connection);
        Room[] rooms = this.getRoomAccessor().search(new Room(),"", connection);
        OutageRoomPage result = new OutageRoomPage(page.getPoints(), rooms);
        result.config(roomAccessor, connection);
        return result;
    }

    @Override
    public String getKey(ElectricLogMessage message) {
        return message.getRoomDayKey();
    }

    public RoomAccessorSignature getRoomAccessor() {
        return roomAccessor;
    }

    public void setRoomAccessor(RoomAccessorSignature roomAccessor) {
        this.roomAccessor = roomAccessor;
    }
}
