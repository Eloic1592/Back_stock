package inventorymanagement.devis.proforma;

import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;

public class ClientProforma extends MappedInteger {
    String idproforma;
    String iddevis;
    String nom;
    String adresse;
    String numstat;
    String nif;
    String telephone;
    Timestamp datedevis;
    Timestamp datevalidation;

    public ClientProforma() {
        setNomTable("client_proforma");
    }


    public String getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(String idproforma) {
        this.idproforma = idproforma;
    }

    public String getIddevis() {
        return iddevis;
    }

    public void setIddevis(String iddevis) {
        this.iddevis = iddevis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Timestamp getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Timestamp datedevis) {
        this.datedevis = datedevis;
    }

    public Timestamp getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(Timestamp datevalidation) {
        this.datevalidation = datevalidation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumstat() {
        return numstat;
    }

    public void setNumstat(String numstat) {
        this.numstat = numstat;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}


