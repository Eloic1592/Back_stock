package inventorymanagement.commande.commande;

public class CommandeParams {
    String idcommande;
    String iddetailcommande;



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
}
