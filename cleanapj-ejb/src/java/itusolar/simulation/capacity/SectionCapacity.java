package itusolar.simulation.capacity;

import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class SectionCapacity extends MappedInteger {
    int sectionid;
    double capacite;
    String sectiontitle;

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqBatteryCapacity");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }
}
