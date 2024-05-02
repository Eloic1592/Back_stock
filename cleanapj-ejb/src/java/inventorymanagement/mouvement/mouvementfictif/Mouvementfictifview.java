package inventorymanagement.mouvement.mouvementfictif;

import bean.CGenUtil;
import inventorymanagement.article.Article;
import inventorymanagement.mouvement.mouvementstock.Mouvementstockview;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Timestamp;

public class Mouvementfictifview extends Mouvementfictif {
    String iddetailmouvementfictif;
    String idmouvementstock;
    Timestamp datededepot;
    String mouvement;
    int typemouvement;
    String idnaturemouvement;
    String naturemouvement;
    String marque;
    String modele;
    String numserie;
    String idmateriel;
    String iddepot;
    String depot;
    double caution;
    Timestamp datedeb;
    Timestamp datefin;
    String commentaire;
    String description;
    int statut;

    public Mouvementfictifview() {
        setNomTable("mouvement_fictif");
    }


    public Timestamp getDatededepot() {
        return datededepot;
    }

    public void setDatededepot(Timestamp datededepot) {
        this.datededepot = datededepot;
    }

    public String getIdmouvementstock() {
        return idmouvementstock;
    }

    public void setIdmouvementstock(String idmouvementstock) {
        this.idmouvementstock = idmouvementstock;
    }

    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }

    @Override
    public String getIdmateriel() {
        return idmateriel;
    }

    @Override
    public void setIdmateriel(String idmateriel) {
        this.idmateriel = idmateriel;
    }

    @Override
    public String getIddepot() {
        return iddepot;
    }

    @Override
    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public int getTypemouvement() {
        return typemouvement;
    }

    public void setTypemouvement(int typemouvement) {
        this.typemouvement = typemouvement;
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


    public String getIddetailmouvementfictif() {
        return iddetailmouvementfictif;
    }

    public void setIddetailmouvementfictif(String iddetailmouvementfictif) {
        this.iddetailmouvementfictif = iddetailmouvementfictif;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    @Override
    public double getCaution() {
        return caution;
    }

    @Override
    public void setCaution(double caution) {
        this.caution = caution;
    }

    public static void main(String[] args) throws Exception {
        Connection c = null;
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            Mouvementstockview[] articles = (Mouvementstockview[]) CGenUtil.rechercher(new Mouvementstockview(), null, null, c, "AND TYPEMOUVEMENT=0");
            for (int i = 0; i < articles.length; i++) {
                System.out.println(articles[i].getTypemouvement());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif == 1) {
                c.close();
            }
        }
    }

}
