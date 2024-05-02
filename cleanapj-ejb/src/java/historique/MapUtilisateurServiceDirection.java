package historique;
/**
 * Classe représentant un utilisateur avec deux attributs en plus 
 * <code>service</code> et <code>direction</code>.
 * 
 */
public class MapUtilisateurServiceDirection extends MapUtilisateur{
    String service;
    String direction;
    /**
     * 
     * @param refus id de l'utilisateur
     * @param loginus nom d'utilisateur pour se connecter
     * @param pwdus mot de passe pour se connecter
     * @param nomus nom représentant l'utilisateur
     * @param adrus adresse de l'utilisateur
     * @param telus telephone de l'utilisateur
     * @param idrol id du role associé
     * @param service service associé à l'utilisateur
     * @param direction id de la direction associé à l'utilisateur
     * @throws Exception
     */
    public MapUtilisateurServiceDirection(int refus, String loginus, String pwdus, String nomus, String adrus, String telus, String idrol, String service, String direction) throws Exception{
        super(refus, loginus, pwdus, nomus, adrus, telus, idrol);
        this.setService(service);
        this.setDirection(direction);
    }    
    /**
     * Constructeur par défaut
     */
    public MapUtilisateurServiceDirection(){
        super.setNomTable("utilisateurrole");
    }
    /**
     *
     * @return code du service associé
     */
    public String getService() {
        return service;
    }
    /**
     * 
     * @param service code du service associé
     */
    public void setService(String service) {
        this.service = service;
    }
    /**
     * 
     * @return id de la direction
     */
    public String getDirection() {
        return direction;
    }
    /**
     * 
     * @param direction id de la direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public String getTuppleID() {
        //return String.valueOf(getRefuser()+"/"+getPwduser());
        return String.valueOf(getRefuser());
    }
    
}
