package inventorymanagement.devis.devis;

import inventorymanagement.article.Article;
import inventorymanagement.client.Client;
import inventorymanagement.devis.detaildevis.Detaildevisview;


public class DevisPageList {
    Clientdevis[] clientdevis;
    inventorymanagement.devis.detaildevis.Detaildevisview[] detaildevis;
    Article[] articles;
    Client[] clients;
    double somme;

    public DevisPageList() {
    }

    public Clientdevis[] getClientdevis() {
        return clientdevis;
    }

    public void setClientdevis(Clientdevis[] clientdevis) {
        this.clientdevis = clientdevis;
    }

    public inventorymanagement.devis.detaildevis.Detaildevisview[] getDetaildevis() {
        return detaildevis;
    }

    public void setDetaildevis(inventorymanagement.devis.detaildevis.Detaildevisview[] detaildevis) {
        this.detaildevis = detaildevis;
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

    public double sommeproforma(){
        double somme = 0;
        for(Detaildevisview detaildevis1 : detaildevis ){
            somme+=detaildevis1.getTotal();
        }
        return somme;
    }
}


