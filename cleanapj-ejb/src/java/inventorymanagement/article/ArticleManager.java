package inventorymanagement.article;

import bean.CGenUtil;
import bean.ResultatEtSomme;
import inventorymanagement.materiel.typemateriel.Typemateriel;
import inventorymanagement.materiel.typemateriel.TypematerielManager;
import inventorymanagement.stockage.distribution.Distribution;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

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

    public double sommebonetat(String idarticle,Connection connection) throws Exception {
        double somme=0;
        connection=this.getConnection(connection);
        Distribution[] data=(Distribution[])CGenUtil.rechercher(new Distribution(), new String[0], new String[0], connection, "and idarticle='"+idarticle+"' and etatdistribue=1");
        for(Distribution a:data){
            somme+=a.getQuantite();
        }
        return somme;
    }
    public double sommeabime(String idarticle,Connection connection) throws Exception {
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
        articlePageList.setSommebonetat(sommebonetat(idarticle,connection));
        articlePageList.setSommeabime(sommeabime(idarticle,connection));
        return articlePageList;
    }
}
