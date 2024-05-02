package itusolar.prepare.schedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.simulation.SimulData;
import itusolar.simulation.filter.DataIgnoreSignature;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleCreationParams {
    Schedule schedule;
    SimulData[] details;
    @JsonIgnore
    DataIgnoreSignature dataIgnore;


    public ScheduleDetails[] getDetails() {
        SimulData[] datas = this.dataIgnore.removeNotChanged(this.details);
        ScheduleDetails[] results = new ScheduleDetails[datas.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = datas[i].toScheduleDetails();
        }
        return results;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public void setDetails(SimulData[] details) {
        this.details = details;
    }

    public DataIgnoreSignature getDataIgnore() {
        return dataIgnore;
    }

    public void setDataIgnore(DataIgnoreSignature dataIgnore) {
        this.dataIgnore = dataIgnore;
    }
}
