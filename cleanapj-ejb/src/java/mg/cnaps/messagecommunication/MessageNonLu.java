/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.cnaps.messagecommunication;

import historique.MapUtilisateur;

/**
 * A propos du message non lu
 * @author BICI
 */
public class MessageNonLu extends MapUtilisateur{
    private int nonlu;
    private String receiver,sender;
    
    /**
     * Obtenir la valeur du champs "nonlu"
     * @return message non lu
     */
    public int getNonlu() {
        return nonlu;
    }

    /**
     * Obtenir la valeur du champs "receiver"
     * @return le receveur
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Definit la valeur du champs "receiver" 
     * @param receiver le receveur
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * Obtenir la valeur du champs "sender"
     * @return l'expediteur
     */
    public String getSender() {
        return sender;
    }

    /**
     * Definit la valeur du champs "sender"
     * @param sender l'expediteur
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Definit la valeur du champs "nonlu"
     * @param nonlu message non lu
     */
    public void setNonlu(int nonlu) {
        this.nonlu = nonlu;
    }
    
    /**
     * Constructeur par defaut
     */
    public MessageNonLu() {
        this.setNomTable("message_nonlu");
    }
    
}
