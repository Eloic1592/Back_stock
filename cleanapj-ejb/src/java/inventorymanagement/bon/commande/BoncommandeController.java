package inventorymanagement.bon.commande;

import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "BoncommandeController", urlPatterns = {"/boncommande/*"})
public class BoncommandeController extends HController {


    BoncommandeManager boncommandeManager=new BoncommandeManager();

    @HPost(url = "/createcommande")
    public void save(HttpServletRequest request, HttpServletResponse response, Boncommande [] boncommandes) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.boncommandeManager.createcommande(boncommandes, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/listcommande")
    public void getall(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.boncommandeManager.getall(connection)).close();
        connection.close();
    }

    @HPost(url = "/detailcommande")
    public void getdetail(HttpServletRequest request, HttpServletResponse response, CommandeParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.boncommandeManager.contentlist(params.getIdproforma(),connection)).close();
        connection.close();
    }




}
