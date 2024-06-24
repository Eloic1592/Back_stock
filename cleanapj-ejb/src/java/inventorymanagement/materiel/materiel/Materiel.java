package inventorymanagement.materiel.materiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Materiel extends MappedInteger {
    String idmateriel;
    String marque;
    String modele;
    String numserie;
    String description;
    double prixvente;
    double caution;
    String idtypemateriel;
    String signature;
    int statut;

    public Materiel() {
        setNomTable("materiel");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("materiel");
        this.preparePk("MAT", "getseqmateriel");
        this.setIdmateriel(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idmateriel;
    }

    @Override
    public String getAttributIDName() {
        return "idmateriel";
    }

    public String getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(String idmateriel) {
        this.idmateriel = idmateriel;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixvente() {
        return prixvente;
    }

    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }

    public double getCaution() {
        return caution;
    }

    public void setCaution(double caution) {
        this.caution = caution;
    }


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

}
