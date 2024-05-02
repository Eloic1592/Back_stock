package inventorymanagement.devis.devis;

import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;

public class Clientdevis extends MappedInteger {
    String iddevis;
    String nom;
    String adresse;
    String numstat;
    String nif;
    String telephone;
    String libelle;
    Timestamp datedevis;

    public Clientdevis() {
        setNomTable("client_devis");
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Timestamp getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Timestamp datedevis) {
        this.datedevis = datedevis;
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
