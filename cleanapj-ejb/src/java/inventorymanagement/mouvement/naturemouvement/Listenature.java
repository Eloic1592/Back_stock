package inventorymanagement.mouvement.naturemouvement;

import itusolar.prepare.MappedInteger;

public class Listenature extends MappedInteger {
    String idnaturemouvement;
    String naturemouvement;
    String typemouvement;
    int type;


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

    public String getTypemouvement() {
        return typemouvement;
    }

    public void setTypemouvement(String typemouvement) {
        this.typemouvement = typemouvement;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
