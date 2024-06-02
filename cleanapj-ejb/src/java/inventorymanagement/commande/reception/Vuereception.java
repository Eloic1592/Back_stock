package inventorymanagement.commande.reception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;


public class Vuereception extends MappedInteger {
    String idreception;
    Timestamp datereception;
    int statut;
    String idcommande;


    public Vuereception() {
        setNomTable("vue_reception");
    }

    public String getIdreception() {
        return idreception;
    }

    public void setIdreception(String idreception) {
        this.idreception = idreception;
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

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }
}
