package inventorymanagement.stockage.inventaire;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventaire extends MappedInteger {
    String idinventaire;
    String idarticle;
    double quantitereel;
    double quantitetheorique;
    Timestamp dateinventaire;
    int statut;
    int etatinventaire;
    String description;
    String idmateriel;

    public Inventaire() {
        this.setNomTable("inventaire");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("inventaire");
        this.preparePk("INV", "getseqinventaire");
        this.setIdinventaire(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idinventaire;
    }

    @Override
    public String getAttributIDName() {
        return "idinventaire";
    }

    public String getIdinventaire() {
        return idinventaire;
    }

    public void setIdinventaire(String idinventaire) {
        this.idinventaire = idinventaire;
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public double getQuantitereel() {
        return quantitereel;
    }

    public void setQuantitereel(double quantitereel) {
        this.quantitereel = quantitereel;
    }

    public double getQuantitetheorique() {
        return quantitetheorique;
    }

    public void setQuantitetheorique(double quantitetheorique) {
        this.quantitetheorique = quantitetheorique;
    }

    public Timestamp getDateinventaire() {
        return dateinventaire;
    }

    public void setDateinventaire(Timestamp dateinventaire) {
        this.dateinventaire = dateinventaire;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getEtatinventaire() {
        return etatinventaire;
    }

    public void setEtatinventaire(int etatinventaire) {
        this.etatinventaire = etatinventaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(String idmateriel) {
        this.idmateriel = idmateriel;
    }
}
