package inventorymanagement.emplacement;

import inventorymanagement.depot.Depot;

public class EmplacementPageList {
    Listeemplacement [] listeemplacements;
    Depot [] depot;
    Emplacement emplacement;

    public EmplacementPageList() {
    }

    public Listeemplacement[] getListeemplacements() {
        return listeemplacements;
    }

    public void setListeemplacements(Listeemplacement[] listeemplacements) {
        this.listeemplacements = listeemplacements;
    }

    public Depot[] getDepot() {
        return depot;
    }

    public void setDepot(Depot[] depot) {
        this.depot = depot;
    }



    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }
}
