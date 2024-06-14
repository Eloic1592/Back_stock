package inventorymanagement.mouvement.naturemouvement;

import itusolar.prepare.MappedInteger;

public class Listenature extends MappedInteger {
    String idnaturemouvement;
    String naturemouvement;


    public Listenature() {
        setNomTable("liste_nature");
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }

}
