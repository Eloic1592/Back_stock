package itusolar.predict.etatconsommation;

import com.fasterxml.jackson.annotation.JsonFormat;
import itusolar.simulation.response.ScheduleRenderingSignature;

import javax.ejb.Local;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class ConsumptionState implements ScheduleRenderingSignature {

    public static int DEFICIT = 0;
    public static int EXCES = 50;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp debut,fin;
    int type;

    @Override
    public String getTitle() {
        return "";
//        return this.getEtatLabel();
    }

    @Override
    public Timestamp getStart() {
        return this.getDebut();
    }

    @Override
    public Timestamp getEnd() {
        return this.getFin();
    }

    int section;

    double conso,support;

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public abstract double getValue();

    public double getConso() {
        return conso;
    }

    public void setConso(double conso) {
        this.conso = conso;
    }

    public double getSupport() {
        return support;
    }

    public void setSupport(double support) {
        this.support = support;
    }

    public abstract String getEtatLabel();
    public abstract int getEtat();

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public boolean isContinue(ConsumptionState state) {
        LocalDateTime endDate = this.getFin().toLocalDateTime();
        LocalDateTime startDate = state.getStart().toLocalDateTime();
        boolean verif = endDate.toLocalDate().equals(startDate.toLocalDate());
        verif &= endDate.getHour()== startDate.getHour();
        verif &= endDate.getMinute()== startDate.getMinute();
        return verif;
    }

    @Override
    public String toString() {
        return "ConsumptionState{" +
                "debut=" + debut +
                ", fin=" + fin +
                ", type=" + type +
                ", section=" + section +
                ", conso=" + conso +
                ", support=" + support +
                '}';
    }
}
