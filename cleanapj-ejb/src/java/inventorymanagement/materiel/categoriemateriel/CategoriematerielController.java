package inventorymanagement.materiel.categoriemateriel;

import inventorymanagement.depot.DepotParams;
import inventorymanagement.materiel.typemateriel.Typemateriel;
import inventorymanagement.materiel.typemateriel.TypematerielPageList;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "CategoriematerielController", urlPatterns = {"/categoriemateriel/*"})

public class CategoriematerielController extends HController {
        CategoriematerielManager categoriematerielManager= new CategoriematerielManager();

    @HPost(url="/createcategoriemateriel")
    public void save(HttpServletRequest request, HttpServletResponse response, Categoriemateriel categoriemateriel) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.categoriematerielManager.create(categoriemateriel, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url="/listcategoriemateriel")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.categoriematerielManager.getall(connection)).close();
        connection.close();
    }


    @HPost(url = "/contentcategoriemateriel")
    public void contentlist(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        CategoriematerielPageList result = this.categoriematerielManager.contentlist(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    @HPost(url="/getcategoriemateriel")
    public void getcategoriemateriel(HttpServletRequest request, HttpServletResponse response, CategoriematerielParams categoriematerielParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.categoriematerielManager.getOne(categoriematerielParams.getIdcategoriemateriel(),connection)).close();
        connection.close();
    }
}
