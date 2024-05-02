package itusolar.prepare.schedule.predict;

import itusolar.prepare.schedule.ScheduleDetails;
import itusolar.simulation.SimulData;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ScheduleDetailsCompleted extends ScheduleDetails {
    Timestamp startDate,endDate;
    double interval;
    String titre;
    int userid;

    public ScheduleDetailsCompleted() {
        this.setNomTable("completedScheduleDetails");
    }
    public ScheduleDetailsCompleted(ScheduleDetailsCompleted source) {
        this();
        this.setStartDate(source.getStartDate());
        this.setEndDate(source.getEndDate());
        this.setInterval(source.getInterval());
        this.setTitre(source.getTitre());
        this.setUserid(source.getUserid());
        this.setNombre(source.getNombre());
        this.setMaterialid(source.getMaterialid());
        this.setSource(source.getSource());
        this.setLieuid(source.getLieuid());
        this.setScheduleid(source.getScheduleid());
        this.setDebut(source.getDebut());
        this.setFin(source.getFin());
    }

    public boolean between(Date start, Date end) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        LocalDate debutDate = this.getDebut().toLocalDateTime().toLocalDate();
        LocalDate finDate = this.getFin().toLocalDateTime().toLocalDate();
        return this.between(startDate,endDate,debutDate) || this.between(startDate,endDate, finDate);
    }

    public boolean between(LocalDate date1, LocalDate date2, LocalDate date3) {
        return (date3.isAfter(date1) && date3.isBefore(date2)) || date3.isEqual(date1) || date3.isEqual(date2);
    }

    public ScheduleDetailsCompleted[] expand() {
        LocalDate startDate = this.getStartDate().toLocalDateTime().toLocalDate();
        LocalDate endDate = this.getEndDate().toLocalDateTime().toLocalDate();
        Map<String,ScheduleDetailsCompleted> map = new HashMap<>();
        this.expandInf(startDate, map);
        this.expandSup(endDate, map);
        return this.expand(startDate,endDate,map);
    }

    public ScheduleDetailsCompleted[] expand(LocalDate startDate,LocalDate endDate,Map<String,ScheduleDetailsCompleted> results) {
        Vector<ScheduleDetailsCompleted> response = new Vector<>();
        results.forEach((keys,result) -> {
            LocalDate debutTemp = result.getDebut().toLocalDateTime().toLocalDate();
            LocalDate finTemp = result.getFin().toLocalDateTime().toLocalDate();
            boolean verif = this.lessOrEqual(startDate,debutTemp) ||
                    this.lessOrEqual(startDate,finTemp);
            verif &= (this.greaterOrEqual(endDate, debutTemp) || this.greaterOrEqual(endDate,finTemp));
            if (verif) {
                response.add(result);
            }
        });
        ScheduleDetailsCompleted[] answer = new ScheduleDetailsCompleted[response.size()];
        response.copyInto(answer);
        return answer;
    }

    public void expandSup( LocalDate endDate,Map<String,ScheduleDetailsCompleted> results) {
        LocalDate debutTemp = this.getDebut().toLocalDateTime().toLocalDate();
        LocalDate finTemp = this.getFin().toLocalDateTime().toLocalDate();
        while (this.greaterOrEqual(endDate, debutTemp) || this.greaterOrEqual(endDate, finTemp)) {
            ScheduleDetailsCompleted temp = new ScheduleDetailsCompleted(this);
            temp.config(debutTemp,finTemp);
            this.add(results,debutTemp,finTemp,temp);
            debutTemp = debutTemp.plusDays((int) this.getInterval());
            finTemp = finTemp.plusDays((int) this.getInterval());
        }
    }

    public void expandInf(LocalDate startDate,Map<String,ScheduleDetailsCompleted> results) {
        LocalDate debutTemp = this.getDebut().toLocalDateTime().toLocalDate();
        LocalDate finTemp = this.getFin().toLocalDateTime().toLocalDate();
        while (this.lessOrEqual(startDate,debutTemp) || this.lessOrEqual(startDate,finTemp)) {
            ScheduleDetailsCompleted temp = new ScheduleDetailsCompleted(this);
            temp.config(debutTemp,finTemp);
            this.add(results,debutTemp,finTemp,temp);
            debutTemp = debutTemp.minusDays((int) this.getInterval());
            finTemp = finTemp.minusDays((int) this.getInterval());
        }
    }

    public void add(Map<String, ScheduleDetailsCompleted> results,LocalDate debut,LocalDate fin,ScheduleDetailsCompleted detailsCompleted) {
        String keys = String.format("%s,%s", debut, fin);
        try {
            results.get(keys);
            results.put(keys,detailsCompleted);
        } catch (NullPointerException e) {
            System.out.println("Schedule Details Complete, expand : "+e.getMessage());
        }
    }

    public void config(LocalDate debut, LocalDate fin) {
        this.setDebut(this.toTimestamp(debut, this.getDebut()));
        this.setFin(this.toTimestamp(fin,this.getFin()));
    }

    public Timestamp toTimestamp(LocalDate date, Timestamp timestamp) {
        LocalDateTime temp = timestamp.toLocalDateTime();
        LocalTime time = temp.toLocalTime();
        temp = LocalDateTime.of(date,time);
        Instant instant = temp.atZone(ZoneId.of("UTC")).toInstant();
        return Timestamp.from(instant);
    }

    public boolean greaterOrEqual(LocalDate first, LocalDate second) {
        return first.isAfter(second) || first.equals(second);
    }

    public boolean lessOrEqual(LocalDate first,LocalDate second) {
        return first.isBefore(second) || first.equals(second);
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    public SimulData toSimulData() {
        SimulData data = new SimulData();
        data.setNb(this.getNombre());
        data.setDebut(this.getDebut());
        data.setFin(this.getFin());
        data.setChanged(true);
        data.setAppareilidValue(this.getMaterialid());
        data.setSourceValue(this.getSource());
        data.setLieux(this.getLieuid());
        return data;
    }
}
