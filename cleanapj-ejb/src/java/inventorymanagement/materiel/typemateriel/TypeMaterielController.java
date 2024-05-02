package inventorymanagement.materiel.typemateriel;

import inventorymanagement.depot.Depot;
import inventorymanagement.depot.DepotPageList;
import inventorymanagement.depot.DepotParams;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "TypeMaterielController", urlPatterns = {"/typemateriel/*"})
public class TypeMaterielController extends HController {
    TypematerielManager typematerielManager=new TypematerielManager();

    @HPost(url="/createtypemateriel")
    public void save(HttpServletRequest request, HttpServletResponse response, Typemateriel typemateriel) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.typematerielManager.create(typemateriel, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url="/listtypemateriel")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.typematerielManager.getall(connection)).close();
        connection.close();
    }
    @HPost(url = "/contenttypemateriel")
    public void contentlist(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        TypematerielPageList result = this.typematerielManager.contentlist(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    @HPost(url="/gettypemateriel")
    public void gettypemateriel(HttpServletRequest request, HttpServletResponse response, TypematerielParams typematerielParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.typematerielManager.getOnePage(typematerielParams.getIdtypemateriel(),connection)).close();
        connection.close();
    }

    @HPost(url="/getstattypemateriel")
    public void getstattypemateriel(HttpServletRequest request, HttpServletResponse response, TypematerielParams typematerielParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.typematerielManager.getstatlist(typematerielParams.getIdnaturemouvement(), typematerielParams.getMois(),typematerielParams.getAnnee(), connection)).close();
        connection.close();
    }
}
