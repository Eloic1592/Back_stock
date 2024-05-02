package inventorymanagement.facture.facture;


import com.fasterxml.jackson.annotation.JsonIgnore;
import inventorymanagement.facture.detailfacture.Detailsfacturemateriel;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Facturemateriel extends MappedInteger {
    String idfacturemateriel;
    Timestamp datefacture;
    String idclient;
    String iddetailmouvementphysique;
    int statut;
    Detailsfacturemateriel[] detailFactures;
    double total;


    public Facturemateriel() {
        super.setNomTable("facturemateriel");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("facturemateriel");
        this.preparePk("FACT", "getseqfacturemateriel");
        this.setIdfacturemateriel(makePK(c));

    }

    public double getotalfacture() {
        double result = 0;
        for (Detailsfacturemateriel detail : this.getDetailFactures()) {
            result += detail.getTotal();
        }
        return result;
    }


    public String getIdfacturemateriel() {
        return idfacturemateriel;
    }

    public void setIdfacturemateriel(String idfacturemateriel) {
        this.idfacturemateriel = idfacturemateriel;
    }

    public Timestamp getDatefacture() {
        return datefacture;
    }

    public void setDatefacture(Timestamp datefacture) {
        this.datefacture = datefacture;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public String getIddetailmouvementphysique() {
        return iddetailmouvementphysique;
    }

    public void setIddetailmouvementphysique(String iddetailmouvementphysique) {
        this.iddetailmouvementphysique = iddetailmouvementphysique;
    }


    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }


    public Detailsfacturemateriel[] getDetailFactures() {
        return detailFactures;
    }

    public void setDetailFactures(Detailsfacturemateriel[] detailFactures) {
        this.detailFactures = detailFactures;
    }

    public double getTotal() {
        return getotalfacture();
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
