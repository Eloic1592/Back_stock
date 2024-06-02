package inventorymanagement.depot;


import bean.CGenUtil;
import bean.ClassMAPTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Depot extends MappedInteger {
    String iddepot;
    String  depot;
    String codedep;
    double capacite;
    String codebarre;

    public Depot() {
        super.setNomTable("depot");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("depot");
        this.preparePk("DEP", "getseqdepot");
        this.setIddepot(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return iddepot;
    }

    @Override
    public String getAttributIDName() {
        return "iddepot";
    }

    public String getIddepot() {
        return iddepot;
    }
    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getCodedep() {
        return codedep;
    }

    public void setCodedep(String codedep) {
        this.codedep = codedep;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }
}
