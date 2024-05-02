package mg.cnaps.messagecommunication;

import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Timestamp;
import utilitaire.Utilitaire;

/**
 * Description du message 
 * @author BICI
 */
public class Message extends ClassMAPTable{
    private String id;
    private String idconversation;
    private String sender;
    private String receiver;
    private String msg;
    private Timestamp dateheure;
    private String statut;
    
    /**
     * Constructeur par defaut
     */
    public Message() {
        super.setNomTable("message");
    }
    
    public String getTuppleID() {
        return id;
    }
    
    public String getAttributIDName() {
        return "id";
    }
    
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MSG", "getSeqmessage");
        this.setId(makePK(c));
    }

    /**
     * Obtenir l'identification du message
     * @return identification du message
     */
    public String getId() {
        return id;
    }

    /**
     * Identification du message
     * @param id identification du message
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtenir qui est l'expediteur
     * @return l'expediteur
     */
    public String getSender() {
        return Utilitaire.champNull(sender);
    }

    /**
     * Expediteur
     * @param sender l'expediteur
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Obtenir le message
     * @return le message
     */
    public String getMsg() {
        return Utilitaire.champNull(msg);
    }

    /**
     * Donner valeur au message
     * @param msg message
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Obtenir le status du message
     * @return status du message
     */
    public String getStatut() {
        return Utilitaire.champNull(statut);
    }

    /**
     * Donner valeur à la statut
     * @param statut status du message
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }    

    /**
     * Obtenir le date et heure 
     * @return date et heure
     */
    public Timestamp getDateheure() {
        return dateheure;
    }

    /**
     * Date et heure
     * @param dateheure date et heure 
     */
    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }

    /**
     * Obtenir l'identification de la conversation
     * @return identification de la conversation
     */
    public String getIdconversation() {
        return Utilitaire.champNull(idconversation);
    }

    /**
     * Identification de la conversation
     * @param idconversation Identification de la conversation
     */
    public void setIdconversation(String idconversation) {
        this.idconversation = idconversation;
    }

    /**
     * Obtenir le receveur
     * @return le receveur
     */
    public String getReceiver() {
        return Utilitaire.champNull(receiver);
    }

    /**
     * Receveur
     * @param receiver Receveur
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
}
