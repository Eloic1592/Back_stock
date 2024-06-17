package inventorymanagement.mouvement.mouvementstock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictif;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysique;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Mouvementstock extends MappedInteger {
    String idmouvementstock;
    Timestamp datedepot;
    int typemouvement;
    String idnaturemouvement;
    int statut;
    Mouvementphysique [] mouvementphysiques;
    Mouvementfictif [] mouvementfictifs;

    public Mouvementstock() {
        super.setNomTable("mouvementstock");
    }
    String idetudiant;

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("mouvementstock");
        this.preparePk("MVT", "getseqmouvementStock");
        this.setIdmouvementstock(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idmouvementstock;
    }

    @Override
    public String getAttributIDName() {
        return "idmouvementstock";
    }

    public String getIdmouvementstock() {
        return idmouvementstock;
    }

    public void setIdmouvementstock(String idmouvementstock) {
        this.idmouvementstock = idmouvementstock;
    }

    public Timestamp getDatedepot() {
        return datedepot;
    }

    public void setDatedepot(Timestamp datedepot) {
        this.datedepot = datedepot;
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

    public Mouvementphysique[] getMouvementphysiques() {
        return mouvementphysiques;
    }

    public void setMouvementphysiques(Mouvementphysique[] mouvementphysiques) {
        this.mouvementphysiques = mouvementphysiques;
    }

    public Mouvementfictif[] getMouvementfictifs() {
        return mouvementfictifs;
    }

    public void setMouvementfictifs(Mouvementfictif[] mouvementfictifs) {
        this.mouvementfictifs = mouvementfictifs;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getIdetudiant() {
        return idetudiant;
    }

    public void setIdetudiant(String idetudiant) {
        this.idetudiant = idetudiant;
    }
}
