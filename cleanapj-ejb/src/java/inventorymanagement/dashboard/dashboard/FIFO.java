package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class FIFO extends MappedInteger {
    String idarticle;
    String marque;
    String modele;
    String description;
    double annee;
    double mois;
    double total_achetes;
    double total_vendus;
    double total_achetes_valeur;
    double total_vendus_valeur;
    double quantite_stock;

    public FIFO() {
        setNomTable("v_FIFO");
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal_achetes() {
        return total_achetes;
    }

    public void setTotal_achetes(double total_achetes) {
        this.total_achetes = total_achetes;
    }

    public double getTotal_vendus() {
        return total_vendus;
    }

    public void setTotal_vendus(double total_vendus) {
        this.total_vendus = total_vendus;
    }

    public double getTotal_achetes_valeur() {
        return total_achetes_valeur;
    }

    public void setTotal_achetes_valeur(double total_achetes_valeur) {
        this.total_achetes_valeur = total_achetes_valeur;
    }

    public double getTotal_vendus_valeur() {
        return total_vendus_valeur;
    }

    public void setTotal_vendus_valeur(double total_vendus_valeur) {
        this.total_vendus_valeur = total_vendus_valeur;
    }

    public double getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(double quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public double getMois() {
        return mois;
    }

    public void setMois(double mois) {
        this.mois = mois;
    }
}
