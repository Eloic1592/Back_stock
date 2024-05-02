package inventorymanagement.etudiant;

import bean.CGenUtil;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;

public class ListeEtudiant extends MappedInteger {
    String idetudiant;
    String nom;
    String prenom;
    String mail;
    String sexe;

    public ListeEtudiant() {
        setNomTable("liste_etudiant");
    }


    public String getIdetudiant() {
        return idetudiant;
    }

    public void setIdetudiant(String idetudiant) {
        this.idetudiant = idetudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public static void main(String[] args) throws Exception {
        Connection c = null;
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            ListeEtudiant[] etudiants = (ListeEtudiant[]) CGenUtil.rechercher(new ListeEtudiant(), null, null, c, "");
            for (int i = 0; i < etudiants.length; i++) {
                System.out.println(etudiants[i].getIdetudiant());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif == 1) {
                c.close();
            }
        }
    }
}
