package inventorymanagement.materiel.materiel;

import itusolar.prepare.MappedInteger;

public class UtilisationMateriel extends MappedInteger {
    String iddepot;
    String depot;
    double totalmateriels;
    double materielsutilises;
    double pourcentage_utilisation;

    public UtilisationMateriel() {
        setNomTable("utilisation_materiel");
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

    public double getTotalmateriels() {
        return totalmateriels;
    }

    public void setTotalmateriels(double totalmateriels) {
        this.totalmateriels = totalmateriels;
    }

    public double getMaterielsutilises() {
        return materielsutilises;
    }

    public void setMaterielsutilises(double materielsutilises) {
        this.materielsutilises = materielsutilises;
    }

    public double getPourcentage_utilisation() {
        return pourcentage_utilisation;
    }

    public void setPourcentage_utilisation(double pourcentage_utilisation) {
        this.pourcentage_utilisation = pourcentage_utilisation;
    }
}
