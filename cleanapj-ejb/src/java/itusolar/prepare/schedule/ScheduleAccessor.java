package itusolar.prepare.schedule;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class ScheduleAccessor extends HServiceManager implements ScheduleAccessorSignature {
    @Override
    public void active(Schedule schedule, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Schedule[] schedules = this.getById(schedule, connection);
        if (schedules != null && schedules.length > 0) {
            schedule = schedules[0];
            schedule.setState(Schedule.ACTIVE);
            schedule.updateToTable(connection);
        } else {
            throw new Exception("No matched schedule found");
        }
    }

    @Override
    public itusolar.prepare.schedule.ScheduleList getListContents(Connection connection) throws Exception {
        Schedule[] results = this.getAll(connection);
        return new ScheduleList(results);
    }

    @Override
    public Schedule[] getAll(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return this.search("", connection);
    }

    @Override
    public Schedule[] search(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Schedule schedule = new Schedule();
        String[] columns = {};
        String[] values = {};
        Object[] datas = CGenUtil.rechercher(schedule, columns, values, connection, where);
        return this.cast(datas);
    }

    @Override
    public Schedule[] getById(Schedule schedule, Connection connection) throws Exception {
        String where = " and id = "+schedule.getId();
        Schedule[] results = this.search(where, connection);
        if (results.length > 0) {
            where = " and scheduleid = "+results[0].getId();
            ScheduleDetails[] details = this.searchDetails(where, connection);
            results[0].setDetails(details);
        }
        return results;
    }



    @Override
    public void create(Schedule schedule, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (schedule.getId() > 0) {
            schedule.updateToTable(connection);
            return;
        }
        schedule.construirePK(connection);
        CGenUtil.save(schedule, connection);
    }

    @Override
    public ScheduleDetails[] getDetails(Schedule schedule, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return this.searchDetails("", connection);
    }

    @Override
    public ScheduleDetails[] searchDetails(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        ScheduleDetails details = new ScheduleDetails();
        String[] columns = {}, values = {};
        Object[] results = CGenUtil.rechercher(details, columns, values, connection, where);
        return this.castDetails(results);
    }

    @Override
    public void create(ScheduleCreationParams params, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        this.create(params.getSchedule(),connection);
        for (ScheduleDetails detail : params.getDetails()) {
            detail.setScheduleid(params.getSchedule().getId());
            if (detail.getId() > 0) {
                detail.updateToTable(connection);
                continue;
            }
            detail.construirePK(connection);
            CGenUtil.save(detail, connection);
        }
    }

    public ScheduleDetails[] castDetails(Object[] datas) {
        ScheduleDetails[] responses = new ScheduleDetails[datas.length];
        for (int i = 0; i < datas.length; i++) {
            responses[i] = (ScheduleDetails) datas[i];
        }
        return responses;
    }

    public Schedule[] cast(Object[] datas) {
        Schedule[] result = new Schedule[datas.length];
        for (int i = 0; i < datas.length; i++) {
            result[i] = (Schedule) datas[i];
        }
        return result;
    }
}
