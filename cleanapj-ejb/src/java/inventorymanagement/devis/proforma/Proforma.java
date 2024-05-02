package inventorymanagement.devis.proforma;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Proforma extends MappedInteger {
    String idproforma;
    String iddevis;
    Timestamp datevalidation;

    public Proforma() {
        setNomTable("proforma");
    }
    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("proforma");
        this.preparePk("PROF", "getseqproforma");
        this.setIdproforma(makePK(c));

    }

    public String getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(String idproforma) {
        this.idproforma = idproforma;
    }

    public String getIddevis() {
        return iddevis;
    }

    public void setIddevis(String iddevis) {
        this.iddevis = iddevis;
    }

    public Timestamp getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(Timestamp datevalidation) {
        this.datevalidation = datevalidation;
    }
}
