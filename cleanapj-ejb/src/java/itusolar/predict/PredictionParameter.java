package itusolar.predict;

import itusolar.simulation.SimulData;
import itusolar.simulation.response.ScheduleRenderingSignature;

import java.sql.Date;

public class PredictionParameter {
    Date start;
    Date end;
    String id;
    SimulData[] datas;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public SimulData[] getDatas() {
        return datas;
    }

    public void setDatas(SimulData[] datas) {
        this.datas = datas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
