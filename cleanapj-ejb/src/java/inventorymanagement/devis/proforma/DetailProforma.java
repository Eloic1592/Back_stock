package inventorymanagement.devis.proforma;

import itusolar.prepare.MappedInteger;

public class DetailProforma extends MappedInteger {
    String idproforma;
    String iddetaildevis;
    String iddevis;
    String marque;
    String modele;
    String description;
    double quantite;
    double pu;
    double total;

    public DetailProforma() {
        setNomTable("detail_proforma");
    }

    public String getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(String idproforma) {
        this.idproforma = idproforma;
    }

    public String getIddetaildevis() {
        return iddetaildevis;
    }

    public void setIddetaildevis(String iddetaildevis) {
        this.iddetaildevis = iddetaildevis;
    }

    public String getIddevis() {
        return iddevis;
    }

    public void setIddevis(String iddevis) {
        this.iddevis = iddevis;
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
