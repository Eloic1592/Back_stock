package inventorymanagement.article;

import bean.CGenUtil;
import bean.ResultatEtSomme;
import inventorymanagement.materiel.typemateriel.Typemateriel;
import inventorymanagement.materiel.typemateriel.TypematerielManager;
import inventorymanagement.stockage.distribution.Distribution;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.util.Random;

public class ArticleManager extends HServiceManager implements ArticleManagerSignature {
    ArticleManagerSignature articleManagerSignature;
    TypematerielManager typematerielManager=new TypematerielManager();

    @Override
    public void create(Article article, Connection connection) throws Exception {
        connection = this.getConnection(connection);

        if (article.getIdarticle()!=null) {
            article.updateToTable(connection);
            return;
        }
        article.construirePK(connection);
        CGenUtil.save(article, connection);
    }

    @Override
    public Article[] getall(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Article(), new String[0], new String[0], connection, "");
        return cast(data);
    }

    @Override
    public Listearticle getOne(String idarticle,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Listearticle[] data=(Listearticle[])CGenUtil.rechercher(new Listearticle(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"'");
        return data[0];
    }

    public Article getsinglearticle(String idarticle,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Article[] data=(Article[])CGenUtil.rechercher(new Article(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"'");
        return data[0];
    }


    public Article[] cast(Object[] datas) {
        Article[] articles = new Article[datas.length];
        for (int i = 0; i < datas.length; i++) {
            articles[i] = (Article) datas[i];
        }
        return articles;
    }

    public Listearticle[] getlistarticle(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Listearticle(), new String[0], new String[0], connection, "");
        return castarticle(data);
    }

    public Listearticle[] castarticle(Object[] datas) {
        Listearticle[] articles = new Listearticle[datas.length];
        for (int i = 0; i < datas.length; i++) {
            articles[i] = (Listearticle) datas[i];
        }
        return articles;
    }

    public Stockarticle[] getstockarticle(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockarticle[] data=(Stockarticle[])CGenUtil.rechercher(new Stockarticle(), new String[0], new String[0], connection, "");
        return data;
    }
    public double sommebonetat(Connection connection) throws Exception {
        double somme=0;
        connection=this.getConnection(connection);
        Distribution[] data=(Distribution[])CGenUtil.rechercher(new Distribution(), new String[0], new String[0], connection, " and etatdistribue=1");
        for(Distribution a:data){
            somme+=a.getQuantite();
        }
        return somme;
    }
    public double sommeabime(Connection connection) throws Exception {
        double somme=0;
        connection=this.getConnection(connection);
        Distribution[] data=(Distribution[])CGenUtil.rechercher(new Distribution(), new String[0], new String[0], connection, " and etatdistribue=0");
        for(Distribution a:data){
            somme+=a.getQuantite();
        }
        return somme;
    }

    public double sommedetailbonetat(String idarticle,Connection connection) throws Exception {
        double somme=0;
        connection=this.getConnection(connection);
        Distribution[] data=(Distribution[])CGenUtil.rechercher(new Distribution(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"' and etatdistribue=1");
        for(Distribution a:data){
            somme+=a.getQuantite();
        }
        return somme;
    }
    public double sommedetailabime(String idarticle,Connection connection) throws Exception {
        double somme=0;
        connection=this.getConnection(connection);
        Distribution[] data=(Distribution[])CGenUtil.rechercher(new Distribution(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"' and etatdistribue=0");
        for(Distribution a:data){
            somme+=a.getQuantite();
        }
        return somme;
    }

    public Stockarticle[] detailstockarticle(String idarticle, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockarticle[] data=(Stockarticle[])CGenUtil.rechercher(new Stockarticle(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"'");
        return data;
    }

    public Stockarticleinventaire[] stockarticleinventaires(String idarticle, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockarticleinventaire[] data=(Stockarticleinventaire[])CGenUtil.rechercher(new Stockarticleinventaire(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"'");
        return data;
    }

    @Override
    public ArticlePageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        ArticlePageList articlePageList=new ArticlePageList();
        articlePageList.setArticles(getlistarticle(connection));
        articlePageList.setTypemateriels(typematerielManager.getall(connection));
        return articlePageList;
    }

    public ArticlePageList stocklist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        ArticlePageList articlePageList=new ArticlePageList();
        articlePageList.setStockarticles(getstockarticle(connection));
        articlePageList.setSommebonetat(sommebonetat(connection));
        articlePageList.setSommeabime(sommeabime(connection));
        return articlePageList;
    }

    public ArticlePageList analysestock(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        ArticlePageList articlePageList=new ArticlePageList();
        articlePageList.setStockarticles(getstockarticle(connection));
        return articlePageList;
    }

    public ArticlePageList getOnePage(String idarticle,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        ArticlePageList articlePageList=new ArticlePageList();
        articlePageList.setArticle(getOne(idarticle,connection));
        articlePageList.setTypemateriels(typematerielManager.getall(connection));
        return articlePageList;
    }

    public Stockrupturearticle[] rupturearticle(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockrupturearticle[] data=(Stockrupturearticle[])CGenUtil.rechercher(new Stockrupturearticle(), new String[0], new String[0], connection, "");
        return data;
    }

    public ArticlePageList getdetaistockarticle(String idarticle,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        ArticlePageList articlePageList=new ArticlePageList();
        articlePageList.setStockarticles(detailstockarticle(idarticle,connection));
        articlePageList.setStockarticleinventaires(stockarticleinventaires(idarticle,connection));
        articlePageList.setSommebonetat(sommedetailbonetat(idarticle,connection));
        articlePageList.setSommeabime(sommedetailabime(idarticle,connection));
        return articlePageList;
    }

    public Stockarticle[] stockurgents(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockarticle[] data=(Stockarticle[])CGenUtil.rechercher(new Stockarticle(), new String[0], new String[0], connection, "and degre=5 or degre=2 order by degre desc");
        return data;
    }

    public Reapprovisionnement[] getreapprovisionnement(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockarticle[] stockurgents = stockurgents(connection);
        Reapprovisionnement[] reapprovisionnements = new Reapprovisionnement[stockurgents.length];

        for (int i = 0; i < stockurgents.length; i++) {
            Reapprovisionnement reapprovisionnement = new Reapprovisionnement();
            reapprovisionnement.setIdarticle(stockurgents[i].getIdarticle());
            reapprovisionnement.setMarque(stockurgents[i].getMarque());
            reapprovisionnement.setModele(stockurgents[i].getModele());
            reapprovisionnement.setCodearticle(stockurgents[i].getCodearticle());
            reapprovisionnement.setQuantitestock(stockurgents[i].getQuantitestock());
            reapprovisionnement.setStocksecurite(stockurgents[i].getStocksecurite());
            reapprovisionnement.setVal(stockurgents[i].getVal());
            reapprovisionnement.setTypemateriel(stockurgents[i].getTypemateriel());
            reapprovisionnement.setIdtypemateriel(stockurgents[i].getIdtypemateriel());
            Random rand = new Random();
            double stockrecharge = reapprovisionnement.getStocksecurite() + (50 - reapprovisionnement.getStocksecurite()) * rand.nextDouble();
            reapprovisionnement.setStockrecharge(stockrecharge);
            reapprovisionnements[i] = reapprovisionnement;
        }

        return  reapprovisionnements;
    }
}
