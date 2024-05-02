package inventorymanagement.article;

import inventorymanagement.materiel.typemateriel.Typemateriel;

public class ArticlePageList {
    Listearticle [] articles;
    Typemateriel[] typemateriels;
    Stockarticle[] stockarticles;
    Listearticle  article;


    public ArticlePageList() {
    }

    public Listearticle[] getArticles() {
        return articles;
    }

    public void setArticles(Listearticle[] articles) {
        this.articles = articles;
    }

    public Typemateriel[] getTypemateriels() {
        return typemateriels;
    }

    public void setTypemateriels(Typemateriel[] typemateriels) {
        this.typemateriels = typemateriels;
    }

    public Stockarticle[] getStockarticles() {
        return stockarticles;
    }

    public void setStockarticles(Stockarticle[] stockarticles) {
        this.stockarticles = stockarticles;
    }

    public Listearticle getArticle() {
        return article;
    }

    public void setArticle(Listearticle article) {
        this.article = article;
    }
}
