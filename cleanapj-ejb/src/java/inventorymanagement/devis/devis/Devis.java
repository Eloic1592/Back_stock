package inventorymanagement.devis.devis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import inventorymanagement.devis.detaildevis.Detaildevis;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Devis extends MappedInteger {
    String iddevis;
    String idclient;
    String iddetailmouvementphysique;
    Timestamp datedevis;
    int statut;
    String libelle;
    Detaildevis [] detaildevis;


    public Devis() {
        super.setNomTable("devis");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("devis");
        this.preparePk("DEV", "getseqdevis");
        this.setIddevis(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return iddevis;
    }

    @Override
    public String getAttributIDName() {
        return "iddevis";
    }

    public String getIddevis() {
        return iddevis;
    }

    public void setIddevis(String iddevis) {
        this.iddevis = iddevis;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public Timestamp getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Timestamp datedevis) {
        this.datedevis = datedevis;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Detaildevis[] getDetaildevis() {
        return detaildevis;
    }

    public void setDetaildevis(Detaildevis[] detaildevis) {
        this.detaildevis = detaildevis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIddetailmouvementphysique() {
        return iddetailmouvementphysique;
    }

    public void setIddetailmouvementphysique(String iddetailmouvementphysique) {
        this.iddetailmouvementphysique = iddetailmouvementphysique;
    }
}
