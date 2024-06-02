package inventorymanagement.stockage.inventaire;

import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;

public class Vueinventaire extends MappedInteger {
    String idinventaire;
    String idarticle;
    double quantitereel;
    String marque;
    String modele;
    double quantitetheorique;
    Timestamp dateinventaire;
    int statut;

    public Vueinventaire() {
        setNomTable("vue_inventaire");
    }

    public String getIdinventaire() {
        return idinventaire;
    }

    public void setIdinventaire(String idinventaire) {
        this.idinventaire = idinventaire;
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public double getQuantitereel() {
        return quantitereel;
    }

    public void setQuantitereel(double quantitereel) {
        this.quantitereel = quantitereel;
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

    public double getQuantitetheorique() {
        return quantitetheorique;
    }

    public void setQuantitetheorique(double quantitetheorique) {
        this.quantitetheorique = quantitetheorique;
    }

    public Timestamp getDateinventaire() {
        return dateinventaire;
    }

    public void setDateinventaire(Timestamp dateinventaire) {
        this.dateinventaire = dateinventaire;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
