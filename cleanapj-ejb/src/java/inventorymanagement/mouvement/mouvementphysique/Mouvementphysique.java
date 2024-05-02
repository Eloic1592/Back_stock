package inventorymanagement.mouvement.mouvementphysique;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inventorymanagement.mouvement.mouvementstock.MouvementstockManager;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;


import java.sql.Connection;
import java.sql.Timestamp;

public class Mouvementphysique extends MappedInteger {
    String iddetailmouvementphysique;
    int typemouvement;
    String idnaturemouvement;
    Timestamp datedepot;
    String idarticle;
    double quantite;
    double pu;
    double total;
    String iddepot;
    String description;
    String commentaire;
    int statut;

    public Mouvementphysique() {
        super.setNomTable("detailmouvementphysique");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("detailmouvementphysique");
        this.preparePk("MVTP", "getseqdetailmouvementphysique");
        this.setIddetailmouvementphysique(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return iddetailmouvementphysique;
    }

    @Override
    public String getAttributIDName() {
        return "iddetailmouvementphysique";
    }

    public String getIddetailmouvementphysique() {
        return iddetailmouvementphysique;
    }

    public void setIddetailmouvementphysique(String iddetailmouvementphysique) {
        this.iddetailmouvementphysique = iddetailmouvementphysique;
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

    public int getTypemouvement() {
        return typemouvement;
    }

    public void setTypemouvement(int typemouvement) {
        this.typemouvement = typemouvement;
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public Timestamp getDatedepot() {
        return datedepot;
    }

    public void setDatedepot(Timestamp datedepot) {
        this.datedepot = datedepot;
    }


}
