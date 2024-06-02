package inventorymanagement.commande.reception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Reception extends MappedInteger {
    String idreception;
    String idcommande;
    Timestamp datereception;
    int statut;

    public Reception() {
        setNomTable("reception");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("reception");
        this.preparePk("REC", "getseqreception");
        this.setIdreception(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return idreception;
    }

    @Override
    public String getAttributIDName() {
        return "idreception";
    }

    public String getIdreception() {
        return idreception;
    }

    public void setIdreception(String idreception) {
        this.idreception = idreception;
    }

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }

    public Timestamp getDatereception() {
        return datereception;
    }

    public void setDatereception(Timestamp datereception) {
        this.datereception = datereception;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
