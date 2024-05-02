package historique;

import bean.*;
import utilitaire.Utilitaire;


/**
 * @deprecated
 * Classe representant les historiques sur les états d'une classe.
 * Il est possible d'avoir le même resultat avec {@link historique.MapHistorique} et {@link historique.Historique_valeur}
 */

public class HistoriqueEtat extends ClassMAPTable {
  
  public String id;
  public String idMere;
  public String idUser;
  public String idEtat;
  public java.sql.Date dateModification;
  /**
   * 
   * @param i identifiant unique de l'historique
   * @param mere id de l'objet qui garde l'historique
   * @param user id de l'utilisateur modifiant l'etat
   * @param idEta id de l'etat
   * @param dateModif date de la modification
   */
  public HistoriqueEtat(String i, String mere, String user, String idEta, java.sql.Date dateModif) {
    super.setNomTable("historiqueetat");
    this.setId(i);
    this.setIdMere(mere);
    this.setIdUser(user);
    this.setIdEtat(idEta);
    this.setDateModification(dateModif);
  }
  /**
   * 
   * @param mere id de l'objet qui garde l'historique
   * @param user id de l'utilisateur modifiant l'etat
   * @param idEta id de l'etat
   * @param dateModif date de la modification
   */
  public HistoriqueEtat(String mere, String user, String idEta, java.sql.Date dateModif) {
    super.setNomTable("historiqueetat");
    setIndicePk("HE");
    setNomProcedureSequence("getSeqHistoriqueEtat");
    this.setId(makePK());
    this.setIdMere(mere);
    this.setIdUser(user);
    this.setIdEtat(idEta);
    this.setDateModification(dateModif);
  }
  /**
   * 
   * @param mere id de l'objet qui garde l'historique
   * @param user id de l'utilisateur modifiant l'etat
   * @param idEta id de l'etat
   * @param dateModif date de la modification
   */
  public HistoriqueEtat(String mere, String user, String idEta, String dateModif) {
   super.setNomTable("historiqueetat");
   setIndicePk("HE");
   setNomProcedureSequence("getSeqHistoriqueEtat");
   this.setId(makePK());
//   this.setIdMere(mere);
   if (mere.compareTo("")==0 || mere==null) {
     this.setIdMere("-");
   }
   else this.setIdMere(mere);

//   this.setIdUser(user);
   if (user.compareTo("")==0 || user==null) {
     this.setIdUser("-");
   }
   else this.setIdUser(user);

//   this.setIdEtat(idEta);
   if (idEta.compareTo("")==0 || idEta==null) {
     this.setIdEtat("-");
   }
   else this.setIdEtat(idEta);

//   this.setDateModification(dateModif);
   if (String.valueOf(dateModif).compareTo("")==0 || dateModif==null){
     this.setDateModification(utilitaire.Utilitaire.dateDuJourSql());
   }
   else this.setDateModification(utilitaire.Utilitaire.string_date("dd/MM/yyyy",dateModif));

 }
  public String getAttributIDName() {
    return "id";
  }
  public String getTuppleID() {
    return String.valueOf(id);
  }
  /**
   * 
   * @param id identifiant unique représentant l'historique
   */
  public void setId(String id) {
    this.id = id;
  }
  /**
   * 
   * @return identifiant unique représentant l'historique
   */
  public String getId() {
    return id;
  }
  /**
   * 
   * @param idMere identifiant de l'objet modifié
   */
  public void setIdMere(String idMere) {
    this.idMere = idMere;
  }
  /**
   * 
   * @return idMere identifiant de l'objet modifié
   */
  public String getIdMere() {
    return idMere;
  }
  public void setIdUser(String idUser) {
      if (getMode().compareTo("modif") == 0 && idUser != null && idUser.contains("/")) {
          String[] g = Utilitaire.split(idUser, "/");
          String t = g[0];
          this.idUser = g[0];
          return;
      }
    this.idUser = idUser;
  }
  public String getIdUser() {
    return idUser;
  }
  /**
   * @param idEtat etat modifié
   */
  public void setIdEtat(String idEtat) {
    this.idEtat = idEtat;
  }
  /**
   * 
   * @return etat modifié
   */
  public String getIdEtat() {
    return idEtat;
  }
  /**
   * 
   * @param dateModification date de la modification
   */
  public void setDateModification(java.sql.Date dateModification) {
    this.dateModification = dateModification;
  }
  /**
   * @return date de la modification
   */
  public java.sql.Date getDateModification() {
    return dateModification;
  }
}