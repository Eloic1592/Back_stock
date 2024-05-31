package inventorymanagement.emplacement;

import itusolar.prepare.MappedInteger;

public class Listeemplacement extends MappedInteger {
    String idemplacement;
    String iddepot;
    String depot;
    String codedep;
    String codeemp;
    double capacite;


    public Listeemplacement() {
        setNomTable("liste_emplacement");
    }

    public String getIdemplacement() {
        return idemplacement;
    }

    public void setIdemplacement(String idemplacement) {
        this.idemplacement = idemplacement;
    }

    public String getDepot() {
        return depot;
    }

    public String getIddepot() {
        return iddepot;
    }

    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getCodedep() {
        return codedep;
    }

    public void setCodedep(String codedep) {
        this.codedep = codedep;
    }

    public String getCodeemp() {
        return codeemp;
    }

    public void setCodeemp(String codeemp) {
        this.codeemp = codeemp;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
}
