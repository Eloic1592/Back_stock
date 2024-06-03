package inventorymanagement.stockage.distribution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Distribution extends MappedInteger {
    String iddistribution;
    String idarticle;
    double quantite;
    Timestamp datedistribution;
    String iddepot;
    String idemplacement;
    int statut;
    int etatdistribue;

    public Distribution() {
        this.setNomTable("distribution");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("distribution");
        this.preparePk("DIS", "getseqdistribution");
        this.setIddistribution(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return iddistribution;
    }

    @Override
    public String getAttributIDName() {
        return "iddistribution";
    }

    public String getIddistribution() {
        return iddistribution;
    }

    public void setIddistribution(String iddistribution) {
        this.iddistribution = iddistribution;
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

    public Timestamp getDatedistribution() {
        return datedistribution;
    }

    public void setDatedistribution(Timestamp datedistribution) {
        this.datedistribution = datedistribution;
    }

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getIdemplacement() {
        return idemplacement;
    }

    public void setIdemplacement(String idemplacement) {
        this.idemplacement = idemplacement;
    }

    public int getEtatdistribue() {
        return etatdistribue;
    }

    public void setEtatdistribue(int etatdistribue) {
        this.etatdistribue = etatdistribue;
    }
}
