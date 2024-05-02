package itusolar.historique.dashboard.evolution;

import java.sql.Date;

public class EvolutionParams {

    Date start, end;

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
