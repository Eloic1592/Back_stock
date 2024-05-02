package inventorymanagement.article;

import itusolar.prepare.MappedInteger;

public class Tempsrupturearticle extends MappedInteger {
    String idarticle;
    String marque;
    String modele;
    String description;
    String idtypemateriel;
    String typemateriel;
    double moyenne_jour;

    public Tempsrupturearticle() {
        setNomTable("temps_rupture_stock_article");
    }

    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public double getMoyenne_jour() {
        return moyenne_jour;
    }

    public void setMoyenne_jour(double moyenne_jour) {
        this.moyenne_jour = moyenne_jour;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(String typemateriel) {
        this.typemateriel = typemateriel;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }
}
