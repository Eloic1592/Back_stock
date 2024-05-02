/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author BICI
 */
public class ValeurCroises {
    double valeur;
    String lien;
    double[] listeVal;


    /**
     * Obtenir des valeur 
     * @return liste des valeurs
     */
    public double[] getListeVal() {
        return listeVal;
    }

    /**
     * Setter le liste des valeurs 
     * @param listeVal tableau des valeurs
     */
    public void setListeVal(double[] listeVal) {
        this.listeVal = listeVal;
    }

    /**
     * Obtenir la valeur 
     * @return valeur
     */
    public double getValeur() {
        return valeur;
    }

    /**
     * Setter la valeur 
     * @param valeur
     */
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    

    /**
     * Obtenir le lien 
     * @return lien
     */
    public String getLien() {
        return lien;
    }

    /**
     * Donner valeur au lien
     * @param lien  
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Constructeur
     * @param val  valeur
     * @param lie lien
     */
    public ValeurCroises(double val,String lie)
    {
        this.setValeur(val);
        this.setLien(lie);
    }

    /**
     * Constructeur
     * @param val tableau de valeur
     * @param lie lien
     */
    public ValeurCroises(double[] val,String lie)
    {
        this.setListeVal(val);
        if(val.length>=1)this.setValeur(val[0]);
        this.setLien(lie);
    }
    
    
    
    
}
