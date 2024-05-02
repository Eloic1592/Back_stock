
package mg.cnaps.messagecommunication;

import bean.ClassMAPTable;
import java.sql.Connection;
import utilitaire.Utilitaire;

/**
 * Cette classe permet d'effectuer une conversation
 * @author BICI
 */
public class Conversation extends ClassMAPTable{
    private String id;
    private String iduser1;
    private String iduser2;
    
    /**
     * Constructeur
     * Donner la table utiliser pour cette classe
     */
    public Conversation() {
        super.setNomTable("conversation");
    }

    /**
     * Constructeur
     * @param id identifiant 
     * @param iduser1 l'identifiant de l'utilisateur 1 
     * @param iduser2 l'identifiant de l'utilisateur 2 
     */
    public Conversation(String id, String iduser1, String iduser2) {
        super.setNomTable("conversation");
        this.id = id;
        this.iduser1 = iduser1;
        this.iduser2 = iduser2;
    }
    
    public String getTuppleID() {
        return id;
    }
    
    public String getAttributIDName() {
        return "id";
    }
    
    /**
     * Création de la primary Key
     */
    public void construirePK(Connection c) throws Exception {
       
        this.preparePk("CNV", "getSeqconversation");
        this.setId(makePK(c));
    }

    /**
     * Obtenir l'identifiant
     * @return identifiant
     */
    public String getId() {
        return id;
    }

    /**
     * Donner valeur à l'identifiant
     * @param id identifiant
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtenir l'utilisateur 1 
     * @return identifiant de l'utilisateur 1
     */
    public String getIduser1() {
        return Utilitaire.champNull(iduser1);
    }

    /**
     * Donner valeur à l'utilisateur 1 
     * @param iduser1  identifiant de l'utilisateur 1
     */
    public void setIduser1(String iduser1) {
        if(getMode().compareTo("modif")==0 && iduser1!=null && iduser1.contains("/")){
            String[] g = Utilitaire.split(iduser1, "/");
            String t = g[0];
            this.iduser1= g[0];
            return;
        }
        this.iduser1 = iduser1;
    }

    /**
     * Obtenir l'utilisateur 2
     * @return identifiant de l'utilisateur 2
     */
    public String getIduser2() {
        return Utilitaire.champNull(iduser2);
    }

    /**
     * Donner valeur à l'utilisateur 1 
     * @param iduser2 identifiant de l'utilisateur 2
     */
    public void setIduser2(String iduser2) {
        if(getMode().compareTo("modif")==0 && iduser2!=null && iduser2.contains("/")){
            String[] g = Utilitaire.split(iduser2, "/");
            String t = g[0];
            this.iduser2= g[0];
            return;
        }
        this.iduser2 = iduser2;
    }  
}