package itusolar.simulation.preparation;

import itusolar.predict.PredictionParameter;
import itusolar.simulation.DateConcern;
import itusolar.simulation.SimulData;

import java.sql.Timestamp;

public class DatePreparator implements DatePreparatorSignature{
    DataPreparatorSignature dataPreparator;

    public DatePreparator(DataPreparatorSignature dataPreparator) {
        this.dataPreparator = dataPreparator;
    }

    @Override
    public DateConcern[] filterConcern(PredictionParameter data) {
        SimulData[] datas = data.getDatas();
        if (!(datas != null && datas.length > 0)) return new DateConcern[0];
        datas = this.dataPreparator.sort(datas);
        Timestamp[] intervals = this.dataPreparator.splitHours(this.getDebut(datas),this.getFin(datas));
        DateConcern[] dates = new DateConcern[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            dates[i] = new DateConcern(intervals[i]);
        }
        return dates;
    }

    public Timestamp getFin(SimulData[] datas) {
        return datas[datas.length - 1].getFin();
    }

    public Timestamp getDebut(SimulData[] datas) {
        return datas[0].getDebut();
    }
}
