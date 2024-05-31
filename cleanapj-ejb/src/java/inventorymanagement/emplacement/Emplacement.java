package inventorymanagement.emplacement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Emplacement extends MappedInteger {
    String idemplacement;
    String iddepot;
    String codeemp;
    double capacite;

    public Emplacement() {
        setNomTable("emplacement");
    }
    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("emplacement");
        this.preparePk("EMP", "getseqemplacement");
        this.setIdemplacement(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idemplacement;
    }

    @Override
    public String getAttributIDName() {
        return "idemplacement";
    }

    public String getIdemplacement() {
        return idemplacement;
    }

    public void setIdemplacement(String idemplacement) {
        this.idemplacement = idemplacement;
    }

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getCodeemp() {
        return codeemp;
    }

    public void setCodeemp(String codeemp) {
        this.codeemp = codeemp;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
}
