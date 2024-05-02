package inventorymanagement.materiel.typemateriel;

import itusolar.prepare.MappedInteger;

public class Listetypemateriel extends MappedInteger {
    String idtypemateriel;
    String typemateriel;
    String idcategoriemateriel;
    String categoriemateriel;


    public Listetypemateriel() {
        super.setNomTable("liste_typemateriel");
    }
    @Override
    public String getTuppleID() {
        return idtypemateriel;
    }

    @Override
    public String getAttributIDName() {
        return "idtypemateriel";
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

    public String getIdcategoriemateriel() {
        return idcategoriemateriel;
    }

    public void setIdcategoriemateriel(String idcategoriemateriel) {
        this.idcategoriemateriel = idcategoriemateriel;
    }

    public String getCategoriemateriel() {
        return categoriemateriel;
    }

    public void setCategoriemateriel(String categoriemateriel) {
        this.categoriemateriel = categoriemateriel;
    }
}
