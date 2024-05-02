package inventorymanagement.materiel.materiel;

import inventorymanagement.article.ArticleParams;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "MaterielController",urlPatterns = {"/materiel/*"})
public class MaterielController extends HController {
    MaterielManager materielManager=new MaterielManager();

    @HPost(url="/createmateriel")
    public void save(HttpServletRequest request, HttpServletResponse response, Materiel materiel) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.materielManager.create(materiel, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url="/listmateriel")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.materielManager.getall(connection)).close();
        connection.close();
    }


    @HPost(url = "/contentmateriel")
    public void contentlist(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        MaterielPageList result = this.materielManager.contentlist(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    @HPost(url = "/contentform")
    public void contentform(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        MaterielPageList result = this.materielManager.contentform(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    @HPost(url="/utilisationmateriel")
    public void utilisationmateriel(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.materielManager.stockmateriellist(connection)).close();
        connection.close();
    }

    @HPost(url="/getmateriel")
    public void getmateriel(HttpServletRequest request, HttpServletResponse response, MaterielParams materielParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.materielManager.getOnePage(materielParams.getIdmateriel(),connection)).close();
        connection.close();
    }

    @HPost(url="/importmateriel")
    public void importmateriel(HttpServletRequest request, HttpServletResponse response,Materiel [] materiels) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.materielManager.importexcelfile(connection,materiels);
        this.writeSuccess(response, null,"Lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/stockmateriel")
    public void stocklist(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.materielManager.stocklist(connection)).close();
        connection.close();
    }
}
