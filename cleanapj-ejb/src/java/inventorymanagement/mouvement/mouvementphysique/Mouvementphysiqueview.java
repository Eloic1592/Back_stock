package inventorymanagement.mouvement.mouvementphysique;

import itusolar.prepare.MappedInteger;

import java.sql.Timestamp;

public class Mouvementphysiqueview extends Mouvementphysique {
    String iddetailmouvementphysique;
    Timestamp datededepot;
    int typemouvement;
    String mouvement;
    String naturemouvement;
    String idarticle;
    String marque;
    String modele;
    double quantite;
    double pu;
    double total;
    double restestock;
    String iddepot;
    String depot;
    String commentaire;
    String description;
    int statut;
    double somme;

    public Mouvementphysiqueview() {
        setNomTable("mouvement_physique");
    }
    public Timestamp getDatededepot() {
        return datededepot;
    }

    public void setDatededepot(Timestamp datededepot) {
        this.datededepot = datededepot;
    }


    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
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

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public String getIddetailmouvementphysique() {
        return iddetailmouvementphysique;
    }

    public void setIddetailmouvementphysique(String iddetailmouvementphysique) {
        this.iddetailmouvementphysique = iddetailmouvementphysique;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public double getRestestock() {
        return restestock;
    }

    public void setRestestock(double restestock) {
        this.restestock = restestock;
    }

    @Override
    public int getTypemouvement() {
        return typemouvement;
    }

    @Override
    public void setTypemouvement(int typemouvement) {
        this.typemouvement = typemouvement;
    }

    @Override
    public String getIdarticle() {
        return idarticle;
    }

    @Override
    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    @Override
    public String getIddepot() {
        return iddepot;
    }

    @Override
    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }
}
