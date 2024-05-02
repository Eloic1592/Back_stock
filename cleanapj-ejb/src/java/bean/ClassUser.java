
package bean;

import java.sql.Date;

/**
 * Classe de mapping avec un attribut <code>iduser</code> pour garder
 * la dernière utilisateur qui a modifié l'objet. Cette classe est un enfant direct de {@link bean.ClassMAPTable}
 * ainsi en extendant cette classe vous allez avoir une classMAPTable mais avec des attributs en plus pour gérer
 * les utilisateurs.
 * <p>
 *  Ci-dessous un exemple d'implementation de cette classe:
 * </p>
 * <pre>
 *  public class Exemple extends ClassUser{
 *  
       public void controlerUpdate(Connection c) {
 *         //Logique de controle pour les mises à jour
 *         super.controlerUpdate(c);
 *     }
 *     public void controler(Connection c) {
 *            //Logique de controle pour insert
 *            super.controler(c); 
 *     }
 *      public ClassMapTable updateObject(Connection c) throws Exception{
 *          //logique de mise à jour supplémentaire
 *          super.updateObject(c);
 *      }
 *      public ClassMapTable createObject(Connection c) throws Exception{
 *          //logique supplémentaire de création
 *          super.createObject(c);
 *      }
 * }
 * </pre>
 * @author BICI
 */

public abstract class ClassUser extends ClassMAPTable{
    private String iduser;
    private String idverif, idrempli;
    private Date dateverif, daterempli, datesaisie;
    private String direction;
    String service;
    /**
     * Renvoie l'id du dernier utilisateur à avoir fait la modification
     * @return id 
     */
    public String getIduser() {
        return iduser;
    }
    /**
     * DOnner valeur à l'user 
     * @param iduser id du dernier utilisateur à avoir fait la modification
     */
    public void setIduser(String iduser) {
        this.iduser = iduser;
    }    

    public String getDirection() {
        return direction;
    }

    public Date getDatesaisie() {
        return datesaisie;
    }

    public void setDatesaisie(Date datesaisie) {
        this.datesaisie = datesaisie;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getIdverif() {
        return idverif;
    }

    public void setIdverif(String idverif)  throws Exception {
        this.idverif = idverif;
    }

    public String getIdrempli() {
        return idrempli;
    }

    public void setIdrempli(String idrempli)  throws Exception {
        this.idrempli = idrempli;
    }

    public Date getDateverif() {
        return dateverif;
    }

    public void setDateverif(Date dateverification)  throws Exception {
        this.dateverif = dateverification;
    }

    public Date getDaterempli() {
        return daterempli;
    }

    public void setDaterempli(Date dateremplissage)  throws Exception {
        this.daterempli = dateremplissage;
    }
    
}
