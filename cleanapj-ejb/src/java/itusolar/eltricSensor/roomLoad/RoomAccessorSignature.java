package itusolar.eltricSensor.roomLoad;

import itusolar.eltricSensor.Room;

import java.sql.Connection;

public interface RoomAccessorSignature {
    public RoomResult getRooms(Room room, Connection connection) throws Exception;
    public Room[] search(Room room, String where, Connection connection) throws Exception;
}
