/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import utilitaire.UtilDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Cette presente les caractéristique de la table (nom, nombre de colone , les attributs)
 * @author BICI
 */

public class Table {

    String nomTable;
    int nombreCol;
    Champ champ[];

    /**
     * Constructeur par defaut 
     */
    public Table() {

    }

    /**
     * Constructeur
     * @param nom nom de la table
     */
    public Table(String nom) {
        nomTable = nom;
        //findInfoTable();
    }

    /**
     * Constructeur avec connexion
     * @param nom  nom de la table
     * @param c connexion ouverte à la base de donnée
     */
    public Table(String nom, Connection c) {
        nomTable = nom;
        //findInfoTable(c);
    }

    /**
     * Obtenir les champs
     * @return les champs
     */
    public Champ[] getChamp() {
        findInfoTable();
        return champ;
    }

    /**
     * Obtenir le nom de la table
     * @return nom de la table
     */
    public String getNomTable() {
        return nomTable;
    }

    /**
     * Cette fonction utilise la fonction <pre>findInfoTable(Connection c)</pre>
     */
    public void findInfoTable() {
        UtilDB util = null;
        Connection c = null;
        try {
            util = new UtilDB();
            c = util.GetConn();
            findInfoTable(c);
        } catch (Exception s) {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("RechercheExecution44444 ")).append(getNomTable()).append(" par Reference ").append(s.getMessage()))));
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                util.close_connection();
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture SQL RechercheExecution ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    /**
     * Les informations d'une table
     * @param c Connexion ouverte à la base de donnée
     */
    public void findInfoTable(Connection c) {
        UtilDB util = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //String param = "SELECT column_name,data_type,character_maximum_length,numeric_precision FROM information_schema.columns WHERE  upper(table_name)  = upper(?) order by ordinal_position asc";
            String param = "SELECT * FROM USER_TAB_COLUMNS where table_Name = upper(?) order by column_id asc";
            st = c.prepareStatement(param);
            st.setString(1, nomTable);
            rs = st.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                //v.add(new Champ(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
                v.add(new Champ(rs.getString(2), rs.getString(3), rs.getInt(7), rs.getInt(8)));
            }
            champ = new Champ[v.size()];
            v.copyInto(champ);
        } catch (Exception s) {
            s.printStackTrace();
            System.out.println("findInfoTable " + getNomTable() + " par Reference ");
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture SQL RechercheExecution ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
        }
    }

    /**
     * Setter le nom de la table
     * @param nomTable Nom de la table
     */
    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
    }



}
