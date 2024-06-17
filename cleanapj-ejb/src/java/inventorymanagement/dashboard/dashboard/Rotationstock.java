package inventorymanagement.dashboard.dashboard;

import itusolar.prepare.MappedInteger;

public class Rotationstock extends MappedInteger {
    int annee;
    double cmv;
    double rotationstockquantite;
    double rotationstockvaleur;

    public Rotationstock() {
        setNomTable("rotation_stock");
    }


    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getCmv() {
        return cmv;
    }

    public void setCmv(double cmv) {
        this.cmv = cmv;
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
}
