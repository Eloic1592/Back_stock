package inventorymanagement.depot;

import itusolar.prepare.MappedInteger;

public class Stocktypematerieldepot extends MappedInteger {
    double nombre;
    String idtypemateriel;
    String typemateriel;
    String iddepot;
    String depot;
    String val;

    public Stocktypematerieldepot() {
        setNomTable("stock_typemateriel_depot");
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
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

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
