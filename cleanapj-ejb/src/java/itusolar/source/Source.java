package itusolar.source;

import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class Source extends MappedInteger {
    String titre;
    int typeid;
    int sectionid;

    public Source() {
        this.setNomTable("energysource");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeq_Source");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }
}
