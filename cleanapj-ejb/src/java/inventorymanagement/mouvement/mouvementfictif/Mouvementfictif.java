package inventorymanagement.mouvement.mouvementfictif;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Mouvementfictif extends MappedInteger {
    String iddetailmouvementfictif;
    String idmouvement;
    Timestamp datedeb;
    Timestamp datefin;
    double caution;
    String idmateriel;
    String iddepot;
    String description;
    String commentaire;
    int statut;

    public Mouvementfictif() {
        super.setNomTable("detailmouvementfictif");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("detailmouvementfictif");
        this.preparePk("MVTF", "getseqdetailmouvementfictif");
        this.setIddetailmouvementfictif(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return iddetailmouvementfictif;
    }

    @Override
    public String getAttributIDName() {
        return "iddetailmouvementfictif";
    }
    public String getIddetailmouvementfictif() {
        return iddetailmouvementfictif;
    }

    public void setIddetailmouvementfictif(String iddetailmouvementfictif) {
        this.iddetailmouvementfictif = iddetailmouvementfictif;
    }

    public String getIdmouvement() {
        return idmouvement;
    }

    public void setIdmouvement(String idmouvement) {
        this.idmouvement = idmouvement;
    }

    public Timestamp getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Timestamp datedeb) {
        this.datedeb = datedeb;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    public double getCaution() {
        return caution;
    }

    public void setCaution(double caution) {
        this.caution = caution;
    }

    public String getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(String idmateriel) {
        this.idmateriel = idmateriel;
    }

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

}
