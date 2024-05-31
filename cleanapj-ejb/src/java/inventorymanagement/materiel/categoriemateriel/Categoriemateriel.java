package inventorymanagement.materiel.categoriemateriel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Categoriemateriel extends MappedInteger {
    String idcategoriemateriel;
    String categoriemateriel;
    String val;

    public Categoriemateriel() {
        super.setNomTable("categoriemateriel");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("categoriemateriel");
        this.preparePk("TYPE", "getseqtypemateriel");
        this.setIdcategoriemateriel(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return idcategoriemateriel;
    }

    @Override
    public String getAttributIDName() {
        return "idcategoriemateriel";
    }

    public String getIdcategoriemateriel() {
        return idcategoriemateriel;
    }

    public void setIdcategoriemateriel(String idcategoriemateriel) {
        this.idcategoriemateriel = idcategoriemateriel;
    }

    public String getCategoriemateriel() {
        return categoriemateriel;
    }

    public void setCategoriemateriel(String categoriemateriel) {
        this.categoriemateriel = categoriemateriel;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
