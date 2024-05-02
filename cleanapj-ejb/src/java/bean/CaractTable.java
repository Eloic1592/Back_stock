package bean;

import java.sql.*;

/**
 * Objet de configuration sur les informations extra d'une table
 * 
 * 
 * @author BICI
 * 
 */
public class CaractTable extends bean.ClassMAPTable {

  private String id;
  private String nomTable;
  private String nomSeq;
  private String nomProc;
  private String nomFille;

  /**
   * Constructeur par defaut
   */
  public CaractTable() {
  }
  public String getAttributIDName() {
          return "id";
  }

  public String getTuppleID() {
          return id;
	}

  /**
   *
   * Constructeur
   * @param i identifiant 
   * @param nomT nom de la table
   * @param nomS nom sequence
   * @param nomProc nom procédure
   */
  public CaractTable(String i,String nomT,String nomS,String nomProc) {
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getId() {
    return id;
  }

  public void setNomTable(String nomTable) {
    this.nomTable = nomTable;
  }
  /**
   * Nom de la table 
   */
  public String getNomTable() {
    return nomTable;
  }

  public void setNomSeq(String nomSeq) {
    this.nomSeq = nomSeq;
  }
  /**
   * Nom de la séquence à utiliser pour la table donnée
   * @return nom de la séquence 
   */
  public String getNomSeq() {
    return nomSeq;
  }
  public void setNomProc(String nomProc) {
    this.nomProc = nomProc;
  }
  /**
   *  Nom de la procédure utilisée pour la géneration de valeur ID pour la table donnée
   * @return nom de la procédure 
   */
  public String getNomProc() {
    return nomProc;
  }
  public void setNomFille(String nomFille) {
    this.nomFille = nomFille;
  }
  /**
   * Nom de la table fille
   * @return nom de la table fille
   */
  public String getNomFille() {
    return nomFille;
  }
}