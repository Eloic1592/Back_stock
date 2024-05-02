package inventorymanagement.devis.detaildevis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class Detaildevis extends MappedInteger {
    String iddetaildevis;
    String iddevis;
    String idarticle;
    double quantite;
    double pu;
    double total;
    String description;

    public Detaildevis() {
        super.setNomTable("detaildevis");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("detaildevis");
        this.preparePk("DETDEV", "getseqdetaildevis");
        this.setIddetaildevis(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return iddetaildevis;
    }

    @Override
    public String getAttributIDName() {
        return "iddetaildevis";
    }


    public String getIddetaildevis() {
        return iddetaildevis;
    }

    public void setIddetaildevis(String iddetaildevis) {
        this.iddetaildevis = iddetaildevis;
    }

    public String getIddevis() {
        return iddevis;
    }

    public void setIddevis(String iddevis) {
        this.iddevis = iddevis;
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
