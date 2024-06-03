package inventorymanagement.article;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "ArticleController",urlPatterns = {"/article/*"})
public class ArticleController extends HController {
    ArticleManager articleManager=new ArticleManager();

    @HPost(url="/createarticle")
    public void save(HttpServletRequest request, HttpServletResponse response, Article article) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.articleManager.create(article, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/listarticle")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.articleManager.getall(connection)).close();
        connection.close();
    }

    @HPost(url="/contentarticle")
    public void contentlist(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.articleManager.contentlist(connection)).close();
        connection.close();
    }

    @HPost(url="/stockarticle")
    public void stocklist(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.articleManager.stocklist(connection)).close();
        connection.close();
    }

    @HPost(url="/getarticle")
    public void getarticle(HttpServletRequest request, HttpServletResponse response,ArticleParams articleParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.articleManager.getOnePage(articleParams.getIdarticle(),connection)).close();
        connection.close();
    }


    @HPost(url="/rupturearticle")
    public void rupturearticle(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.articleManager.rupturearticle(connection)).close();
        connection.close();
    }

    @HPost(url="/detailstockarticle")
    public void detailstockarticle(HttpServletRequest request, HttpServletResponse response,ArticleParams articleParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.articleManager.getdetaistockarticle(articleParams.getIdarticle(),connection)).close();
        connection.close();
    }
}
