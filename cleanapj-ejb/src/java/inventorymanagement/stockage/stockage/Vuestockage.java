package inventorymanagement.stockage.stockage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Vuestockage extends MappedInteger {
    String idstockage;
    String idarticle;
    double quantite;
    String marque;
    String modele;
    String codearticle;
    Timestamp datestockage;
    int statut;


    public Vuestockage() {
        setNomTable("vue_stockage");
    }

    public String getIdstockage() {
        return idstockage;
    }

    public void setIdstockage(String idstockage) {
        this.idstockage = idstockage;
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

    public Timestamp getDatestockage() {
        return datestockage;
    }

    public void setDatestockage(Timestamp datestockage) {
        this.datestockage = datestockage;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
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

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }
}
