package inventorymanagement.mouvement.naturemouvement;

import inventorymanagement.materiel.categoriemateriel.CategoriematerielParams;
import inventorymanagement.materiel.typemateriel.Typemateriel;
import inventorymanagement.materiel.typemateriel.TypematerielPageList;
import inventorymanagement.mouvement.mouvementstock.MouvementstockManager;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "NaturemouvementController",urlPatterns ={"/naturemouvement/*"})
public class NaturemouvementController extends HController {
    NaturemouvementManager naturemouvementManager=new NaturemouvementManager();
    MouvementstockManager mouvementstockManager=new MouvementstockManager();

    @HPost(url="/createnatmouvement")
    public void save(HttpServletRequest request, HttpServletResponse response, Naturemouvement naturemouvement) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.naturemouvementManager.create(naturemouvement, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url="/listnatmouvement")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.naturemouvementManager.getall(connection)).close();
        connection.close();
    }


    @HPost(url = "/contentnatmouvement")
    public void contentlist(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        NaturemouvementPageList result = this.naturemouvementManager.contentlist(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    @HPost(url = "/statnaturemouvement")
    public void statnaturemouvement(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        this.writeSuccess(resp, null, mouvementstockManager.statnaturelist(connection)).close();
        connection.close();
    }

    @HPost(url = "/gettotalnature")
    public void gettotalnature(HttpServletRequest req, HttpServletResponse resp,NaturemouvementParams naturemouvementParams) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        this.writeSuccess(resp, null, mouvementstockManager.statnature(connection,naturemouvementParams.getIdnaturemouvement())).close();
        connection.close();
    }

    @HPost(url="/getnaturemouvement")
    public void getnaturemouvement(HttpServletRequest request, HttpServletResponse response, NaturemouvementParams naturemouvementParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.naturemouvementManager.getOne(naturemouvementParams.getIdnaturemouvement(),connection)).close();
        connection.close();
    }

    @HPost(url = "/cyclenaturemouvement")
    public void cyclenaturemouvement(HttpServletRequest req, HttpServletResponse resp,Object o) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        this.writeSuccess(resp, null, naturemouvementManager.cyclenaturelist(connection)).close();
        connection.close();
    }
}
