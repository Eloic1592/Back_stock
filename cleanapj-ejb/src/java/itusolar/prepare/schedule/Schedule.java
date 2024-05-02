package itusolar.prepare.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.SimulData;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule extends MappedInteger {
    public static int ACTIVE = 50;
    public static int CLOSED = 0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp startdate,enddate;
    double interval;
    String titre;
    int userid,state;

    itusolar.prepare.schedule.ScheduleDetails[] details;
    SimulData[] datas = null;

    public boolean isActive() {
        return this.getState() >= Schedule.ACTIVE;
    }

    public itusolar.prepare.schedule.ScheduleDetails[] getDetails() {
        return details;
    }

    public void setDetails(ScheduleDetails[] details) {
        this.details = details;
    }

    public Schedule() {
        this.setNomTable("schedule");
        this.setState(Schedule.CLOSED);
    }

    public SimulData[] getDatas() {
        if (this.datas == null) {
            if (!(this.getDetails() != null && this.getDetails().length > 0))
                return new SimulData[0];
            SimulData[] datas = new SimulData[details.length];
            for (int i = 0; i < datas.length; i++) {
                datas[i] = this.getDetails()[i].toSimulData();
            }
            this.datas = datas;
        }
        return this.datas;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqSchedule");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
