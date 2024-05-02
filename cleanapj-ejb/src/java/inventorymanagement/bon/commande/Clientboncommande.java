package inventorymanagement.bon.commande;

import bean.CGenUtil;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Timestamp;

public class Clientboncommande extends MappedInteger {
    String idboncommande;
    Timestamp dateboncommande;
    String nom;
    String nif;
    String numstat;
    String adresse;
    String telephone;
    String idproforma;
    Timestamp datevalidation;

    public Clientboncommande() {
        super.setNomTable("client_commande");
    }

    public String getIdboncommande() {
        return idboncommande;
    }

    public void setIdboncommande(String idboncommande) {
        this.idboncommande = idboncommande;
    }

    public Timestamp getDateboncommande() {
        return dateboncommande;
    }

    public void setDateboncommande(Timestamp dateboncommande) {
        this.dateboncommande = dateboncommande;
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

    public static void main(String[] args) throws Exception {
        Connection c = null;
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            Clientboncommande[] clientcommandes = (Clientboncommande[]) CGenUtil.rechercher(new Clientboncommande(), null, null, c,"");
            for (int i = 0; i < clientcommandes.length; i++) {
                System.out.println(clientcommandes[i].getNom());
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
