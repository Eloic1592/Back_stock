package inventorymanagement.mouvement.mouvementstock;

import java.sql.Timestamp;

public class Mouvementstockview extends Mouvementstock{
    String idmouvement;
    Timestamp datedepot;
    int type;
    String mouvement;
    String idnaturemouvement;
    String naturemouvement;
    String idetudiant;
    String nom;
    String prenom;
    String mail;
    String contact;
    String adresse;
    int statut;

    public Mouvementstockview() {
        setNomTable("mouvement_stock");
    }

    public String getIdmouvement() {
        return idmouvement;
    }

    public void setIdmouvement(String idmouvement) {
        this.idmouvement = idmouvement;
    }

    @Override
    public Timestamp getDatedepot() {
        return datedepot;
    }

    @Override
    public void setDatedepot(Timestamp datedepot) {
        this.datedepot = datedepot;
    }



    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    @Override
    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    @Override
    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String getIdetudiant() {
        return idetudiant;
    }

    @Override
    public void setIdetudiant(String idetudiant) {
        this.idetudiant = idetudiant;
    }

    @Override
    public int getStatut() {
        return statut;
    }

    @Override
    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
