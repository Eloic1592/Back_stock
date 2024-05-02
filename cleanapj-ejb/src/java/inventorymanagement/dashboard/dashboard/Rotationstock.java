package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Rotationstock extends MappedInteger {
    int annee;
    int mois;
    String nommois;
    double rotationstockquantite;
    double rotationstockvaleur;

    public Rotationstock() {
        setNomTable("rotation_stock");
    }

    public Rotationstock(int annee, int mois, double rotationstockquantite, double rotationstockvaleur) {
        this.annee = annee;
        this.mois = mois;
        this.rotationstockquantite = rotationstockquantite;
        this.rotationstockvaleur = rotationstockvaleur;
    }

    public Rotationstock(int annee, int mois, String nommois, double rotationstockquantite, double rotationstockvaleur) {
        this.annee = annee;
        this.mois = mois;
        this.nommois = nommois;
        this.rotationstockquantite = rotationstockquantite;
        this.rotationstockvaleur = rotationstockvaleur;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getRotationstockquantite() {
        return rotationstockquantite;
    }

    public void setRotationstockquantite(double rotationstockquantite) {
        this.rotationstockquantite = rotationstockquantite;
    }

    public double getRotationstockvaleur() {
        return rotationstockvaleur;
    }

    public void setRotationstockvaleur(double rotationstockvaleur) {
        this.rotationstockvaleur = rotationstockvaleur;
    }

    public String getNommois() {
        return nommois;
    }

    public void setNommois(String nommois) {
        this.nommois = nommois;
    }
}
