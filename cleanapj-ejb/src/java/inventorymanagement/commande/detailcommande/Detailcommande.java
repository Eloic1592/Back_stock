package inventorymanagement.commande.detailcommande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class Detailcommande extends MappedInteger {
    String iddetailcommande;
    String idcommande;
    String idarticle;
    double quantite;
    double pu;
    double total;
    String description;

    public Detailcommande() {
        super.setNomTable("detailcommande");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("detailcommande");
        this.preparePk("DTC", "getseqdetailcommande");
        this.setIddetailcommande(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return iddetailcommande;
    }

    @Override
    public String getAttributIDName() {
        return "iddetailcommande";
    }

    public String getIddetailcommande() {
        return iddetailcommande;
    }

    public void setIddetailcommande(String iddetailcommande) {
        this.iddetailcommande = iddetailcommande;
    }

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
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
