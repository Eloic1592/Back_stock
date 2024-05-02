package inventorymanagement.bon.livraison;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Bonlivraison extends MappedInteger {
    String idbonlivraison;
    String idboncommande;
    Timestamp datebonlivraison;
    int statut;

    public Bonlivraison() {
        setNomTable("bonlivraison");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("bonlivraison");
        this.preparePk("LIV", "getseqbonlivraison");
        this.setIdbonlivraison(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return idbonlivraison;
    }

    @Override
    public String getAttributIDName() {
        return "idbonlivraison";
    }

    public String getIdbonlivraison() {
        return idbonlivraison;
    }

    public void setIdbonlivraison(String idbonlivraison) {
        this.idbonlivraison = idbonlivraison;
    }

    public String getIdboncommande() {
        return idboncommande;
    }

    public void setIdboncommande(String idboncommande) {
        this.idboncommande = idboncommande;
    }

    public Timestamp getDatebonlivraison() {
        return datebonlivraison;
    }

    public void setDatebonlivraison(Timestamp datebonlivraison) {
        this.datebonlivraison = datebonlivraison;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
