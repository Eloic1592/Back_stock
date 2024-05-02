package itusolar.prepare.schedule;

import java.sql.Connection;

public interface ScheduleAccessorSignature {
    public void active(Schedule schedule, Connection connection) throws Exception;
    public ScheduleList getListContents(Connection connection) throws Exception;
    public Schedule[] getAll(Connection connection) throws Exception;
    public Schedule[] search(String where, Connection connection) throws Exception;
    public Schedule[] getById(Schedule schedule, Connection connection) throws Exception;
    public void create(Schedule schedule, Connection connection) throws Exception;
    public ScheduleDetails[] getDetails(Schedule schedule,Connection connection) throws Exception;
    public ScheduleDetails[] searchDetails(String where, Connection connection) throws Exception;
    public void create(ScheduleCreationParams params, Connection connection) throws Exception;
}
