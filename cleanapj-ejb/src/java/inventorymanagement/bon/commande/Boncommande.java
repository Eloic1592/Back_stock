package inventorymanagement.bon.commande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;


public class Boncommande extends MappedInteger {
    String idboncommande;
    String idproforma;
    Timestamp dateboncommande;
    int statut;

    public Boncommande() {
        setNomTable("boncommande");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("boncommande");
        this.preparePk("COM", "getseqboncommande");
        this.setIdboncommande(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return idboncommande;
    }

    @Override
    public String getAttributIDName() {
        return "idboncommande";
    }

    public String getIdboncommande() {
        return idboncommande;
    }

    public void setIdboncommande(String idboncommande) {
        this.idboncommande = idboncommande;
    }

    public String getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(String idproforma) {
        this.idproforma = idproforma;
    }

    public Timestamp getDateboncommande() {
        return dateboncommande;
    }

    public void setDateboncommande(Timestamp dateboncommande) {
        this.dateboncommande = dateboncommande;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
