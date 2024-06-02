package inventorymanagement.commande.reception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class Reception extends MappedInteger {
    String idrepcetion;
    String idcommande;
    String datereception;
    int statut;

    public Reception() {
        setNomTable("reception");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("reception");
        this.preparePk("REC", "getseqreception");
        this.setIdrepcetion(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idrepcetion;
    }

    @Override
    public String getAttributIDName() {
        return "idrepcetion";
    }

    public String getIdrepcetion() {
        return idrepcetion;
    }

    public void setIdrepcetion(String idrepcetion) {
        this.idrepcetion = idrepcetion;
    }

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }

    public String getDatereception() {
        return datereception;
    }

    public void setDatereception(String datereception) {
        this.datereception = datereception;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
