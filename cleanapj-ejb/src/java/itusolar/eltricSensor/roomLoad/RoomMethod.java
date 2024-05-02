package itusolar.eltricSensor.roomLoad;

import itusolar.eltricSensor.*;
import itusolar.historique.wait.ElectricWaitMethod;

import java.sql.Connection;

public class RoomMethod extends ElectricWaitMethod {
    Room[] rooms;
    RoomAccessorSignature roomAccessor;

    public RoomMethod(ElectricAccessorSignature accessor, RoomAccessorSignature roomAccessor) {
        super(accessor);
        this.roomAccessor = roomAccessor;
    }

    @Override
    public ElectricPage pageContent(Connection connection) throws Exception {
        RoomPage page = new RoomPage();
        this.initStates(null, connection);
        page.setRooms(this.getRooms());
        return page;
    }

    @Override
    public void initStates(ElectricSensor sensor, Connection connection) throws Exception {
        boolean verif = this.getRooms() == null;
        if (verif) {
            connection = this.getConnection(connection);
            this.loadRoom(connection);
            this.addToMaps();
            connection.close();
        }
    }

    public void loadRoom(Connection connection) throws Exception {
        RoomResult result = this.getRoomAccessor().getRooms(new Room(), connection);
        this.setRooms(result.getRooms());
        this.setStates(result.getStates());
    }

    @Override
    public void addToMaps() {
        for (Room room : this.getRooms()) {
            this.addToMaps(room);
        }
    }

    public void addToMaps(Room room) {
        for (Line line : room.getLines()) {
            this.addToMaps(line);
        }
    }

    public void addToMaps(Line line) {
        this.addToMaps((ElectricState[]) line.getSensors());
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public RoomAccessorSignature getRoomAccessor() {
        return roomAccessor;
    }

    public void setRoomAccessor(RoomAccessorSignature roomAccessor) {
        this.roomAccessor = roomAccessor;
    }
}
