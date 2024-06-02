package inventorymanagement.commande.commande;

public class CommandeParams {
    String idcommande;
    String iddetailcommande;
    String idreception;



    public CommandeParams() {
    }

    public String getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(String idcommande) {
        this.idcommande = idcommande;
    }

    public String getIddetailcommande() {
        return iddetailcommande;
    }

    public void setIddetailcommande(String iddetailcommande) {
        this.iddetailcommande = iddetailcommande;
    }

    public String getIdreception() {
        return idreception;
    }

    public void setIdreception(String idreception) {
        this.idreception = idreception;
    }
}
