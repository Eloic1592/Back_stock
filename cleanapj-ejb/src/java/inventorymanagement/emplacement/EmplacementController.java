package inventorymanagement.emplacement;

import inventorymanagement.article.Article;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "EmplacementController",urlPatterns = {"/emplacement/*"})
public class EmplacementController extends HController {
    EmplacementManager emplacementManager=new EmplacementManager();

    @HPost(url="/contentemplacement")
    public void contentlist(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.emplacementManager.contentlist(connection)).close();
        connection.close();
    }

    @HPost(url="/createemplacement")
    public void save(HttpServletRequest request, HttpServletResponse response, Emplacement emplacement) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.emplacementManager.create(emplacement, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/getemplacement")
    public void getEmplacement(HttpServletRequest request, HttpServletResponse response, EmplacementParams emplacementParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.emplacementManager.getOnePage( emplacementParams.getIdemplacement(),connection)).close();
        connection.close();
    }

    @HPost(url="/getlistemplacementdepot")
    public void getbydepot(HttpServletRequest request, HttpServletResponse response, EmplacementParams emplacementParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.emplacementManager.getlistemplacement( emplacementParams.getIddepot(),connection)).close();
        connection.close();
    }
}
