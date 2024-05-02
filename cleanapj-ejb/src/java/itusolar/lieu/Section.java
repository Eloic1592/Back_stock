package itusolar.lieu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.UtilsSignature;

import java.sql.Connection;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Section extends MappedInteger implements UtilsSignature {
    String titre;

    public Section() {
        this.setNomTable("section");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqSection");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String getLabel() {
        return this.getTitre();
    }
}
