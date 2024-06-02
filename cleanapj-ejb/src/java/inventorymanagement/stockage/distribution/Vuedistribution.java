package inventorymanagement.stockage.distribution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Vuedistribution extends MappedInteger {
    String iddistribution;
    String idarticle;
    String marque;
    String modele;
    String codearticle;
    String codedep;
    String depot;
    double quantite;
    Timestamp datedistribution;
    int statut;

    public Vuedistribution() {
        setNomTable("vue_distribution");
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

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }
}