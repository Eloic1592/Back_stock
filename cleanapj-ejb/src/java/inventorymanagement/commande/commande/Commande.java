package inventorymanagement.commande.commande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import inventorymanagement.commande.detailcommande.Detailcommande;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commande extends MappedInteger {
    String idcommande;
    String idclient;
    Timestamp datecommande;
    int statut;
    String libelle;
    Detailcommande [] detailcommandes;


    public Commande() {
        super.setNomTable("commande");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("commande");
        this.preparePk("COM", "getseqcommande");
        this.setIdcommande(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idcommande;
    }

    @Override
    public String getAttributIDName() {
        return "idcommande";
    }


    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public Timestamp getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Timestamp datecommande) {
        this.datecommande = datecommande;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Detailcommande[] getDetailcommandes() {
        return detailcommandes;
    }

    public void setDetailcommandes(Detailcommande[] detailcommandes) {
        this.detailcommandes = detailcommandes;
    }
}
