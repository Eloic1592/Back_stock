package inventorymanagement.bon.livraison;

import bean.CGenUtil;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Timestamp;

public class Clientlivraison extends MappedInteger {
    String idbonlivraison;
    String idboncommande;
    Timestamp datebonlivraison;
    String nom;
    String nif;
    String numstat;
    String adresse;
    String telephone;
    String idproforma;
    Timestamp dateboncommande;
    Timestamp datevalidation;

    public Clientlivraison() {
        setNomTable("client_livraison");
    }

    public String getIdbonlivraison() {
        return idbonlivraison;
    }

    public void setIdbonlivraison(String idbonlivraison) {
        this.idbonlivraison = idbonlivraison;
    }

    public String getIdboncommande() {
        return idboncommande;
    }

    public void setIdboncommande(String idboncommande) {
        this.idboncommande = idboncommande;
    }

    public Timestamp getDatebonlivraison() {
        return datebonlivraison;
    }

    public void setDatebonlivraison(Timestamp datebonlivraison) {
        this.datebonlivraison = datebonlivraison;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(String idproforma) {
        this.idproforma = idproforma;
    }

    public Timestamp getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(Timestamp datevalidation) {
        this.datevalidation = datevalidation;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNumstat() {
        return numstat;
    }

    public void setNumstat(String numstat) {
        this.numstat = numstat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Timestamp getDateboncommande() {
        return dateboncommande;
    }

    public void setDateboncommande(Timestamp dateboncommande) {
        this.dateboncommande = dateboncommande;
    }

    public static void main(String[] args) throws Exception {
        Connection c = null;
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            Clientlivraison[] clientlivraisons = (Clientlivraison[]) CGenUtil.rechercher(new Clientlivraison(), null, null, c,"");
            for (int i = 0; i < clientlivraisons.length; i++) {
                System.out.println(clientlivraisons[i].getNom());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif == 1) {
                c.close();
            }
        }
    }
}
