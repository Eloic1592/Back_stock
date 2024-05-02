package itusolar.historique.dashboard;

import itusolar.historique.dashboard.SectionState;
import itusolar.simulation.DateConcern;

public class DashBoard implements SectionStateResult{
    SectionStateResult[] sectionStates = {};
    DateConcern meteo;

    public DashBoard(SectionStateResult[] sectionStates, DateConcern dateConcern) {
        this.sectionStates = sectionStates;
        this.setMeteo(dateConcern);
    }

    public double battery() {
        double result = 0.;
        for (SectionStateResult sectionState : sectionStates) {
            result += ((SectionState)sectionState).getPuissancebatt();
        }
        return result;
    }

    public String getOutput() {
        return ""+this.output();
    }

    public double output() {
        double result = 0.;
        for (SectionStateResult section : this.getSectionStates())
            result += ((SectionState)section).getPuissancesortie();
        return result;
    }

    public String getInput() {
        return ""+this.input();
    }

    @Override
    public String getBatteryState() {
        return ""+this.battery();
    }

    @Override
    public String getTime() {
        return null;
    }

    public double input() {
        double result = 0.;
        for (SectionStateResult section : this.getSectionStates())
            result += ((SectionState)section).getPuissanceentree();
        return result;
    }

    public SectionStateResult[] getSectionStates() {
        return sectionStates;
    }

    public void setSectionStates(SectionStateResult[] sectionStates) {
        this.sectionStates = sectionStates;
    }

    public DateConcern getMeteo() {
        return meteo;
    }

    public void setMeteo(DateConcern meteo) {
        this.meteo = meteo;
    }
}
