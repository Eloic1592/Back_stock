package bean;

import java.sql.Statement;
import utilitaire.UtilDB;
import java.sql.Connection;

/**
 * Classe de mapping pour gérer la liaison de deux objets avec une valeur numérique.
 * 
 */

public class UnionIntraTable extends ClassMAPTable {

  public String id;
  public String id1;
  public String id2;
  public String remarque;
  public double montantMere=0;
  public int etat;
  /**
   * @deprecated les états vont au dela de 1 ou 0
   * @return
   */
  public String getEtatLettre()
  {
    if(etat==0)
      return "cree";
    if(etat==1)
      return "valide";
    return "-";
  }
  /**
   * Constructeur par défaut
   */
  public UnionIntraTable() {
  }
  /**
   * 
   * @param nomTable nom de la table pour stocker l'objet
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param eta etat de la liaison
   */
  public UnionIntraTable(String nomTable,String ide,String id1e,String id2e,String remarqueE, double montantE,int eta){
    this.setNomTable(nomTable);
    this.setId(ide);
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(montantE);
    this.setEtat(eta);
  }
  /**
   * Le nom de procédure et de sequence est obtenu à partir de recherche dans {@link bean.CaractTable}
   * @deprecated etat fixé à 0
   * @param nomTable nom de la table pour stocker l'objet
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   */
  public UnionIntraTable(String nomTable,String id1e,String id2e,String remarqueE, String montantE){
    this.setNomTable(nomTable);
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(montantE);
    this.setEtat(0);
    CaractTable ct=(CaractTable)new bean.CaractTableUtil().rechercher(2,nomTable)[0];
    setIndicePk(ct.getNomSeq());
    setNomProcedureSequence(ct.getNomProc());
    this.setId(makePK());
  }
  /**
   * Le nom de procédure et de sequence est obtenu à partir de recherche dans {@link bean.CaractTable}
   * @deprecated etat fixé à 0
   * @param nomTable nom de la table pour stocker l'objet
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param c connexion ouverte à la base de données
   */
  public UnionIntraTable(String nomTable,String id1e,String id2e,String remarqueE, String montantE,Connection c)throws Exception{
    this.setNomTable(nomTable);
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(montantE);
    this.setEtat(0);
    CaractTable ct=(CaractTable)new bean.CaractTableUtil().rechercher(2,nomTable,c)[0];
    setIndicePk(ct.getNomSeq());
    setNomProcedureSequence(ct.getNomProc());
    this.setId(makePK());
  }
  /**
   * 
   * @param nomTable nom de la table pour stocker l'objet
   * @param id id de l'objet de liaison
   */
  public UnionIntraTable(String nomTable,String id){
    this.setNomTable(nomTable);
    this.setId(id);
  }
  /**
   * @param nomTable nom de la table pour stocker l'objet
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param eta etat de l'objet de liaison(selon les etats dans la constante etat)
   */
  public UnionIntraTable(String nomTable,String ide,String id1e,String id2e,String remarqueE, String montantE,String eta){
    this.setNomTable(nomTable);
    this.setId(ide);
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(utilitaire.Utilitaire.stringToDouble(montantE));
    this.setEtat(utilitaire.Utilitaire.stringToInt(eta));
  }
  /**
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param eta etat de l'objet de liaison(selon les etats dans la constante etat)
   */
  public UnionIntraTable(String ide,String id1e,String id2e,String remarqueE, double montantE,int eta){
    this.setId(ide);
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(montantE);
    this.setEtat(eta);
  }
   /**
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param eta etat de l'objet de liaison(selon les etats dans la constante etat)
   */
  public UnionIntraTable(String ide,String id1e,String id2e,String remarqueE, double montantE,String eta){
    this.setId(ide);
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(montantE);
    this.setEtat(utilitaire.Utilitaire.stringToInt(eta));
  }
  /**
   * @param nomTable nom de la table pour stocker l'objet
   * @param nomProcedure nom de la procédure pour génerer la PK
   * @param suff nom de suffixe au début de la primary key
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param eta etat de l'objet de liaison(selon les etats dans la constante etat)
   */
  public UnionIntraTable(String nomTable,String nomProcedure,String suff,String id1e,String id2e,String remarqueE, double montantE,int eta){
    setNomTable(nomTable);
    setNomProcedureSequence(nomProcedure);
    setIndicePk(suff);
    id = makePK();
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(montantE);
    this.setEtat(eta);
  }
  /**
   * @param nomTable nom de la table pour stocker l'objet
   * @param nomProcedure nom de la procédure pour génerer la PK
   * @param suff nom de suffixe au début de la primary key
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relative à la liaison
   * @param eta etat de l'objet de liaison(selon les etats dans la constante etat)
   */
  public UnionIntraTable(String nomTable,String nomProcedure,String suff,String id1e,String id2e,String remarqueE, String montantE,String eta){
    setNomTable(nomTable);
    setNomProcedureSequence(nomProcedure);
    setIndicePk(suff);
    id = makePK();
    this.setId1(id1e);
    this.setId2(id2e);
    this.setRemarque(remarqueE);
    this.setMontantMere(utilitaire.Utilitaire.stringToDouble(montantE));
    this.setEtat(utilitaire.Utilitaire.stringToInt(eta));
  }
  public String getAttributIDName() {
    /**@todo Implement this bean.ClassMAPTable abstract method*/
    return "id";
  }
  public String getTuppleID() {
    return this.getId();
  }
  /**
   * @param nomTable nom de la table pour stocker l'objet
   * @param nomProcedure nom de la procédure pour génerer la PK
   * @param suff nom de suffixe au début de la primary key
   * @param ide id representant la liaison
   * @param id1e id du premier objet à lier
   * @param id2e id du deuxième objet à lier
   * @param remarqueE remarque sur la liaison
   * @param montantE montant relatif à la liaison
   * @param eta etat de l'objet de liaison(selon les etats dans la constante etat)
   */
  /**
   * 
   * @param id id representant la liaison
   */
  public void setId(String id) {
    this.id = id;
  }
  public String getId() {
    return id;
  }
  /**
   * 
   * @param id1 id du premier objet à lier
   */
  public void setId1(String id1) {
    this.id1 = id1;
  }
  /**
   * 
   * @return id du premier objet à lier
   */
  public String getId1() {
    return id1;
  }
  /**
   * 
   * @param id2 id du deuxième objet à lier
   */
  public void setId2(String id2) {
    this.id2 = id2;
  }
  /**
   * 
   * @return id du deuxième objet à lier
   */
  public String getId2() {
    return id2;
  }
  /**
   * 
   * @param remarque remarque sur la liaison
   */
  public void setRemarque(String remarque) {
    this.remarque = remarque;
  }
  /**
   * 
   * @return remarque sur la liaison
   */
  public String getRemarque() {
    return remarque;
  }
  /**
   * 
   * @param montantMere montant relatif à la liaison
   */
  public void setMontantMere(double montantMere) {
    this.montantMere = montantMere;
  }
  /**
   * Si la chaine n'est pas vide sinon aucun set n'est réalisé
   * @param montantMere montant relatif à la liaison
   */
  public void setMontantMere(String montantMere) {
    if(montantMere!=null && montantMere.compareToIgnoreCase("")!=0)
      this.montantMere =  utilitaire.Utilitaire.stringToDouble(montantMere);
  }
  /**
   * 
   * @return montant relatif à la liaison
   */
  public double getMontantMere() {
    return montantMere;
  }
  /**
   * 
   * @return valeur du montant en enlevant l'exponentiel
   * et en arrondissant 2 chiffre après virgule
   */
  public String getMontantMereLettre()
  {
    return utilitaire.Utilitaire.formaterAr(getMontantMere());
  }
  /**
   * 
   * @param etat 
   */
  public void setEtat(int etat) {
    this.etat = etat;
  }
  public int getEtat() {
    return etat;
  }
  /**
   * @return si etat {@literal >}9 alors faux sinon vrai
   */
  public boolean estIlModifiable()
  {
    if (this.getEtat()>=9)
      return false;
    return true;
  }
  /**
   * Mettre à jour le montant mère d'une liaison pour deux ids donnés
   * @param nomTable nom de la table de stockage
   * @param id1 id de l'objet 1
   * @param id2 id de l'objet 2
   * @param montant montant à mettre à jour
   * @param c connexion ouverte à la base de données
   * @return id de l'objet 
   * @throws Exception
   */
  public static String updateMontantMere(String nomTable,String id1,String id2,String montant,java.sql.Connection c) throws Exception
  {
    Statement st= null;
    try {
      UnionIntraTableUtil uti=new UnionIntraTableUtil();
      uti.setNomTable(nomTable);
      UnionIntraTable[] ui=(UnionIntraTable[])uti.rechercher(2,id1);
      if((ui.length>0)&&(ui[0].estIlModifiable()==false)) throw new Exception("Mappage deja valide");
      String rek="update "+nomTable+"  set montantMere="+ montant+" where id1='"+id1+"' and id2='"+id2+"'";
      st=c.createStatement();
      st.executeUpdate(rek);
      return id1;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
    finally{
      if(st!=null)
        st.close();
    }
  }
  /**
   * Mettre à jour le montant mère d'une liaison pour deux ids donnés
   * avec initialisation interne de la connexion à la base
   * @param id1 id de l'objet 1
   * @param id2 id de l'objet 2
   * @param montant montant à mettre à jour
   * @param c connexion ouverte à la base de données
   * @return id de l'objet 
   * @throws Exception
   */
  public static String updateMontantMere(String nomTable,String id1,String id2,String montant) throws Exception
   {
    java.sql.Connection c=null;
     Statement st= null;
     try {
       c=new UtilDB().GetConn();
       return updateMontantMere(nomTable,id1,id2,montant,c);
     }
     catch (Exception ex) {
       throw new Exception(ex.getMessage());
     }
     finally{
       if(c!=null)
         c.close();
     }
  }
  /**
   * Trouver les liaisons où l'objet fille est l'id donné en paramètre
   * @param i2 id de l'objet fille
   * @param nomTable nom de la table de stockage
   * @param c connexion ouverte à la base de données
   * @return liste de liaisons correspondantes
   * @throws Exception
   */
  public static UnionIntraTable[] getMemeId2(String i2,String nomTable,Connection c) throws Exception
  {
    UnionIntraTable crt=new UnionIntraTable();
    crt.setNomTable(nomTable);
    crt.setId2(i2);
    return (UnionIntraTable[])CGenUtil.rechercher(crt,null,null,c,"");
  }
  /**
   * Trouver les liaisons où l'objet mère est l'id donné en paramètre
   * @param i2 id de l'objet mère
   * @param nomTable nom de la table de stockage
   * @param c connexion ouverte à la base de données
   * @return liste de liaisons correspondantes
   * @throws Exception
   */
  public static UnionIntraTable[] getMemeId1(String i1,String nomTable,Connection c) throws Exception
  {
    UnionIntraTable crt=new UnionIntraTable();
    crt.setNomTable(nomTable);
    crt.setId1(i1);
    return (UnionIntraTable[])CGenUtil.rechercher(crt,null,null,c,"");
  }
}
