package itusolar.eltricSensor.stat.evolution.room;

import itusolar.eltricSensor.Room;
import itusolar.eltricSensor.roomLoad.RoomAccessorSignature;
import itusolar.eltricSensor.stat.evolution.OutagePage;
import itusolar.eltricSensor.stat.evolution.OutagePoint;

import java.sql.Connection;

public class OutageRoomPage extends OutagePage {
    Room[] rooms;

    public OutageRoomPage(OutagePoint[] points, Room[] rooms) {
        super(points);
        this.setRooms(rooms);
    }

    public OutageRoomPage(OutagePoint[] points) {
        super(points);
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public void config(RoomAccessorSignature roomAccessor, Connection connection) throws Exception {
        for (OutagePoint point : this.getPoints()) {
            String where = " and id ="+point.getSensor().getRoomId();
            Room[] results = roomAccessor.search(new Room(), where, connection);
            if (rooms.length > 0) {
                Room room = results[0];
                point.setRoom(room);
                point.getSensor().setTitle(room.getTitle());
            }
        }
    }
}
