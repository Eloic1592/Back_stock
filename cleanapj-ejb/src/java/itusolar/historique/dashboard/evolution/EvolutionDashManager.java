package itusolar.historique.dashboard.evolution;

import itusolar.predict.PredictionParameter;
import itusolar.predict.loader.Loader;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.HServiceManager;
import itusolar.prepare.schedule.predict.SchedulePreparatorySignature;
import itusolar.simulation.SimulData;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class EvolutionDashManager extends HServiceManager implements EvolutionDashManagerSignature{
    SchedulePreparatorySignature schedulePreparatory;
    LoaderSignature loader;

    public EvolutionDashManager(SchedulePreparatorySignature schedulePreparatory, LoaderSignature loader) {
        this.schedulePreparatory = schedulePreparatory;
        this.loader = loader;
    }

    @Override
    public EvolutionDash dashContent(Date start, Date end, Connection connection) throws Exception {
//        connection = this.getConnection(connection);
//        PredictionParameter parameter = this.schedulePreparatory.prepare(start, end, connection);
//        this.loader.loadAppareilAndSection(parameter.getDatas(), connection);
//        SimulData[] datas = this.expand(parameter.getDatas());
//        return new EvolutionDash(this.toEvolutionState(datas));
        return null;
    }

    @Override
    public EvolutionDash dashContent(EvolutionParams params,Connection connection) throws Exception {
        return this.dashContent(params.getStart(),params.getEnd(), connection);
    }

    public EvolutionState[] toEvolutionState(SimulData[] data) {
        HashMap<String,EvolutionState> response = new HashMap<>();
        for (SimulData datum : data) {
            String key = datum.getDebut().getDate() + "-" + datum.getSourceValue();
            EvolutionState element = response.get(key);
            if (element != null) {
                element.setValue(element.getValue() + datum.getConsommation());
            } else {
                element = new EvolutionState();
                element.setIndex(""+datum.getDebut().getDate());
                element.setOrder(datum.getDebut().getDate());
                element.setValue(datum.getConsommation());
                element.setType(datum.getSourceValue());
                element.setSection(datum.getSection());
                response.put(key, element);
            }
        }
        EvolutionState[] results = new EvolutionState[response.size()];
        int[] index = {0};
        response.forEach((key,value) -> {
            results[index[0]] = value;
            index[0]++;
        });
        return results;
    }

    public SimulData[] expand(SimulData[] datas) {
        Vector<SimulData> result = new Vector<>();
        for (SimulData data : datas) {
            result.addAll(Arrays.asList(this.expand(data)));
        }
        SimulData[] resultArray = new SimulData[result.size()];
        result.copyInto(resultArray);
        return resultArray;
    }

    public SimulData[] expand(SimulData data) {
        int count = this.count(data);
        SimulData[] results = new SimulData[count];
        Timestamp start = data.getDebut();
        Timestamp end = data.getFin();
        for (int i = 0; i < count; i++) {
            results[i] = new SimulData(data);
            results[i].setDebut(start);
            start = this.toStart(start);
            end = this.toLast(end);
            if (i == count-1) {
                results[i].setFin(data.getFin());
            } else {
                results[i].setFin(end);
            }
            results[i].evalConsommation(-1);
        }
        return results;
    }

    public Timestamp toStart(Timestamp timestamp) {
        LocalDateTime result = timestamp.toLocalDateTime();
        result = result.plusDays(1);
        result = LocalDateTime.of(result.toLocalDate(), LocalTime.of(0,0));
        return Timestamp.valueOf(result);
    }

    public Timestamp toLast(Timestamp timestamp) {
        LocalDateTime result = timestamp.toLocalDateTime();
        result = LocalDateTime.of(result.toLocalDate(), LocalTime.of(23,59));
        return Timestamp.valueOf(result);
    }

    // Count le nombre de jour entre le debut et la fin
    public int count(SimulData data) {
        LocalDate first = data.getDebut().toLocalDateTime().toLocalDate();
        LocalDate second = data.getFin().toLocalDateTime().toLocalDate();
        return Period.between(second, first).getDays()+1;
    }
}
