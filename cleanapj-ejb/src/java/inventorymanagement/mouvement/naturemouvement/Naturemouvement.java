package inventorymanagement.mouvement.naturemouvement;

import bean.CGenUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import inventorymanagement.bon.livraison.Clientlivraison;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Naturemouvement extends MappedInteger {

    String idnaturemouvement;
    String naturemouvement;

    public Naturemouvement() {
        super.setNomTable("naturemouvement");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("naturemouvement");
        this.preparePk("NATMOUV", "getseqnaturemouvement");
        this.setIdnaturemouvement(makePK(c));
    }


    @Override
    public String getTuppleID() {
        return idnaturemouvement;
    }

    @Override
    public String getAttributIDName() {
        return "idnaturemouvement";
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }

}
