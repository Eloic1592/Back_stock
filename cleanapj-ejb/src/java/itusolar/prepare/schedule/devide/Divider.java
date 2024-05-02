package itusolar.prepare.schedule.devide;

import bean.CGenUtil;
import itusolar.prepare.schedule.Schedule;
import itusolar.prepare.schedule.ScheduleAccessor;
import itusolar.prepare.schedule.ScheduleDetails;

import java.sql.Connection;
import java.sql.Timestamp;

public class Divider extends ScheduleAccessor implements DividerSignature {
    @Override
    public void divide( DividerParams params, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Schedule schedule = params.getSchedule();
        Schedule[] schedules = this.getById(schedule, connection);
        if (schedules.length > 0) {
            schedule = schedules[0];
            if (!(schedule.getStartdate().before(params.getDate()) && schedule.getEnddate().after(params.getDate()))) {
                throw new Exception("Date not normal");
            }
            Timestamp last = schedule.getEnddate();
            schedule.setEnddate(params.getDate());
            schedule.updateToTable(connection);
            schedule.setStartdate(params.getDate());
            schedule.setTitre(schedule.getTitre()+ " (Bis)");
            schedule.setEnddate(last);
            schedule.setId(0);
            this.create(schedule, connection);
            for (ScheduleDetails detail : schedule.getDetails()) {
                detail.setScheduleid(schedule.getId());
                detail.construirePK(connection);
                CGenUtil.save(detail, connection);
            }
        } else {
            throw new Exception("Unmatched schedule id");
        }
    }
}
