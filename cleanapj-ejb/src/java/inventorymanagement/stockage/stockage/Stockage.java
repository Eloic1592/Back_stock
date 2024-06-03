package inventorymanagement.stockage.stockage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stockage extends MappedInteger {
    String idstockage;
    String idarticle;
    double quantite;
    Timestamp datestockage;
    int statut;
    int etatstocke;


    public Stockage() {
        this.setNomTable("stockage");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("stockage");
        this.preparePk("STO", "getseqstockage");
        this.setIdstockage(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idstockage;
    }

    @Override
    public String getAttributIDName() {
        return "idstockage";
    }

    public String getIdstockage() {
        return idstockage;
    }

    public void setIdstockage(String idstockage) {
        this.idstockage = idstockage;
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

    public Timestamp getDatestockage() {
        return datestockage;
    }

    public void setDatestockage(Timestamp datestockage) {
        this.datestockage = datestockage;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getEtatstocke() {
        return etatstocke;
    }

    public void setEtatstocke(int etatstocke) {
        this.etatstocke = etatstocke;
    }
}
