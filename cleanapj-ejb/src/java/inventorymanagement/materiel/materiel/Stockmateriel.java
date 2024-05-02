package inventorymanagement.materiel.materiel;

import itusolar.prepare.MappedInteger;

public class Stockmateriel extends MappedInteger {
    double libre;
    double occupe;
    double total;
    String idtypemateriel;
    String typemateriel;

    public Stockmateriel() {
        setNomTable("stock_materiel");
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
