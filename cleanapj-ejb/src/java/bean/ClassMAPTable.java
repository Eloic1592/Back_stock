
package bean;


import bean.CGenUtil;
import bean.ErreurDAO;
import bean.ListeColonneTable;
import constante.ConstanteEtat;
import utilitaire.UtilDB;
import files.Log;
import historique.Historique_valeur;
import historique.MapHistorique;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import utilitaire.Utilitaire;

/**
 * Cette classe est un objet de mapping representant une table/view dans une base de données et
 * définit les différentes logiques d'intéraction avec cette base.
 * 
 * <p>
 *  Ci-dessous un exemple d'implémentation de la classe:
 * </p>
 * <pre>
 *  public class Exemple extends ClassMapTable{
 *     public void controlerUpdate(Connection c) {
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
 *  }
 * </pre>
 * @author BICI
 */
public abstract class ClassMAPTable implements Serializable {

    /**
     * Constructeur: initialise le nom de la procédure et la longueur des clés primaires par défaut
     */
    public ClassMAPTable() {
        INDICE_PK = "EXE";
        nomProcedureSequence = "GetSeqExecutions";
        longuerClePrimaire = 6;
        setNombreChamp();
    }
    
    /**
     * Inserer un objet
     * @param user : id de l'utilisateur courant qui a fait l'insertion
     * @param c : connexion ouverte à la base de données
     * @return l'objet même après son enregistrement
     * @throws Exception 
     */
    public ClassMAPTable createObject(String user, Connection c)throws Exception
    {
        controler(c);
        if (getTuppleID()==null||getTuppleID().compareToIgnoreCase("")==0||getTuppleID().compareToIgnoreCase("0")==0)construirePK(c);
        insertToTableWithHisto(user, c);
        return this;
    }

    /**
     * Génerer une clé primaire 
     * @param c : connexion ouverte à la base de données
     * @return chaine de caractère représentant la clé primaire généré
     * @throws Exception 
     */
    public String makePK(Connection c) throws Exception {
        int maxSeq = Utilitaire.getMaxSeq(nomProcedureSequence, c);
        String nombre = Utilitaire.completerInt(longuerClePrimaire, maxSeq);
        return String.valueOf(INDICE_PK) + String.valueOf(nombre);
    }
    /**
     * 
     * @param c connexion ouverte à la base de données
     * @throws Exception
     */
    public void construirePK(Connection c) throws Exception {
    }

    /**
     * Redéfinir cette fonction pour des contrôles avant insertion au sein de l'objet concerné 
     * @param c : connexion ouverte à la base de données
     * @throws Exception 
     */
    public void controler(Connection c) throws Exception {

    }

    /**
     * Redéfinir cette fonction pour des contrôles avant mise à jour au sein de l'objet concerné 
     * @param c
     * @throws Exception 
     */
    public void controlerUpdate(Connection c) throws Exception {

    }

    /**
     * Construire une clé primaire, NE PAS OUBLIER de redéfinir cette fonction pour une classe mappant une table de la base de données
     * @return une clé primaire
     */
    public String makePK() {
        int maxSeq = Utilitaire.getMaxSeq(nomProcedureSequence);
        String nombre = Utilitaire.completerInt(longuerClePrimaire, maxSeq);
        return String.valueOf(INDICE_PK) + String.valueOf(nombre);
    }

    /**
     * Creation de la clé primaire
     * @param indicePK indice de la clé primaire
     * @param fonct nom de la procedure de la  sequence 
     * @return une clé primaire
     * @throws Exception
     */
    public String makePK(String indicePK, String fonct) throws Exception {
        this.preparePk(indicePK, fonct);
        return makePK();
    }

    /***
     * Creation de la clé primaire
     * @param daty date
     * @return une clé primaire
     */
    public String makePKCFin(String daty) {
        int maxSeq = getMaxColonneFactFin(daty) + 1;
        String nombre = Utilitaire.completerInt(longuerClePrimaire, maxSeq);
        return String.valueOf(INDICE_PK) + String.valueOf(nombre);
    }

    /***
     * Creation de la clé primaire
     * @param daty date
     * @param entite entité
     * @return une clé primaire
     */
    public String makePKCFinEntite(String daty, String entite) {
        int maxSeq = getMaxColonneFactFinEntite(daty, entite) + 1;
        String nombre = Utilitaire.completerInt(longuerClePrimaire, maxSeq);
        return String.valueOf(INDICE_PK) + String.valueOf(nombre);
    }

    /**
     * Donner valeur à l'indice du clé primaire
     * @param indice indice
     */
    public void setIndicePk(String indice) {
        INDICE_PK = indice;
    }

    /**
     * Donner nom à la procédure du sequence
     * @param seq nom de la procédure du sequence
     */
    public void setNomProcedureSequence(String seq) {
        nomProcedureSequence = seq;
    }

    /**
     *  Donner valeur à la longueur du cle primaire
     * @param longueur la longueur du cle primaire
     */
    public void setLonguerClePrimaire(int longueur) {
        if (longueur > 0) {
            longuerClePrimaire = longueur;
        }
    }

    /**
     * Obtenur 
     * @return
     */
    public String getNomTable() {
        if (nomTable == null) {
            if (this.nomTableSelect == null) {
                return this.getClassName();
            }
            return nomTableSelect;
        }
        return nomTable;
    }

    public void setNomTable(String table) {
        if (table != null && table.compareTo("") != 0) {
            nomTable = table;
        }
    }

    /**
     * Retourne l'id d'une classe
     * @return 
     */
    public abstract String getTuppleID();

    /**
     * Retourne le nom de la méthode qui retourne l'id d'une classe
     * @return 
     */
    public abstract String getAttributIDName();

    /**
     * Obtenir nom de classe 
     * @return nom de la classe
     */
    public String getClassName() {
        return getClass().getName();
    }
    /**
     * Obtenir valeur à utiliser comme libellé
     * @return valeur à utiliser comme libellé
     */
    public String getValColLibelle(){
        return columnlibelle;
    }
    
    /**
     * Donner valeur à la colonne 
     * @param columnlib valeur à utiliser comme libellé
     */
    public void setValColLibelle(String columnlib){
        this.columnlibelle = columnlib;
    }
    
    /**
     * Retourne la liste des champs d'un objet avec heritage
     * @return liste des attributs en prenant en compte les heritages
     * @throws Exception 
     */
    public Field[] getFieldList() throws Exception {
        return ListeColonneTable.getFieldListeHeritage(this);
    }

    /**
     * Donner valeur au nombre de champs
     */
    public void setNombreChamp() {
        Class cls = getClass();
        Field fieldlist[] = cls.getDeclaredFields();
        nombreChamp = fieldlist.length;
    }

    /**
     * Donner valeur au nombre de champs
     * @param nouveau nombr de champs
     */
    public void setNombreChamp(int nouveau) {
        nombreChamp = nouveau;
    }

    /**
     * Ajouter des champs 
     */
    public void setFieldList() {
        try {
            cls = getClass();
            Field fieldlist[] = cls.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                champ.add(i, fieldlist[i]);
            }

            nombreChamp = fieldlist.length;
        } catch (Exception e) {
            System.out.println("SETFIELDLIST ERREUR".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
    }

    /***
     * Ajouter champs
     * @param a champs à ajouter
    */
    public void setFieldList(Field a) {
        try {
            champ.add(nombreChamp, a);
            nombreChamp++;
        } catch (Exception e) {
            System.out.println("SETFIELDLIST ERREUR".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
    }
    /**
     * Retourne le type d'un champ
     * @param fld
     * @return 
     */
    protected int getTypeMAPField(Field fld) {
        String nomClasse = fld.getType().getName();
        if (nomClasse.equals("boolean")) {
            return 0;
        }
        if (nomClasse.equals("byte")) {
            return 1;
        }
        if (nomClasse.equals("short")) {
            return 2;
        }
        if (nomClasse.equals("int")) {
            return 3;
        }
        if (nomClasse.equals("long")) {
            return 4;
        }
        if (nomClasse.equals("float")) {
            return 5;
        }
        if (nomClasse.equals("Real")) {
            return 5;
        }
        if (nomClasse.equals("double")||nomClasse.equals(Double.class.getName())) {
            return 6;
        }
        if (nomClasse.equals("java.lang.String")) {
            return 10;
        }
        if (nomClasse.equals("java.sql.Date")) {
            return 21;
        }
        if (nomClasse.equals("java.sql.Time")) {
            return 22;
        }
        if (nomClasse.equals("java.sql.Blob")) {
            return 31;
        }
        if (nomClasse.equals("java.sql.Clob")) {
            return 32;
        }
        if (nomClasse.equals("java.lang.Number")) {
            return 34;
        }
        if (nomClasse.equals("java.sql.Timestamp")) {
            return 35;
        }
        if (nomClasse.equals("org.postgis.PGgeometry")) {
            return 36;
        }
        return !nomClasse.equals("java.lang.Integer") ? -1 : 33;
    }

    /**
     * Suppression avec historique de l'objet en cours
     * @param refUser id de l'utilisateur qui fait la suppression
     * @param con connexion ouverte à la base de données
     * @return toujours 1
     * @throws Exception 
     */
    public int deleteToTableWithHisto(String refUser, Connection con) throws Exception {
        try {
            MapHistorique histo = new MapHistorique(this.getNomTable(), "delete", refUser, this.getTuppleID());
            //System.out.println(" ClassMapTable.deleteToTableWithHisto 211" );
            histo.setObjet(this.getClassName());

            ClassMAPTable obj = (ClassMAPTable) (Class.forName(this.getClassName()).newInstance());
            if (obj != null) {
                System.out.println("BREAKPOINT DELETEHISTO = " + obj.getNomTable());
                ClassMAPTable[] tabObj = (ClassMAPTable[]) CGenUtil.rechercher(obj, null, null, con, " and " + obj.getAttributIDName() + " = '" + histo.getRefObjet() + "'");
                Historique_valeur valeur = new Historique_valeur();
                if (tabObj.length != 0) {
                    Field[] listeField = this.getFieldList();
                    for (int i = 0; i < listeField.length; i++) {
//                    String nomVal = "setVal"+i;
                        int temp = i + 1;
//                    String get = "get"+Utilitaire.convertDebutMajuscule(listeField[i].getName());
//                        System.out.println(listeField[i].getName() + " : "+ tabObj[0].getValInsert(listeField[i].getName()));
                        valeur.setValChamp("val" + temp, listeField[i].getName() + ":" + tabObj[0].getValInsert(listeField[i].getName()));
                    }
                    valeur.setIdhisto(histo.getIdHistorique());
                    valeur.setRefhisto(histo.getRefObjet());
                    valeur.setNom_table(this.getValInsert("nomTable"));
                    valeur.setNom_classe(this.getClassName());
                    valeur.construirePK(con);
                    valeur.insertToTable(con);
                }
            }

            int r = this.deleteToTable(con);
            //System.out.println(" ClassMapTable.deleteToTableWithHisto r="+r );
            if (r >= 1) {
                histo.insertToTable(con);
                return r;
            }
            //System.out.println(" Apres insert to table 214" );
        } catch (Exception ex) {
            if(con!=null)con.rollback();
            throw new Exception(ex.getMessage());
        }
        return 0;
    }

    /**
     * Suppression avec historique de l'objet en cours
     * Initialisation de connexion interne à la fonction
     * @param refUser id de l'utilisateur qui fait la suppression
     * @return toujours 1
     * @throws Exception 
     */
    public int deleteToTableWithHisto(String refUser) throws Exception {
        Connection con = null;
        try {
            con = new UtilDB().GetConn();
            con.setAutoCommit(false);
            deleteToTableWithHisto(refUser, con);
            con.commit();
            return 1;
        } catch (SQLException e) {
            throw e;
        } catch (Exception ex) {
            con.rollback();
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    /**
     * Suppression dans une table sans historique
     * @param cDb connexion ouverte eà la base de donée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int deleteToTable(Connection cDb) throws Exception {
        PreparedStatement stmt;
        String sqlQry;
        int retoure;
        String tableName = getNomTable();
        stmt = null;
        Field fld = null;
        sqlQry = "DELETE FROM " + tableName+"";
        retoure = 0;
        try {
            //Field fieldlist[] = getFieldList();
            sqlQry = sqlQry + " WHERE " + getAttributIDName() + " = '" + getTuppleID() + "'";
            stmt = cDb.prepareStatement(sqlQry);
            //stmt.setString(1, getTuppleID());
            retoure = stmt.executeUpdate();
//            cDb.commit();
            // if (retour == 0)throw new Exception ("Erreur suppression dans la table");
            //retoure= 1;
        } catch (SQLException sqlex) {
            throw new Exception(sqlex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cDb != null) {
                    cDb.rollback();
                }
                throw new Exception("ERREUR deleteToTable " + e.getMessage());
            } catch (Exception ee) {
                throw new Exception("ERREUR SQL AVEC CONN " + ee.getMessage());
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                //if(cDb!=null) cDb.close();

            } catch (Exception se) {
                Log.setLog(getClassName() + " - probleme de fermeture d'1 Statement ");
            }
            return retoure;
        }
    }

    /**
     * Suppression dans une table sans historique, avec where
     * @param where critère apres WHERE dans la requete
     * @param cDb connexion ouverte à la base de donnée
     * @return un nombre de type int 
     * @throws Exception 
     */
    public int deleteToTable(String where, Connection cDb) throws Exception {
        PreparedStatement stmt;
        String sqlQry;
        int retoure;
        String tableName = getNomTable();
        stmt = null;
        Field fld = null;
        sqlQry = "DELETE FROM " + tableName+"";
        retoure = 0;
        try {
            //Field fieldlist[] = getFieldList();
            sqlQry = sqlQry + " WHERE " + where;
            stmt = cDb.prepareStatement(sqlQry);
            //stmt.setString(1, getTuppleID());
//      System.out.println("sqlQry =========== "+sqlQry + getTuppleID());
            int retour = stmt.executeUpdate();
            // System.out.println("ClassMapTable.DeleteToTable =========== 259" + sqlQry + getTuppleID());
//            cDb.commit();
            // if (retour == 0)throw new Exception ("Erreur suppression dans la table");
            //retoure= 1;
        } catch (SQLException sqlex) {
            //System.out.println("ClassMapTable.DeleteToTable =========== 266" + sqlex.getMessage());
            throw new Exception(sqlex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cDb != null) {
                    cDb.rollback();
                }
                throw new Exception("ERREUR deleteToTable " + e.getMessage());
            } catch (Exception ee) {
                throw new Exception("ERREUR SQL AVEC CONN " + ee.getMessage());
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                //if(cDb!=null) cDb.close();

            } catch (Exception se) {
                Log.setLog(getClassName() + " - probleme de fermeture d'1 Statement ");
            }
            return retoure;
        }
    }

    /**
     * Suppression groupée
     * @param cDb connexion ouverte à la base de donnée
     * @return un nombre de type int 
     * @throws Exception 
     */
    public int deleteToTableGroupe(Connection cDb)
            throws Exception {
        Statement stmt;
        String sqlQry;
        int retoure;
        String tableName = getNomTable();
        stmt = null;
        Field fld = null;
        sqlQry = "DELETE FROM " + tableName+"";
        retoure = 0;
        try {
            //Field fieldlist[] = getFieldList();
            sqlQry = sqlQry + " WHERE " + CGenUtil.makeWhere(this);
            stmt = cDb.createStatement();

            int retour = stmt.executeUpdate(sqlQry);
            cDb.commit();
            /*if (retour==0)
             throw new Exception ("Erreur suppression dans la table");*/
            //retoure= 1;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cDb != null) {
                    cDb.rollback();
                }
                throw new Exception("ERREUR deleteToTable " + e.getMessage());
            } catch (Exception ee) {
                throw new Exception("ERREUR SQL AVEC CONN " + ee.getMessage());
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                //if(cDb!=null) cDb.close();

            } catch (Exception se) {
                Log.setLog(getClassName() + " - probleme de fermeture d'1 Statement ");
            }
            return retoure;
        }
    }

    /**
     * Suppression
     * @return un nombre de type int
     */
    public int deleteToTable() {
        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            try {
                c = util.GetConn();
                c.setAutoCommit(false);
                int i = deleteToTable(c);
                c.commit();
                return i;
            } catch (Exception e) {
                if (c != null) {
                    try {
                        c.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                //System.out.println("ERREUR DELETE_TABLE ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (Exception exception1) {
            }
        }
    }

    /**
     * Suppression
     * @param histo classe mapping
     * @return un nombre de type int
     * @throws ErreurDAO 
     */
    public int deleteToTable(ClassMAPTable histo) throws ErreurDAO {
        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            c = util.GetConn();
            c.setAutoCommit(false);
            histo.insertToTable(c);
            int i = deleteToTable(c);
            c.commit();
            int j = 1;
            return j;
        } catch (Exception e) {
            try {
                c.rollback();
                throw new ErreurDAO(String.valueOf(String.valueOf((new StringBuffer("ERREUR deleteToTable ")).append(e.getMessage()).append(" ").append(e.getMessage()))));
            } catch (Exception ee) {
                throw new ErreurDAO(String.valueOf(String.valueOf((new StringBuffer("ERREUR SQL ")).append(e.getMessage()).append(" ").append(ee.getMessage()))));
            }
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                //util.close_connection();
            } catch (Exception exception1) {
            }
        }
    }

    /**
     * Mise à jour
     * @param cDb connexion ouverte à la base de donnée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int updateToTable(Connection cDb)
            throws Exception {
        String tableName = getNomTable();
        PreparedStatement stmt = null;
        Field fld = null;
        String sqlQry = String.valueOf(String.valueOf((new StringBuffer("UPDATE ")).append(""+tableName+"").append(" SET ")));
        try {
            Field fieldlist[] = ListeColonneTable.getChampBase(this, cDb);
            //Field fieldlist[] = getFieldList();
            for (int i = 0; i < fieldlist.length; i++) {
                fld = fieldlist[i];
                sqlQry = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sqlQry)))).append(fld.getName()).append(" = ? ")));
                if (i + 1 < fieldlist.length) {
                    sqlQry = String.valueOf(String.valueOf(sqlQry)).concat(", ");
                }
            }

            sqlQry = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sqlQry)))).append(" WHERE ").append(getAttributIDName()).append(" = ? ")));
            System.out.println("requete == "+sqlQry);
            stmt = cDb.prepareStatement(sqlQry);
            for (int i = 0; i < fieldlist.length; i++) {
                fld = fieldlist[i];
                //System.out.println(" ===== " + this.getValField(fld));
                switch (getTypeMAPField(fld)) {
                    case 0: // '\0'
                        stmt.setBoolean(i + 1, ((Boolean) this.getValField(fld)).booleanValue());
                        break;

                    case 1: // '\001'
                        stmt.setByte(i + 1, ((Byte) this.getValField(fld)).byteValue());
                        break;

                    case 2: // '\002'
                        stmt.setShort(i + 1, ((Short) this.getValField(fld)).shortValue());
                        break;

                    case 3: // '\003'
                        Integer it = (Integer) this.getValField(fld);
                        stmt.setInt(i + 1, it.intValue());
                        break;

                    case 4: // '\004'
                        Long lo = (Long) this.getValField(fld);
                        stmt.setLong(i + 1, lo.longValue());
                        break;

                    case 5: // '\005'
                        stmt.setFloat(i + 1, ((Float) this.getValField(fld)).floatValue());
                        break;

                    case 6: // '\006'
                        stmt.setDouble(i + 1, ((Double) this.getValField(fld)).doubleValue());
                        break;

                    case 10: // '\n'
                        stmt.setString(i + 1, (String) this.getValField(fld));
                        break;

                    case 21: // '\025'
                        stmt.setDate(i + 1, (Date) this.getValField(fld));
                        break;

                    case 33: // '!'
                        stmt.setInt(i + 1, ((Integer) this.getValField(fld)).intValue());
                        break;
                    case 35: // '!'
                        stmt.setTimestamp(i + 1, ((java.sql.Timestamp) this.getValField(fld)));
                        break;
                    case 36: // '!'
                        stmt.setObject(i + 1, (org.postgis.PGgeometry)this.getValField(fld)); 
                        break;
                }
            }

            stmt.setString(fieldlist.length + 1, getTuppleID());
//      int retour = 1;
            int retour = stmt.executeUpdate();
            if (retour == 0) {
                throw new Exception("Erreur modification dans la table");
            }
            int j = 1;
            return j;
        } catch (Exception e) {
            e.printStackTrace();
            if (cDb != null) {
                cDb.rollback();
            }
            throw new Exception("Erreur update dans la table 4" + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                Log.setLog(String.valueOf(String.valueOf(getClassName())).concat(" - probleme de fermeture d'1 Statement "));
            }
        }
    }

    /**
     * Mise à jour
     * @param apresSet critère apres set dans le requete sql 
     * @param cDb connexion ouverte à la base de donnée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int updateToTable(String apresSet, Connection cDb)
            throws Exception {
        String tableName = getNomTable();
        Statement stmt = null;
        try {
            String sqlQry = "UPDATE " + tableName + " SET " + apresSet;
            stmt = cDb.createStatement();
            int retour = stmt.executeUpdate(sqlQry);
            if (retour == 0) {
                throw new Exception("Erreur modification dans la table");
            }
            return retour;
        } catch (Exception e) {
            e.printStackTrace();
            if (cDb != null) {
                cDb.rollback();
            }
            throw new Exception("Erreur update dans la table 4" + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                Log.setLog(String.valueOf(String.valueOf(getClassName())).concat(" - probleme de fermeture d'1 Statement "));
            }
        }
    }

    /**
     * Mise à jour
     * @param nomAttributValeur nom de l'attribut concerné
     * @param valeur nouveau valeur pour l'attribut concerné
     * @param nomAttributFiltre nom de l'attribut apres where concerné dans le requête sql
     * @param valeurFiltre valeur pour l'attribut apres where concerné
     * @param cDb connexion ouverte à la base de donée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int updateToTable(String nomAttributValeur, String valeur, String nomAttributFiltre, String[] valeurFiltre, Connection cDb)
            throws Exception {
        String apresSet = nomAttributValeur + "=" + valeur + " where ";
        if (nomAttributFiltre == null || valeurFiltre == null || valeurFiltre.length == 0) {
            return updateToTable(apresSet + "1<2", cDb);
        }
        apresSet = apresSet + nomAttributFiltre + " in (";
        for (int i = 0; i < valeurFiltre.length; i++) {
            apresSet = apresSet + "'" + valeurFiltre[i] + "'";
            if (i < valeurFiltre.length - 1) {
                apresSet = apresSet + ",";
            }
        }
        apresSet = apresSet + ")";
        return updateToTable(apresSet, cDb);
    }

    /**
     * Mise à jour
     * @return un nombre de type int
     */
    public int upDateToTable() {
        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            try {
                c = util.GetConn();
                c.setAutoCommit(false);
                int i = updateToTable(c);
                c.commit();
                return i;
            } catch (Exception e) {
                if (c != null) {
                    try {
                        c.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println(String.valueOf(String.valueOf((new StringBuffer("ERREUR UPDATE_TABLE ")).append(getNomTable()).append(" ").append(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (Exception exception1) {
            }
        }
    }

    /**
     * Mise à jour avec historique
     * @param refUser reference de l'utilisateur
     * @param c connexion ouverte à la base de donnée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int updateToTableWithHisto(String refUser, Connection c) throws Exception {

        try {
            MapHistorique histo = new MapHistorique(this.getNomTable(), "update", refUser, this.getTuppleID());
            histo.setObjet(this.getClassName());
            ClassMAPTable obj = (ClassMAPTable) (Class.forName(this.getClassName()).newInstance());
            System.out.println(" and " + obj.getAttributIDName() + " = '" + histo.getRefObjet() + "'");
            if (obj != null) {
                ClassMAPTable[] tabObj = (ClassMAPTable[]) CGenUtil.rechercher(obj, null, null, c, " and " + obj.getAttributIDName() + " = '" + histo.getRefObjet() + "'");
                Historique_valeur valeur = new Historique_valeur();
                if (tabObj.length != 0 && getEstHistorise()) {
                    Field[] listeField = this.getFieldList();
                    for (int i = 0; i < listeField.length; i++) {
//                    String nomVal = "setVal"+i;
                        int temp = i + 1;
//                    String get = "get"+Utilitaire.convertDebutMajuscule(listeField[i].getName());
//                        System.out.println(listeField[i].getName() + " : "+ tabObj[0].getValInsert(listeField[i].getName()));
                        valeur.setValChamp("val" + temp, listeField[i].getName() + ":" + tabObj[0].getValInsert(listeField[i].getName()));
                    }
                    valeur.setIdhisto(histo.getIdHistorique());
                    valeur.setRefhisto(histo.getRefObjet());
                    valeur.setNom_table(this.getValInsert("nomTable"));
                    valeur.setNom_classe(this.getClassName());
                    valeur.setVal40(getMemo());
                    valeur.construirePK(c);
                    valeur.insertToTable(c);
                }
            }
            System.out.println("15");
            return this.updateToTableWithHisto(histo, c);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ERREUR UPDATE 2" + e.getMessage());
        }

    }

    /**
     * Mise à jour avec historique
     * @param refUser reference de l'utlisateur 
     * @return un nombre de type int
     * @throws Exception 
     */
    public int updateToTableWithHisto(String refUser) throws Exception {

        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            c = util.GetConn();
            c.setAutoCommit(false);
            int i = updateToTableWithHisto(refUser, c);
            c.commit();
            return i;
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new Exception("ERREUR Update 1 ".concat(String.valueOf(String.valueOf(e.getMessage()))));
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (Exception exception1) {
            }
        }

    }

    /**
     * Ajout avec historique
     * @param refUser reference de l'utlisateur 
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertToTableWithHisto(String refUser) throws Exception {
        try {
            MapHistorique histo = new MapHistorique(this.getNomTable(), "insert", refUser, this.getTuppleID());
            return insertToTable(histo);
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Ajout avec historique
     * @param refUser reference de l'utlisateur 
     * @param c connection ouverte à la base de donnée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertToTableWithHisto(String refUser, java.sql.Connection c) throws Exception {
        try {
            MapHistorique histo = new MapHistorique(this.getNomTable(), "insert", refUser, this.getTuppleID());
            histo.setObjet(this.getClassName());
            return insertToTable(histo, c);
        } catch (Exception e) {
            if(c!=null)c.rollback();
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Mise à jour avec historique
     * @param histo classe mapping
     * @param c connection ouverte à la base de donnée
     * @return un nombre de type int
     * @throws Exception 
     */
    public int updateToTableWithHisto(ClassMAPTable histo, Connection c)
            throws Exception {
        UtilDB util = new UtilDB();
        try {
            histo.insertToTable(c);
            return (updateToTable(c));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ERREUR UPDATE_TABLE 3 " + e.getMessage());
        }
    }

    /**
     * Mise à jour avec historique
     * @param histo classe mapping
     * @return un nombre de type int
     * @throws ErreurDAO 
     */
    public int updateToTableWithHisto(ClassMAPTable histo)
            throws ErreurDAO {
        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            c = util.GetConn();
            c.setAutoCommit(false);
            histo.insertToTable(c);

            int i = updateToTable(c);
            c.commit();
            int j = 1;
            return j;
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new ErreurDAO(String.valueOf(String.valueOf((new StringBuffer("ERREUR UPDATE_TABLE ")).append(getNomTable()).append(" ").append(e.getMessage()))));
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (Exception exception1) {
            }
        }
    }
    
    /**
     * Ajout sans historique
     * @param histo classe mapping 
     * @param c connection de la base donnée 
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertToTable(ClassMAPTable histo, Connection c) throws Exception {

        try {
            int i = insertToTable(c);
            return (histo.insertToTable(c));
        } catch (Exception e) {
            if(c!=null)c.rollback();
            throw e;
        }
    }

    /**
     * Ajout sans historique
     * @param histo classe mapping
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertToTable(ClassMAPTable histo)
            throws Exception {
        UtilDB util = new UtilDB();
        Connection c = null;
        try {
            c = util.GetConn();
            c.setAutoCommit(false);
            int i = insertToTable(histo, c);
            c.commit();
            return i;
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new Exception(e.getMessage());
                }
            }
            throw new Exception("ERREUR INSERT_TABLE ".concat(String.valueOf(String.valueOf(e.getMessage()))));
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (Exception exception1) {
            }
        }
    }

    /**
     * Ajout sans historique
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertToTable() throws Exception {
        UtilDB util = new UtilDB();
        Connection c = null;
        int retour = 0;
        try {
            try {
                c = util.GetConn();
                c.setAutoCommit(false);
                retour = insertToTable(c);
                c.commit();
                int i = retour;
                return i;
            } catch (Exception e) {
                if (c != null) {
                    try {
                        c.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                throw e;
            }
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (Exception e) {
                System.out.println("ERREUR FERMETURE CONNECTION INSERT_TABLE ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    /**
     * Resaka édition de paie sans histo
     * @param refUser reference d'utilisateur
     * @param cDb connection ouverte à la base de donnée
     * @param idpersonnel identifacation du personnel
     * @param idelementpaie
     * @param mois
     * @param annee
     * @param datedebut
     * @param datefin
     * @param gain
     * @param retenue
     * @return
     * @throws Exception 
     */
    //insert paie_edition tsy misy reflexion
    public int insertIntoTablePaieEdition(String refUser, java.sql.Connection cDb, String idpersonnel, String idelementpaie, int mois, int annee, Date datedebut, Date datefin, double gain, double retenue) throws Exception {
        PreparedStatement stmt = null;
        try {
            String sqlQry = "INSERT INTO PAIE_EDITION(ID, IDPERSONNEL , IDELEMENTPAIE, MOIS, ANNEE, GAIN, RETENUE, DATEDEBUT, DATEFIN) VALUES (";
            String sqlTmp = "'ELP'||seq_paie_edition.nextval";
            sqlTmp = sqlTmp + " , ? , ? , ? , ? , ? , ? , ? , ? ";
            sqlQry = sqlQry + sqlTmp + ")";

            stmt = cDb.prepareStatement(sqlQry);
            stmt.setString(1, (String) idpersonnel);
            stmt.setString(2, (String) idelementpaie);
            stmt.setInt(3, mois);
            stmt.setInt(4, annee);
            stmt.setDouble(5, (Double) gain);
            stmt.setDouble(6, (Double) retenue);
            stmt.setDate(7, (Date) datedebut);
            stmt.setDate(8, (Date) datefin);

            int retour = stmt.executeUpdate();
            if (retour == 0) {
                throw new Exception("Erreur insertion dans la table paie_edition");
            }

            return insertPaieEditionIntoHistorique(cDb, refUser);
        } catch (Exception e) {
            try {
                cDb.rollback();
                throw new Exception("Erreur insertion dans la table paie_edition ref= " + getTuppleID());
            } catch (Exception ex) {
                return 0;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                Log.setLog("paie_edition - probleme de fermeture d'1 Statement ");
            }
        }
    }

    /**
     * Resaka edition de paie avec historique
     * @param cDb connection ouverte à la base de donnée
     * @param refUser reference de l'utilisateur
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertPaieEditionIntoHistorique(Connection cDb, String refUser) throws Exception {
        PreparedStatement stmt = null;
        try {
            String sqlQry = "INSERT INTO HISTORIQUE(IDHISTORIQUE , DATEHISTORIQUE, HEURE, OBJET, ACTION, IDUTILISATEUR, REFOBJET) VALUES (";
            String sqlTmp = "'ELP'||SEQ_HISTORIQUE.nextval";
            sqlTmp = sqlTmp + " , ? , ? , ? , ? , ? , ? ";
            sqlQry = sqlQry + sqlTmp + ")";

            stmt = cDb.prepareStatement(sqlQry);
            stmt.setDate(1, (Date) Utilitaire.dateDuJourSql());
            stmt.setString(2, (String) Utilitaire.heureCourante());
            stmt.setString(3, (String) this.getClassName());
            stmt.setString(4, (String) "insert");
            stmt.setString(5, (String) refUser);
            stmt.setString(6, (String) this.getTuppleID());

            int retour = stmt.executeUpdate();
            if (retour == 0) {
                throw new Exception("Erreur insertion dans la table historique");
            }

            int j = 1;
            return j;
        } catch (Exception e) {
            try {
                cDb.rollback();
                throw new Exception("Erreur insertion dans la table HISTORIQUE ref= " + getTuppleID());
            } catch (Exception ex) {
                return 0;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                Log.setLog("HISTORIQUE - probleme de fermeture d'1 Statement ");
            }
        }
    }
    //mifarana eto ny insert paie_edtion tsy misy reflexion

    /**
     * Ajout
     * @param cDb conection ouverte à la base de donnée 
     * @return un nombre de type int
     * @throws Exception 
     */
    public int insertToTable(Connection cDb)
            throws Exception {
//        cDb = new ConnectionSpy(cDb);
        String tableName = getNomTable();
        PreparedStatement stmt = null;
        Field fld = null;
        String sqlQry = "INSERT INTO " + tableName + " ( ";
        String sqlTmp = " ) VALUES ( ";
        if (this instanceof ClassEtat) {
            ClassEtat temp = (ClassEtat) this;
            if (temp.getIduser() == null || temp.getIduser().compareTo("") == 0) {
                temp.setIduser("0");
            }
        }
        if (this instanceof ClassUser) {
            ClassUser temp = (ClassUser) this;
            if (temp.getIduser() == null || temp.getIduser().compareTo("") == 0) {
                temp.setIduser("0");
            }
        }
        try {

            Field[] fieldlist = ListeColonneTable.getChampBase(this, cDb);
            for (int i = 0; i < fieldlist.length; i++) {
                fld = fieldlist[i];
                sqlQry = sqlQry + fld.getName() + " ";
                if ((fld.getName().compareToIgnoreCase(this.getAttributIDName()) == 0) && this.getNomSequenceDirecte() != null && this.getNomSequenceDirecte().compareToIgnoreCase("") != 0) {
                    sqlTmp = sqlTmp + "'" + this.getINDICE_PK() + "'||NEXTVAL('" + getNomSequenceDirecte() + "')";
                } else {
                    sqlTmp = sqlTmp + "? ";
                }
                if (i + 1 < fieldlist.length) {

                    sqlQry = sqlQry + ", ";
                    sqlTmp = sqlTmp + ", ";
                }
            }

            sqlQry = sqlQry + sqlTmp + " ) ";

            stmt = cDb.prepareStatement(sqlQry);
            int indicePrepared = 0;
            for (int i = 0; i < fieldlist.length; i++, indicePrepared++) {
                fld = fieldlist[i];

                switch (getTypeMAPField(fld)) {
                    case 0: // '\0'
                        stmt.setBoolean(indicePrepared + 1, ((Boolean) this.getValField(fld)).booleanValue());
                        break;

                    case 1: // '\001'
                        stmt.setByte(indicePrepared + 1, ((Byte) this.getValField(fld)).byteValue());
                        break;

                    case 2: // '\002'
                        stmt.setShort(indicePrepared + 1, ((Short) this.getValField(fld)).shortValue());
                        break;

                    case 3: // '\003'
                        Integer it = (Integer) this.getValField(fld);
                        stmt.setInt(indicePrepared + 1, it.intValue());
                        break;

                    case 4: // '\004'
                        Long lo = (Long) this.getValField(fld);
                        stmt.setLong(indicePrepared + 1, lo.longValue());
                        break;

                    case 5: // '\005'
                        stmt.setFloat(indicePrepared + 1, ((Float) this.getValField(fld)).floatValue());
                        break;

                    case 6: // '\006'
                        stmt.setDouble(indicePrepared + 1, ((Double) this.getValField(fld)).doubleValue());
                        break;

                    case 10: // '\n'
                        if ((fld.getName().compareToIgnoreCase(this.getAttributIDName()) != 0) || this.getNomSequenceDirecte() == null || this.getNomSequenceDirecte().compareToIgnoreCase("") == 0) {
                            stmt.setString(indicePrepared + 1, (String) this.getValField(fld));
                        } else {
                            indicePrepared = indicePrepared - 1;
                        }
                        break;

                    case 21: // '\025'
                        stmt.setDate(indicePrepared + 1, (Date) this.getValField(fld));
                        break;

                    case 33: // '!'
                        stmt.setInt(indicePrepared + 1, ((Integer) this.getValField(fld)).intValue());
                        break;

                    case 34: // '!'
                        stmt.setInt(indicePrepared + 1, ((Integer) this.getValField(fld)).intValue());
                        break;
                    case 35: // '!'
                        stmt.setTimestamp(indicePrepared + 1, ((java.sql.Timestamp) this.getValField(fld)));
                        break;
                    case 36: // '!'
                        stmt.setObject(indicePrepared + 1,(org.postgis.PGgeometry) this.getValField(fld));
                        break;
                }
            }
            //System.out.println(" requette : " + stmt.toString());
//            System.out.println("req : "+stmt.toString());

            int retour = stmt.executeUpdate();
            if (retour == 0) {
                throw new Exception("Erreur insertion dans la table ");
            }
            
            //int j = 1;
            return retour;
        } catch (Exception e) {
            try {
                cDb.rollback();

                e.printStackTrace();

                throw new Exception("Erreur insertion dans la table " + getNomTable() + " " + e.getMessage() + " ref= " + getTuppleID());
            } catch (Exception ex) {
                return 0;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                Log.setLog(String.valueOf(String.valueOf(getClassName())).concat(" - probleme de fermeture d'1 Statement "));
            }
        }
    }

    /**
     * Avoir les informations reçues dans le curseur
     * @param rs resultat apres le requete
     * @throws ClassMAPTableException 
     */
    public void getInfoFromCursor(ResultSet rs)
            throws ClassMAPTableException {
        try {
            Field fieldlist[] = getFieldList();
            int typeClass = 0;
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                typeClass = getTypeMAPField(fld);
                switch (getTypeMAPField(fld)) {
                    case 0: // '\0'
                        fld.setBoolean(this, rs.getBoolean(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur boolean : ").append(fld.getBoolean(this)))));
                        break;

                    case 1: // '\001'
                        fld.setByte(this, rs.getByte(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur byte : ").append(fld.getByte(this)))));
                        break;

                    case 2: // '\002'
                        fld.setShort(this, rs.getShort(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur Short : ").append(fld.getShort(this)))));
                        break;

                    case 3: // '\003'
                        fld.setInt(this, rs.getInt(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur int : ").append(fld.getInt(this)))));
                        break;

                    case 4: // '\004'
                        fld.setLong(this, rs.getLong(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur long : ").append(fld.getLong(this)))));
                        break;

                    case 5: // '\005'
                        fld.setFloat(this, rs.getFloat(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur Float : ").append(fld.getFloat(this)))));
                        break;

                    case 6: // '\006'
                        fld.setDouble(this, rs.getDouble(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur double : ").append(fld.getDouble(this)))));
                        break;

                    case 10: // '\n'
                        Log.setLog(String.valueOf(String.valueOf(getClassName())).concat(" - TYPE STRING "));
                        fld.set(this, rs.getString(fld.getName()));
                        Log.setLog(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getClassName())))).append(" - Valeur String : ").append((String) fld.get(this)))));
                        break;
                }
            }

        } catch (Exception e) {
            throw new ClassMAPTableException(e.getMessage());
        }
    }

    /**
     * 
     * La classe Utilitaire possède la même fonction
     * @param daty date
     * @return  un nombre de type int
     */
    public static int getMaxColonneFactFin(String daty) {
        UtilDB util = new UtilDB();
        Connection c = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            try {
                String an = Utilitaire.getAnnee(daty);
                c = null;
                c = util.GetConn();
                cs = c.prepareStatement(String.valueOf(String.valueOf((new StringBuffer("select * from  seqFact where daty<='31/12/")).append(an).append("' and daty>='01/01/").append(an).append("'"))));
                rs = cs.executeQuery();
                int i = 0;
                if (rs.next()) {
                    i++;
                }
                System.out.println("sasa ".concat(String.valueOf(String.valueOf(i))));
                if (i == 0) {
                    int k = 0;
                    return k;
                }
                int l = (new Integer(rs.getString(1))).intValue();
                return l;
            } catch (SQLException e) {
                System.out.println("getMaxSeq : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cs != null) {
                    cs.close();
                }
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture SQL RechercheType ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    /**
     * Obtenir la séquence max
     * @param daty date 
     * @param entite entite
     * @return un nombre de type int
     */
    public static int getMaxColonneFactFinEntite(String daty, String entite) {
        UtilDB util = new UtilDB();
        Connection c = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            try {
                String an = Utilitaire.getAnnee(daty);
                c = null;
                c = util.GetConn();
                cs = c.prepareStatement(String.valueOf(String.valueOf((new StringBuffer("select * from  seqFact where entite='" + entite + "' and daty<='31/12/")).append(an).append("' and daty>='01/01/").append(an).append("'"))));
                rs = cs.executeQuery();
                int i = 0;
                if (rs.next()) {
                    i++;
                }
                if (i == 0) {
                    int k = 0;
                    return k;
                }
                int l = (new Integer(rs.getString(1))).intValue();
                return l;
            } catch (SQLException e) {
                System.out.println("getMaxSeq : ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            int j = 0;
            return j;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cs != null) {
                    cs.close();
                }
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture SQL RechercheType ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    public int getNombreChamp() {
        return nombreChamp;
    }

    /**
     * Préparer la clé primaire de la classe
     * @param indicePK indice de la clé primaire
     * @param fonct nom de la procédure du sequence
     * @throws Exception 
     */
    public void preparePk(String indicePK, String fonct) throws Exception {
        setIndicePk(indicePK);
        setNomProcedureSequence(fonct);
    }

    /**
     * Générer une clé primaire interne
     * @throws Exception
     */
    public void genererPKInterne() throws Exception {
        String nomMethode = "set" + Utilitaire.convertDebutMajuscule(this.getAttributIDName());
        String[] args = {makePK()};
        Class[] paramT = {new String().getClass()};
        this.getClass().getMethod(nomMethode, paramT).invoke(this, args);
    }

    /**
     * Modifier la valeur du champ à afficher selon le type de l'objet
     * @param nomChamp nom de l'attribut
     * @param valeur l'objet concernée
     * @throws Exception 
     */
    public void setValChamp(String nomChamp, Object valeur) throws Exception {
        Field f = getFieldByName(nomChamp);
        String nomMethode = "set" + Utilitaire.convertDebutMajuscule(nomChamp);
        Object[] args = {valeur};
        Class[] paramT = {f.getType()};
        if (f.getType().getName().compareToIgnoreCase("java.lang.String") == 0) {
            //bean.CGenUtil.setValChamp(getBase(), f, valeur);
        }
        if (f.getType().getName().compareToIgnoreCase("java.sql.Date") == 0 && valeur instanceof java.lang.String) {
            args[0] = Utilitaire.string_date("dd/MM/yyyy", String.valueOf(valeur));
        }
        if (f.getType().getName().compareToIgnoreCase("double") == 0 && valeur instanceof java.lang.String) {
            args[0] = new Double(Utilitaire.stringToDouble(String.valueOf(valeur)));
        }
        if (f.getType().getName().compareToIgnoreCase("int") == 0 && valeur instanceof java.lang.String) {
            if (valeur == null || String.valueOf(valeur).compareToIgnoreCase("") == 0) {
                args[0] = new Integer(0);
            } else {
                args[0] = new Integer(String.valueOf(valeur));
            }
        }
        if (f.getType().getName().compareToIgnoreCase("float") == 0 && valeur instanceof java.lang.String) {
            args[0] = new Float(Utilitaire.stringToFloat(String.valueOf(valeur)));
        }
        if (f.getType().getName().compareToIgnoreCase("java.sql.Timestamp") == 0 && valeur instanceof java.lang.String) {
            args[0] = Utilitaire.convertStringToTimestampHour(String.valueOf(valeur), ":");
        }
        if (f.getType().getName().compareToIgnoreCase("org.postgis.PGgeometry") == 0 && valeur instanceof java.lang.String) {           
            args[0] = new org.postgis.PGgeometry(String.valueOf(valeur));
        }
        this.getClass().getMethod(nomMethode, paramT).invoke(this, args);
    }

    /**
     * Retourne le champ par son nom
     * @param name nom de l'attribut
     * @return le champs
     * @throws Exception 
     */
    public Field getFieldByName(String name) throws Exception {
        Field[] t = getFieldList();
        for (int i = 0; i < t.length; i++) {
            if (t[i].getName().compareTo(name) == 0) {
                return t[i];
            }
        }
        return null;
    }

    /**
     * Obtenir la valeur du champ en objet
     * @param f champs concerné
     * @return la valeur du champ en objet 
     */
    public Object getValField(Field f) {
        try {
            String nomMethode = "get" + Utilitaire.convertDebutMajuscule(f.getName());
            Object o = this.getClass().getMethod(nomMethode, null).invoke(this, null);
            return o;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Obtenir la valeur du champ en String
     * @param  nomCol nom de la colonne concernée
     * @return  la valeur du champ en String
     */
    public String getValInsert(String nomCol) {
        String o = null;
        try {
            String nomMethode = "get" + Utilitaire.convertDebutMajuscule(nomCol);
            if (this.getClass().getMethod(nomMethode, null).invoke(this, null) != null) {
                o = this.getClass().getMethod(nomMethode, null).invoke(this, null).toString();
            }
            return o;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Retourne la valeur du champ à partir de son rang
     * @param numColonne  rang du champ
     * @return la valeur du cham
     */
    public Object getValeur(int numColonne) {
        try {
            Object o = this.getValField(getFieldList()[numColonne]);
            return o;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Donner valeur à la table pour faire le select
     * @param nomTableSelect nom de la table
     */
    public void setNomTableSelect(String nomTableSelect) {
        this.nomTableSelect = nomTableSelect;
    }

    /**
     * Obtenir la table pour faire le select
     * @return nom de la table
     */
    public String getNomTableSelect() {
        if (nomTableSelect == null || nomTableSelect == "") {
            return this.getNomTable();
        }
        return nomTableSelect;
    }

    /**
     * Obtenir le nom de la procédure pour la sequence
     * @return le nom de la procédure pour la sequence
     */
    public String getNomProcedureSequence() {
        return nomProcedureSequence;
    }

    /**
     * Obtenir la longueur du clé primaire
     * @return la longueur du clé primaire
     */
    public int getLonguerClePrimaire() {
        return longuerClePrimaire;
    }

    /**
     * Obtenir l'indice de la clé primaire
     * @return l'indice de la clé primaire
     */
    public String getINDICE_PK() {
        return INDICE_PK;
    }

    /**
     * Donner valeur à l'indice de la clé primaire
     * @param INDICE_PK
     */
    public void setINDICE_PK(String INDICE_PK) {
        this.INDICE_PK = INDICE_PK;
    }

    /**
     * Donner quelle type est le mode
     * @param mode mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Obtenir la valeur par defaut <strong>modif</strong>
     * @return mode
     */
    public String getMode() {
        return mode;
    }

    static final int TYPE_CLASS_MAP_BOOLEAN = 0;
    static final int TYPE_CLASS_MAP_BYTE = 1;
    static final int TYPE_CLASS_MAP_SHORT = 2;
    static final int TYPE_CLASS_MAP_INT = 3;
    static final int TYPE_CLASS_MAP_LONG = 4;
    static final int TYPE_CLASS_MAP_FLOAT = 5;
    static final int TYPE_CLASS_MAP_DOUBLE = 6;
    static final int TYPE_CLASS_MAP_STRING = 10;
    static final int TYPE_CLASS_MAP_WBYTE = 11;
    static final int TYPE_CLASS_MAP_WSHORT = 12;
    static final int TYPE_CLASS_MAP_WINT = 13;
    static final int TYPE_CLASS_MAP_WLONG = 14;
    static final int TYPE_CLASS_MAP_WFLOAT = 15;
    static final int TYPE_CLASS_MAP_WDOUBLE = 16;
    static final int TYPE_CLASS_MAP_DATE = 21;
    static final int TYPE_CLASS_MAP_TIME = 22;
    static final int TYPE_CLASS_MAP_BLOB = 31;
    static final int TYPE_CLASS_MAP_CLOB = 32;
    static final int TYPE_CLASS_MAP_INTEGER = 33;
    String nomTable;
    Vector champ;
    int nombreChamp;
    Class cls;
    String INDICE_PK;
    String nomProcedureSequence;
    int longuerClePrimaire;
    private String nomTableSelect;
    private String mode = "modif";
    private String columnlibelle = null;
    private boolean groupe = false;
    private int nombrepargroupe;
    String nomSequenceDirecte;
    boolean estHistorise = false;
    String memo = "";
    String attrMere;
    String classMere;

    /**
     *  Obtenir le libellé  de la colonne
     * @return 
     */
    public String getColumnlibelle() {
        return columnlibelle;
    }

    /**
     * Donner du nom à la colonne comme libellé
     * @param columnlibelle nom 
     */
    public void setColumnlibelle(String columnlibelle) {
        this.columnlibelle = columnlibelle;
    }

    /**
     * Obtenir l'attribut mère
     * @return attribut mère
     */
    public String getAttrMere() {
        return attrMere;
    }

    /**
     * Donner valeur à l'attibut mère
     * @param attrMere attibut mère
     */
    public void setAttrMere(String attrMere) {
        this.attrMere = attrMere;
    }

    /**
     * Obtenir la classe mère
     * @return classe mère 
     */
    public String getClassMere() {
        return classMere;
    }

    /**
     * Donner valeur à la classe mère
     * @param classMere classe mère
     */
    public void setClassMere(String classMere) {
        this.classMere = classMere;
    }

    public Vector getChamp() {
        return champ;
    }

    public void setChamp(Vector champ) {
        this.champ = champ;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean getEstHistorise() {
        return estHistorise;
    }

    public void setEstHistorise(boolean estHistorise) {
        this.estHistorise = estHistorise;
    }

    public String getNomSequenceDirecte() {
        return nomSequenceDirecte;
    }

    public void setNomSequenceDirecte(String nomSequenceDirecte) {
        this.nomSequenceDirecte = nomSequenceDirecte;
    }

    public void setNomSequenceDirecte(String nomSequenceDirecte, String indicePK) {
        this.setNomSequenceDirecte(nomSequenceDirecte);
        this.setIndicePk(indicePK);
    }

    public int getNombrepargroupe() {
        return nombrepargroupe;
    }

    public void setNombrepargroupe(int nombrepargroupe) {
        this.nombrepargroupe = nombrepargroupe;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public boolean getGroupe() {
        return groupe;
    }

    public void setGroupe(boolean groupe) {
        this.groupe = groupe;
    }

    public String getIdLibelle() {
        return this.getTuppleID();
    }
    
    public void setTuppleId(String val)throws Exception
    {
        this.setValChamp(this.getAttributIDName(), val);
    }
    
    /**
     * Sélectionne un objet à partir de son id
     * @param id identifiant
     * @param nTable nom de la table dans la base de donnée
     * @param c connexion ouverte à la base de donnée
     * @return
     * @throws Exception 
     */
    public ClassMAPTable getById(String id,String nTable,Connection c) throws Exception
    {
        boolean estOuvert=false;
        try
        {
            if(nTable!=null)this.setNomTable(nTable);
            if(c==null)
            {
                c=new UtilDB().GetConn();
                estOuvert=true;
            }
            this.setTuppleId(id);
            ClassMAPTable[] valiny= (ClassMAPTable[]) CGenUtil.rechercher(this, null, null, c, "");
            if(valiny.length>0)return valiny[0];
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if(c!=null&&estOuvert==true)c.rollback();
            throw e;
        }
        finally
        {
            if(c!=null&&estOuvert==true)c.close();
        }
        
    }
    
    public void testDelete(Connection c) throws Exception {
        try {
            if (this instanceof ClassEtat) {
                String id = getValInsert("id");
                if (Utilitaire.stringToInt(this.getValInsert("etat")) >= ConstanteEtat.getEtatValider()) {
                    throw new Exception("IMPOSSIBLE DE SUPPRIMER. OBJET DEJA VISER");
                }

            }
            if(getAttrMere()!=null && getClassMere()!=null){
                ClassMAPTable crt = (ClassMAPTable) Class.forName(getClassMere()).newInstance();
                crt.setTuppleId(getValInsert(getAttrMere()));
                ClassMAPTable[] listeMere = (ClassMAPTable[]) CGenUtil.rechercher(crt, null, null, c, "");
                if(listeMere.length>0){
                    listeMere[0].testDelete(c);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ClassMAPTable dupliquer(String user,Connection c) throws Exception
    {
        boolean estOuvert=false;
        try{
            if(c==null)
            {
                estOuvert=true;
                c=new UtilDB().GetConn();
            }
            ClassMAPTable retour=(ClassMAPTable)Class.forName(this.getClassName()).newInstance();
            Field[] listeC=this.getFieldList();
            for(Field f:listeC)
            {
                CGenUtil.setValChamp(retour, f, CGenUtil.getValeurFieldByMethod(this, f) );
            }
            retour.construirePK(c);
            return retour;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(c!=null && estOuvert==true)c.close();
        }
    } 
}
