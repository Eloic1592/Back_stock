package inventorymanagement.materiel.materiel;

import itusolar.prepare.MappedInteger;

public class Stocktypemateriel extends MappedInteger {
    String idtypemateriel;
    String typemateriel;
    double libre;
    double occupe;

    public Stocktypemateriel() {
        setNomTable("stock_typemateriel");
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public double getLibre() {
        return libre;
    }

    public void setLibre(double libre) {
        this.libre = libre;
    }

    public double getOccupe() {
        return occupe;
    }

    public void setOccupe(double occupe) {
        this.occupe = occupe;
    }
}
