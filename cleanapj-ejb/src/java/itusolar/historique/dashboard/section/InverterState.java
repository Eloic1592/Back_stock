package itusolar.historique.dashboard.section;

import itusolar.historique.dashboard.SectionStateResult;
import itusolar.prepare.MappedInteger;

public class InverterState extends MappedInteger implements SectionStateResult {
    int sectionid;
    String section;
    double puissanceentree, puissancesortie, puissancebatt, capacite;

    public InverterState() {
        this.setNomTable("histsection");
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    @Override
    public String getOutput() {
        return ""+this.getPuissancesortie();
    }

    @Override
    public String getInput() {
        return ""+this.getPuissanceentree();
    }

    @Override
    public String getBatteryState() {
        return ""+this.getPuissancebatt();
    }

    @Override
    public String getTime() {
        return "";
    }
}
