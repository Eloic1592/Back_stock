package inventorymanagement.stockage.distribution;

import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;

public class Vuedistributionmateriel extends MappedInteger {
    String iddistribution;
    String idmateriel;
    String marque;
    String modele;
    String numserie;
    String codedep;
    String iddepot;
    String idemplacement;
    String depot;
    double quantite;
    Timestamp datedistribution;
    String codeemp;
    int statut;
    String etat;


    public Vuedistributionmateriel() {
        setNomTable("vue_distribution_materiel");
    }

    @Override
    public String getTuppleID() {
        return iddistribution;
    }

    @Override
    public String getAttributIDName() {
        return "iddistribution";
    }

    public String getIddistribution() {
        return iddistribution;
    }

    public void setIddistribution(String iddistribution) {
        this.iddistribution = iddistribution;
    }


    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Timestamp getDatedistribution() {
        return datedistribution;
    }

    public void setDatedistribution(Timestamp datedistribution) {
        this.datedistribution = datedistribution;
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

    public String getCodedep() {
        return codedep;
    }

    public void setCodedep(String codedep) {
        this.codedep = codedep;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }


    public String getCodeemp() {
        return codeemp;
    }

    public void setCodeemp(String codeemp) {
        this.codeemp = codeemp;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getIdemplacement() {
        return idemplacement;
    }

    public void setIdemplacement(String idemplacement) {
        this.idemplacement = idemplacement;
    }


    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public String getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(String idmateriel) {
        this.idmateriel = idmateriel;
    }
}
