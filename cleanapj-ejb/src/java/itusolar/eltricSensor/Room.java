package itusolar.eltricSensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.historique.wait.ElectricWaitState;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.UtilsSignature;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Room extends MappedInteger implements Cloneable, UtilsSignature {
    String title;
    Line[] lines;
    @JsonIgnore
    Map<Integer, Line> lineMap = new HashMap<>();
    public Room() {
        this.setNomTable("room");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqRoom");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public int count() {
        int result = 0;
        for (Line line : this.getLines()) {
            for (ElectricSensor sensor : line.getSensors()) {
                if (!((ElectricWaitState) sensor).isOpen())
                    result++;
            }
        }
        return result;
    }

    public Line[] getLines() {
        return lines;
    }

    public void setLines(Line[] lines) {
        this.lines = lines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Integer, Line> getLineMap() {
        return lineMap;
    }

    public void setLineMap(Map<Integer, Line> lineMap) {
        this.lineMap = lineMap;
    }

    public void add(ElectricState state, Map<Integer, Line> lines) throws CloneNotSupportedException {
        Line line = this.lineMap.get(state.getLineId());
        if (line == null) {
            line = (Line) lines.get(state.getLineId()).clone();
            this.lineMap.put(line.getId(), line);
        }
        line.add(state, lines);
    }

    public void load() {
        this.setLines(new Line[this.lineMap.size()]);
        int[] index = {0};
        this.lineMap.forEach((key, value) -> {
            value.load();
            this.getLines()[index[0]] = value;
            index[0]++;
        });
    }

    @Override
    public Room clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Room) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "title='" + title + '\'' +
                ", lines=" + Arrays.toString(lines) +
                ", lineMap=" + lineMap +
                '}';
    }

    @Override
    public String getLabel() {
        return this.getTitle();
    }
}
