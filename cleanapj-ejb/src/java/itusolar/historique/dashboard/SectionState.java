package itusolar.historique.dashboard;

import itusolar.prepare.MappedInteger;

import java.sql.Time;
import java.time.LocalTime;

public class SectionState extends MappedInteger implements SectionStateResult{
    int sectionid;
    double puissanceentree, puissancesortie, puissancebatt;
    String section;
    double capacite;

    public SectionState() {
        this.setNomTable("sectionState");
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public double getPuissanceentree() {
        return puissanceentree;
    }

    public void setPuissanceentree(double puissanceentree) {
        this.puissanceentree = puissanceentree;
    }

    public double getPuissancesortie() {
        return puissancesortie;
    }

    public void setPuissancesortie(double puissancesortie) {
        this.puissancesortie = puissancesortie;
    }

    public double getPuissancebatt() {
        return puissancebatt;
    }

    public void setPuissancebatt(double puissancebatt) {
        this.puissancebatt = puissancebatt;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String getOutput() {
        return this.getPuissancesortie()+ "";
    }

    @Override
    public String getInput() {
        return this.getPuissanceentree()+ "";
    }

    @Override
    public String getBatteryState() {
        return this.getPuissancebatt()+ "";
    }

    @Override
    public String getTime() {
        if (this.getPuissancesortie() > this.getPuissanceentree()) {
            int dayMinutes = 1440, millisDuration = 60000;
            double vitesse = this.getPuissancesortie()-this.getPuissanceentree();
            vitesse /= 60.;
            double duration = this.getPuissancebatt() / vitesse;
            int dayPassed = (int) duration / dayMinutes;
            duration %= dayMinutes;
            long durMinus = ((long)duration) * millisDuration;
            LocalTime time = new Time(durMinus).toLocalTime();
            String response = dayPassed > 0 ? dayPassed+"j " : "";
            return response+String.format("%s:%s", time.getHour(), time.getMinute());
        }
        return "-";
    }
}
