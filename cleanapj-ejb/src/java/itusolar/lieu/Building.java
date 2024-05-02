package itusolar.lieu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.UtilsSignature;

import java.sql.Connection;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Building extends MappedInteger implements UtilsSignature {
    String titre;
    int sectionid;
    public Building() {
        this.setNomTable("lieu");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqLieu");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    @Override
    public String getLabel() {
        return this.getTitre();
    }
}
