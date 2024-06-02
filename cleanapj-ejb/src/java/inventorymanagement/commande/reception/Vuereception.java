package inventorymanagement.commande.reception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class Vuereception extends MappedInteger {
    String idrepcetion;
    String idcommande;
    String datereception;
    int statut;

    public Vuereception() {
        setNomTable("vue_reception");
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
