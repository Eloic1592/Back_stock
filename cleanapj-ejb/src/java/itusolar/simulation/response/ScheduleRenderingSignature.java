package itusolar.simulation.response;

import java.sql.Timestamp;

public interface ScheduleRenderingSignature {
    public String getTitle();
    public Timestamp getStart();
    public Timestamp getEnd();
    public String getBackgroundColor();
    public int getState();
}
