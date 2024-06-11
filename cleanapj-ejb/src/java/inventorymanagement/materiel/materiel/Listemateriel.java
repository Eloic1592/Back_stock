package inventorymanagement.materiel.materiel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Listemateriel extends MappedInteger {
    String idmateriel;
    String idtypemateriel;
    String typemateriel;
    String val;
    String marque;
    String modele;
    String numserie;
    String description;
    double prixvente;
    double caution;
    String signature;
    int etat;
    int statutmateriel;
    String statut;

    public Listemateriel() {
        super.setNomTable("liste_materiel");
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

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public int getStatutmateriel() {
        return statutmateriel;
    }

    public void setStatutmateriel(int statutmateriel) {
        this.statutmateriel = statutmateriel;
    }
}
