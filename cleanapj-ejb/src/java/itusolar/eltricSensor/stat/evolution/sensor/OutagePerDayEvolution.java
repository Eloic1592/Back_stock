package itusolar.eltricSensor.stat.evolution.sensor;

import itusolar.eltricSensor.log.ElectricLogMessage;
import itusolar.eltricSensor.stat.collector.CollectedDatas;
import itusolar.eltricSensor.stat.collector.MultipleCollectorSignature;
import itusolar.eltricSensor.stat.evolution.*;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OutagePerDayEvolution extends HServiceManager implements OutageEvolutionSignature {
    MultipleCollectorSignature collector;

    public OutagePerDayEvolution(MultipleCollectorSignature collector) {
        this.setCollector(collector);
    }

    @Override
    public OutagePoint[] evolution(LocalDate start, LocalDate end, Connection connection) throws Exception {
        CollectedDatas datas = this.getCollector().collect(start, end, connection);
        return this.evolution(datas);
    }

    @Override
    public OutagePoint[] evolution(Timestamp start, Timestamp end, Connection connection) throws Exception {
        LocalDate startDate = start.toLocalDateTime().toLocalDate();
        LocalDate endDate = end.toLocalDateTime().toLocalDate();
        return this.evolution(startDate, endDate, connection);
    }

    @Override
    public OutagePoint[] evolution(CollectedDatas datas) throws Exception {
        Map<String, OutagePoint> evolution = new HashMap<>();
        for (ElectricLogMessage message : datas.getMessages()) {
            this.evaluate(message, evolution);
        }
        OutagePoint[] results = new OutagePoint[evolution.size()];
        int[] index = {0};
        evolution.forEach((key, value) -> {
            results[index[0]] = value;
            index[0]++;
        });
        return results;
    }

    @Override
    public OutagePage evolutionPage(Timestamp start, Timestamp end, Connection connection) throws Exception {
        return new OutagePage(this.evolution(start, end, connection));
    }

    @Override
    public OutagePage evolutionPage(OutageParams params, Connection connection) throws Exception {
        CollectedDatas datas = this.getCollector().collect(params.getStart().toLocalDate(), params.getEnd().toLocalDate(),
                connection);
        OutagePage page = new OutagePage(this.evolution(datas));
        page.setSensors(datas.getSensors());
        return page;
    }

    public void evaluate(ElectricLogMessage message, Map<String, OutagePoint> evolution) throws NegativeValueException {
        String key = this.getKey(message);
        OutagePoint point = evolution.get(key);
        if (point == null) {
            point = new OutagePoint(message.getSensor(), message.toDate(), 1);
            evolution.put(key, point);
            return;
        }
        point.add(1);
    }

    public String getKey(ElectricLogMessage message) {
        return message.getDayKey();
    }

    public MultipleCollectorSignature getCollector() {
        return collector;
    }

    public void setCollector(MultipleCollectorSignature collector) {
        this.collector = collector;
    }
}
