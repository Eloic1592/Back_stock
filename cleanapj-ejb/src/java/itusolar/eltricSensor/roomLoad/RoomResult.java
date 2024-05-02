package itusolar.eltricSensor.roomLoad;

import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.Room;

public class RoomResult {
    Room[] rooms;
    ElectricState[] states;

    public RoomResult(Room[] rooms, ElectricState[] states) {
        this.setRooms(rooms);
        this.setStates(states);
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public ElectricState[] getStates() {
        return states;
    }

    public void setStates(ElectricState[] states) {
        this.states = states;
    }
}
