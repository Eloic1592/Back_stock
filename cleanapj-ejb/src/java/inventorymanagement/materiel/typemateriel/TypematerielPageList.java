package inventorymanagement.materiel.typemateriel;

import inventorymanagement.materiel.categoriemateriel.Categoriemateriel;

public class TypematerielPageList {
    Listetypemateriel[] typemateriels;
    Categoriemateriel[] categoriemateriels;
    Typemateriel typemateriel;
    Stat_typemateriel [] stat_typemateriels;
    Typemateriel[] listetypemateriels;

    public TypematerielPageList() {
    }

    public Listetypemateriel[] getTypemateriels() {
        return typemateriels;
    }

    public void setTypemateriels(Listetypemateriel[] typemateriels) {
        this.typemateriels = typemateriels;
    }

    public Categoriemateriel[] getCategoriemateriels() {
        return categoriemateriels;
    }

    public void setCategoriemateriels(Categoriemateriel[] categoriemateriels) {
        this.categoriemateriels = categoriemateriels;
    }

    public Typemateriel getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(Typemateriel typemateriel) {
        this.typemateriel = typemateriel;
    }

    public Stat_typemateriel[] getStat_typemateriels() {
        return stat_typemateriels;
    }

    public void setStat_typemateriels(Stat_typemateriel[] stat_typemateriels) {
        this.stat_typemateriels = stat_typemateriels;
    }

    public Typemateriel[] getListetypemateriels() {
        return listetypemateriels;
    }

    public void setListetypemateriels(Typemateriel[] listetypemateriels) {
        this.listetypemateriels = listetypemateriels;
    }
}
