package inventorymanagement.commande.detailcommande;

import itusolar.prepare.MappedInteger;

public class Detailcommandeview extends MappedInteger {
    String iddetailcommande;
    String idcommande;
    String marque;
    String modele;
    String description;
    String descarticle;
    double quantite;
    double pu;
    double total;

    public Detailcommandeview() {
        setNomTable("detail_commande");
    }

    public String getIddetailcommande() {
        return iddetailcommande;
    }

    public void setIddetailcommande(String iddetailcommande) {
        this.iddetailcommande = iddetailcommande;
    }

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescarticle() {
        return descarticle;
    }

    public void setDescarticle(String descarticle) {
        this.descarticle = descarticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
