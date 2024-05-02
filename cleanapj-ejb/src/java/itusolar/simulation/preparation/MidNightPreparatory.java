package itusolar.simulation.preparation;

import itusolar.simulation.SimulData;

import java.sql.Timestamp;
import java.util.Calendar;

public class MidNightPreparatory extends DatePreparator{

    public MidNightPreparatory(DataPreparatorSignature dataPreparator) {
        super(dataPreparator);
    }

    @Override
    public Timestamp getDebut(SimulData[] datas) {
        Timestamp response = super.getDebut(datas);
        Calendar cal = Calendar.getInstance();
        cal.setTime(response);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        response = new Timestamp(cal.getTimeInMillis());
        return response;
    }
}
