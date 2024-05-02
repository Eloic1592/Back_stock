package inventorymanagement.bon.commande;

import inventorymanagement.bon.livraison.Clientlivraison;
import inventorymanagement.devis.proforma.DetailProforma;

public class CommandepageList {
    Clientlivraison [] clientlivraisons;
    Clientboncommande[] clientboncommandes;
    DetailProforma[] detailProformas;
    double somme;


    public CommandepageList() {
    }

    public Clientlivraison[] getClientlivraisons() {
        return clientlivraisons;
    }

    public void setClientlivraisons(Clientlivraison[] clientlivraisons) {
        this.clientlivraisons = clientlivraisons;
    }

    public Clientboncommande[] getClientboncommandes() {
        return clientboncommandes;
    }

    public DetailProforma[] getDetailProformas() {
        return detailProformas;
    }

    public void setDetailProformas(DetailProforma[] detailProformas) {
        this.detailProformas = detailProformas;
    }

    public void setClientboncommandes(Clientboncommande[] clientboncommandes) {
        this.clientboncommandes = clientboncommandes;

    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public double sommeproforma(){
        double somme = 0;
        for(DetailProforma detailProforma : detailProformas ){
            somme+=detailProforma.getTotal();
        }
        return somme;
    }
}
