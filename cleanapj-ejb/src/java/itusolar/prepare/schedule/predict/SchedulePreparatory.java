package itusolar.prepare.schedule.predict;

import itusolar.predict.PredictionParameter;
import itusolar.prepare.HServiceManager;
import itusolar.simulation.SimulData;

import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;

public class SchedulePreparatory extends HServiceManager implements SchedulePreparatorySignature {
    itusolar.prepare.schedule.predict.ScheduleDateFilterSignature filter;

    public SchedulePreparatory(ScheduleDateFilterSignature filter) {
        this.filter = filter;
    }

    @Override
    public PredictionParameter prepare(PredictionParameter params, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        ScheduleDetailsCompleted[] concerns = this.filter.search(params, connection);
        return this.toParameters(params.getStart(), params.getEnd(), concerns, connection);
    }

    public PredictionParameter toParameters(Date start,Date end,ScheduleDetailsCompleted[] datas,Connection connection) throws Exception {
        SimulData[] results = this.toSimulData(start,end,datas,connection);
        PredictionParameter parameter = new PredictionParameter();
        parameter.setDatas(results);
        return parameter;
    }

    public SimulData[] toSimulData(Date start,Date end,ScheduleDetailsCompleted[] datas,Connection connection) throws Exception {
        Vector<SimulData> results = new Vector<>();
        for (ScheduleDetailsCompleted completed : datas) {
            this.add(start,end,completed,results);
        }
        SimulData[] responses = new SimulData[results.size()];
        results.copyInto(responses);
        return responses;
    }

    public void add(Date start, Date end, ScheduleDetailsCompleted data, Vector<SimulData> results) {
        ScheduleDetailsCompleted[] responses = data.expand();
        for (ScheduleDetailsCompleted response : responses) {
            if (response.between(start, end))
                results.add(response.toSimulData());
        }
    }
}
