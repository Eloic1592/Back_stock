package itusolar.historique.type;

import itusolar.prepare.MappedInteger;
import itusolar.simulation.UtilsSignature;

import java.sql.Connection;

public class Type extends MappedInteger implements UtilsSignature {
    String titre;

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("id", "GETSEQType");
        this.setId(Integer.valueOf(makePK(c)));
    }
    @Override
    public String getNomTable() {
        return "type";
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
