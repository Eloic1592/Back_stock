package inventorymanagement.depot;

import inventorymanagement.mouvement.mouvementstock.MouvementstockParams;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "DepotController", urlPatterns = {"/depot/*"})
public class DepotController extends HController {
    DepotManager depotManager= new DepotManager();

    @HPost(url = "/createdepot")
    public void save(HttpServletRequest request, HttpServletResponse response, Depot depot) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.depotManager.create(depot, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/contentdepot")
    public void contentlist(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        DepotPageList result = this.depotManager.contentlist(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    @HPost(url = "/listdepot")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.depotManager.getall(connection)).close();
        connection.close();
    }

    @HPost(url = "/stockdepot")
    public void stockdepot(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.depotManager.getstockadepot(connection)).close();
        connection.close();
    }


    @HPost(url = "/stocktypematerieldepot")
    public void stocktypematerieldepot(HttpServletRequest request, HttpServletResponse response,DepotParams depotParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.depotManager.getstocktpdepot(depotParams.getIddepot(),connection)).close();
        connection.close();
    }


    @HPost(url="/getdepot")
    public void getdepot(HttpServletRequest request, HttpServletResponse response, DepotParams depotParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.depotManager.getOne(depotParams.getIddepot(),connection)).close();
        connection.close();
    }


}
