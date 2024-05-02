package itusolar.prepare.schedule.predict;

import bean.CGenUtil;
import itusolar.predict.PredictionParameter;
import itusolar.prepare.HServiceManager;
import itusolar.prepare.schedule.ScheduleAccessorSignature;

import java.sql.Connection;
import java.sql.Date;

public class ScheduleDateFilter extends HServiceManager implements ScheduleDateFilterSignature {
    ScheduleAccessorSignature scheduleAccessor;

    public ScheduleDateFilter(ScheduleAccessorSignature scheduleAccessor) {
        this.setScheduleAccessor(scheduleAccessor);
    }

    @Override
    public itusolar.prepare.schedule.predict.ScheduleDetailsCompleted[] search(String where, Connection connection) throws Exception {
        itusolar.prepare.schedule.predict.ScheduleDetailsCompleted details = new itusolar.prepare.schedule.predict.ScheduleDetailsCompleted();
        String[] columns = {}, values = {};
        Object[] datas = CGenUtil.rechercher(details,columns,values,connection,where);
        return this.cast(datas);
    }

    public itusolar.prepare.schedule.predict.ScheduleDetailsCompleted[] cast(Object[] datas) {
        itusolar.prepare.schedule.predict.ScheduleDetailsCompleted[] result = new itusolar.prepare.schedule.predict.ScheduleDetailsCompleted[datas.length];
        for (int i = 0; i < datas.length; i++) {
            result[i] = (itusolar.prepare.schedule.predict.ScheduleDetailsCompleted) datas[i];
        }
        return result;
    }

    @Override
    public ScheduleDetailsCompleted[] search(PredictionParameter parameter, Connection connection) throws Exception {
        String where = this.prepareWhere(parameter);
        return this.search(where, connection);
    }

    public String prepareWhere(PredictionParameter parameter) throws Exception {
        if (parameter.getStart() == null || parameter.getEnd() == null)
            throw new Exception("start and end must not be null");
        String result = " and ((startdate<=%s and %s<=ENDDATE) or (startdate<=%s and %s<=ENDDATE) or (%s<=startdate and startdate<=%s) or (%s<=ENDDATE and ENDDATE<=%s))";
        result += " and SCHEDULEID != %s";
        String startValue = this.toDate(parameter.getStart());
        String endValue = this.toDate(parameter.getEnd());
        result = String.format(result, startValue, startValue,endValue,endValue, startValue,endValue, startValue,endValue, parameter.getId());
        return result;
    }

    public ScheduleAccessorSignature getScheduleAccessor() {
        return scheduleAccessor;
    }

    public void setScheduleAccessor(ScheduleAccessorSignature scheduleAccessor) {
        this.scheduleAccessor = scheduleAccessor;
    }
}
