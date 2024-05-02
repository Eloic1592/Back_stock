package itusolar.eltricSensor.roomLoad;

import bean.CGenUtil;
import itusolar.eltricSensor.ElectricAccessor;
import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.Line;
import itusolar.eltricSensor.Room;
import itusolar.historique.wait.ElectricWaitState;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class RoomAccessor extends ElectricAccessor implements RoomAccessorSignature {
    Map<Integer, Room> rooms = new HashMap<>();
    Map<Integer, Line> lines = new HashMap<>();

    @Override
    public RoomResult getRooms(Room room, Connection connection) throws Exception {
        Room[] rooms = this.search(room, "", connection);
        ElectricState[] sensors = this.getAll(new ElectricWaitState(), connection);
        Line[] lines = (Line[]) this.search(new Line(), "", connection);
        this.load(rooms, sensors, lines);
        return new RoomResult(rooms, sensors);
    }

    public void load(Room[] rooms, ElectricState[] states, Line[] lines) throws CloneNotSupportedException {
        this.load(rooms);
        this.load(lines);
        this.load(states);
        this.loadAll(rooms);
    }

    public void loadAll(Room[] rooms) {
        for (Room room : rooms) {
            room.load();
//            this.rooms.put(room.getId(), room);
        }
    }

    public void load(ElectricState[] states) throws CloneNotSupportedException {
        for (ElectricState state : states) {
            System.out.println(state);
            Room room = this.rooms.get(state.getRoomId());
            room.add(state, this.lines);
        }
    }

    public void load(Line[] lines) {
        this.lines = new HashMap<>();
        for (Line line : lines) {
            this.lines.put(line.getId(), line);
        }
    }

    public void load(Room[] rooms) {
        this.rooms = new HashMap<>();
        for (Room room : rooms) {
            this.rooms.put(room.getId(), room);
        }
    }

    @Override
    public Room[] search(Room room, String where, Connection connection) throws Exception {
        String[] intervals = {}, values = {};
        Object[] datas = CGenUtil.rechercher(room, intervals, values, connection, where);
        return room instanceof Line? this.castLine(datas) : this.castRoom(datas);
    }

    public Line[] castLine(Object[] values) {
        Line[] rooms = new Line[values.length];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = (Line) values[i];
        }
        return rooms;
    }

    public Room[] castRoom(Object[] values) {
        Room[] rooms = new Room[values.length];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = (Room) values[i];
        }
        return rooms;
    }
}
