package itusolar.eltricSensor.stat.collector;

import java.time.LocalDate;

public class DateNotNormalException extends Exception {
    LocalDate start, end;
    public DateNotNormalException(LocalDate start, LocalDate end) {
        super("Date not normal exception");
        this.setStart(start);
        this.setEnd(end);
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
