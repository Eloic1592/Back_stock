package inventorymanagement.bon.livraison;

import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "BonlivraisonController", urlPatterns = {"/bonlivraison/*"})
public class BonlivraisonController extends HController {

    BonlivraisonManager livraisonManager=new BonlivraisonManager();


    @HPost(url = "/createlivraison")
    public void save(HttpServletRequest request, HttpServletResponse response, Bonlivraison[] bonlivraisons) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.livraisonManager.createlivraison(bonlivraisons, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/listlivraison")
    public void getall(HttpServletRequest request, HttpServletResponse response, Clientlivraison o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.livraisonManager.getall(connection)).close();
        connection.close();
    }
}
