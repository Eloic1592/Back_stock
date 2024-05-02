package inventorymanagement.client;

import itusolar.prepare.MappedInteger;

public class Client extends MappedInteger {
     String idClient;
     String nom;
     String idRegime;
     String adresse;
     String telephone;
     String numStat;
     String nif;
     String rc;
     String tp;
     String quittance;
     String typeContact;
     String sousTypeContact;

    public Client() {
        setNomTable("client");
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdRegime() {
        return idRegime;
    }

    public void setIdRegime(String idRegime) {
        this.idRegime = idRegime;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNumStat() {
        return numStat;
    }

    public void setNumStat(String numStat) {
        this.numStat = numStat;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getQuittance() {
        return quittance;
    }

    public void setQuittance(String quittance) {
        this.quittance = quittance;
    }

    public String getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(String typeContact) {
        this.typeContact = typeContact;
    }

    public String getSousTypeContact() {
        return sousTypeContact;
    }

    public void setSousTypeContact(String sousTypeContact) {
        this.sousTypeContact = sousTypeContact;
    }
}
