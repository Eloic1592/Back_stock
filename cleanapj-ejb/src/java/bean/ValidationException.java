/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;

/**
 * Responsable de la validaion de Exception
 * @author BICI
 */
public class ValidationException extends Exception {
    private String messageavalider;
    private boolean response = false;
    private ClassMAPTable objet;
    private String acte;
    private List<ClassMAPTable> liste_object;
    
    
    /**
     * Obtenir l'objet concernée
     * @return objet
     */
    public ClassMAPTable getObjet() {
        return objet;
    }

    /**
     * Definit la valeur de l'objet de type ClassMAPTable
     * @param objet objet de type ClassMAPTable
     */
    public void setObjet(ClassMAPTable objet) {
        this.objet = objet;
    }

    /**
     * Obtenir la valeur de l'acte 
     * @return acte
     */
    public String getActe() {
        return acte;
    }

    /**
     * Definit la valeur de l'acte 
     * @param acte acte
     */
    public void setActe(String acte) {
        this.acte = acte;
    }
    
    
    /**
     * Obtenir la valeur du champ "response".
     * @return renvoie simplement la valeur du champ "response".
     */
    public boolean isResponse() {
        return response;
    }

    /**
     * Definit la valeur du champs "response"
     * @param response
     */
    public void setResponse(boolean response) {
        this.response = response;
    }

    /**
     *  Obtenir la valeur du champ "messageavalider".
     * @return message à valider 
     */
    public String getMessageavalider() {
        return messageavalider;
    }

    /**
     * Definit la valeur du champs "messageavalider"
     * @param messageavalider message à valider
     */
    public void setMessageavalider(String messageavalider) {
        this.messageavalider = messageavalider;
    }

    /**
     * Constructeur
     * @param messageavalider message à valider
     */
    public ValidationException(String messageavalider) {
        this.setMessageavalider(messageavalider);
        
    }

    /**
     * Ce constructeur définit les valeurs du messageavalider,objet et
     *  l'acte de l'objet ValidationException à l'aide des paramètres fournis
     * @param messageavalider message à valider
     * @param objet objet 
     * @param acte acte 
     */
    public ValidationException(String messageavalider, ClassMAPTable objet, String acte) {
        this.messageavalider = messageavalider;
        this.objet = objet;
        this.acte = acte;
    }


    /**
     * @deprecated 
     * Construteur 
     * @param message message
     * @param response response
     */
    public ValidationException(String message, boolean response) {
        this.setMessageavalider(messageavalider);
        this.setResponse(response);
    }

    /***
     * obtenir  une liste d'objets de type ClassMAPTable
     * @return liste des objets ClassMAPTable stockés dans le champ "liste_object" de la classe.
     */
    public List<ClassMAPTable> getListe_object() {
        return liste_object;
    }

    /**
     * Definit les valeurs de la liste d'objets de type ClassMAPTable
     * @param liste_object liste des objets ClassMAPTable
     */
    public void setListe_object(List<ClassMAPTable> liste_object) {
        this.liste_object = liste_object;
    }

    
    
    
}
