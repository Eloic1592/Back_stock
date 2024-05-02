package historique;
import bean.ClassMAPTable;
import utilitaire.Utilitaire;

/**
 * Classe représentant les desactivations des utilisateurs.
 * Si une instance de classe existe pour un utilisateur, cet utilisateur est desactivé
 * et ne devrait pas pouvoir se connecter
 * 
 * 
 * @author BICI
 */
public class AnnulationUtilisateur extends ClassMAPTable {
    
    public String idAnnulationUser;
    public int refUser;
    public java.sql.Date daty;
    /**
     * 
     * @param id id de l'annulation
     * @param ref id de l'utilisateur desactivé
     * @param dat date de desactivation
     */
    public AnnulationUtilisateur(String id, String ref, java.sql.Date dat) {
        this.setIdAnnulationUser(id);
        this.setRefUser(ref);
        this.setDaty(dat);
        this.setNomTable("AnnulationUtilisateur");
    }
    /**
     * 
     * @param ref id de l'utilisateur à desactiver
     * @param dat date de desactivation
     */
    public AnnulationUtilisateur(String ref, java.sql.Date dat) {
        setIndicePk("ANU");
//    setNomProcedureSequence("getSeqAnnulationUser");
        setNomProcedureSequence("GET_SEQ_ANNULATIONUTILISATEUR");
        this.setIdAnnulationUser(makePK());
        this.setRefUser(ref);
        this.setDaty(dat);
        this.setNomTable("AnnulationUtilisateur");
    }
    /**
     * 
     * @param ref id de l'utilisateur
     * @param dat date de desactivation
     */
    public AnnulationUtilisateur(String ref, String dat) {
        setIndicePk("ANU");
        setNomProcedureSequence("GET_SEQ_ANNULATIONUTILISATEUR");
        this.setIdAnnulationUser(makePK());
        this.setRefUser(ref);
        if (ref.compareTo("") == 0 || ref == null) {
//            this.setRefUser("-");
        } else {
//            this.setRefUser(ref);
        }

//    this.setDaty(dat);
        if (String.valueOf(dat).compareTo("") == 0 || dat == null) {
            this.setDaty(Utilitaire.dateDuJourSql());
        } else {
            this.setDaty(Utilitaire.string_date("dd/MM/yyyy", dat));
        }
        
        this.setNomTable("AnnulationUtilisateur");
    }
    /**
     * Constructeur la date de desactivation sera la date du jour
     * @param ref id de l'utilisateur
     */
    public AnnulationUtilisateur(String ref) {
        setIndicePk("ANU");
        setNomProcedureSequence("GET_SEQ_ANNULATIONUTILISATEUR");
        this.setIdAnnulationUser(makePK());
        this.setRefUser(ref);
        this.setDaty(Utilitaire.dateDuJourSql());
        this.setNomTable("AnnulationUtilisateur");
    }
    
    public String getAttributIDName() {
        return "idAnnulationUser";
    }
    
    public String getTuppleID() {
        return this.getIdAnnulationUser();
    }
    
    public void setIdAnnulationUser(String idAnnulationUser) {
        this.idAnnulationUser = idAnnulationUser;
    }
    /**
     * 
     * @return id unique permettant d'identifier la desactivation
     */
    public String getIdAnnulationUser() {
        return idAnnulationUser;
    }
    /**
     * 
     * @param refUser id unique permettant d'identifier la desactivation
     */
    public void setRefUser(String refUser) {
        this.setRefUser(Integer.parseInt(refUser));
    }
    
//  public String getRefUser() {
//    return refUser;
//  }
    /**
     * 
     * @return id de l'utilisateur desactivé
     */
    public int getRefUser() {
        return refUser;
    }
    /**
     * 
     * @param refUser id de l'utilisateur desactivé
     */
    public void setRefUser(int refUser) {
        this.refUser = refUser;
    }
    /**
     * Setter daty
     * @param daty date de desactivation
     */
    public void setDaty(java.sql.Date daty) {
        if (String.valueOf(daty).compareTo("") == 0 || daty == null) {
            this.daty = utilitaire.Utilitaire.dateDuJourSql();
        } else {
            this.daty = daty;
        }
    }
    /**
     * 
     * @return date de desactivation
     */
    public java.sql.Date getDaty() {
        return daty;
    }
}
