package inventorymanagement.materiel.typemateriel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Typemateriel extends MappedInteger {
    String idtypemateriel;
    String typemateriel;
    String idcategoriemateriel;
    String val;

    public Typemateriel() {
        super.setNomTable("typemateriel");
    }


    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("typemateriel");
        this.preparePk("TYPE", "getseqtypemateriel");
        this.setIdtypemateriel(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return idtypemateriel;
    }

    @Override
    public String getAttributIDName() {
        return "idtypemateriel";
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public String getIdcategoriemateriel() {
        return idcategoriemateriel;
    }

    public void setIdcategoriemateriel(String idcategoriemateriel) {
        this.idcategoriemateriel = idcategoriemateriel;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
