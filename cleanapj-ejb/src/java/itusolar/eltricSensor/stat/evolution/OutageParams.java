package itusolar.eltricSensor.stat.evolution;


import java.sql.Date;

public class OutageParams {
    Date start, end;

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
}
