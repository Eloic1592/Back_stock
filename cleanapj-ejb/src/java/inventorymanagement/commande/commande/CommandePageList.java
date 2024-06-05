package inventorymanagement.commande.commande;

import inventorymanagement.article.Article;
import inventorymanagement.client.Client;
import inventorymanagement.commande.detailcommande.Detailcommande;
import inventorymanagement.commande.detailcommande.Detailcommandeview;
import inventorymanagement.commande.reception.Vuereception;


public class CommandePageList {
    VueCommande[] vueCommandes;
    Detailcommandeview[] detailcommandeviews;
    Article[] articles;
    Client[] clients;
    Commande commande;
    Detailcommande detailcommande;
    Vuereception[] vuereceptions;
    Totalcommandeannee[] totalcommandeannees;
    double somme;

    public CommandePageList() {
    }

    public VueCommande[] getVueCommandes() {
        return vueCommandes;
    }

    public void setVueCommandes(VueCommande[] vueCommandes) {
        this.vueCommandes = vueCommandes;
    }

    public Detailcommandeview[] getDetailcommandeviews() {
        return detailcommandeviews;
    }

    public void setDetailcommandeviews(Detailcommandeview[] detailcommandeviews) {
        this.detailcommandeviews = detailcommandeviews;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Detailcommande getDetailcommande() {
        return detailcommande;
    }

    public void setDetailcommande(Detailcommande detailcommande) {
        this.detailcommande = detailcommande;
    }

    public Vuereception[] getVuereceptions() {
        return vuereceptions;
    }

    public void setVuereceptions(Vuereception[] vuereceptions) {
        this.vuereceptions = vuereceptions;
    }

    public Totalcommandeannee[] getTotalcommandeannees() {
        return totalcommandeannees;
    }

    public void setTotalcommandeannees(Totalcommandeannee[] totalcommandeannees) {
        this.totalcommandeannees = totalcommandeannees;
    }
}


