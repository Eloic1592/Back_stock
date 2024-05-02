package itusolar.eltricSensor.roomLoad;

import itusolar.eltricSensor.ElectricPage;
import itusolar.eltricSensor.Room;

import java.util.Arrays;
import java.util.Comparator;

public class RoomPage extends ElectricPage implements Comparator<Room> {
    Room[] rooms;

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        Arrays.sort(rooms, this);
        this.rooms = rooms;
    }

    @Override
    public int compare(Room o1, Room o2) {
        return o2.count()-o1.count();
    }
}
