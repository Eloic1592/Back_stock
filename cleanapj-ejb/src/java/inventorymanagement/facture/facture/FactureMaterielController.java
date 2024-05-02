package inventorymanagement.facture.facture;

import inventorymanagement.commande.commande.Commande;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "FactureMaterielController",urlPatterns = {"/facturemateriel/*"})
public class FactureMaterielController extends HController {

    FactureMaterielManager factureMaterielManager= new FactureMaterielManager();

    @HPost(url = "/createfacture")
    public void save(HttpServletRequest request, HttpServletResponse response, Facturemateriel facturemateriel) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.factureMaterielManager.create(facturemateriel, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/listfacture")
    public void getall(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.factureMaterielManager.getall(connection)).close();
        connection.close();
    }

    @HPost(url = "/contentfacture")
    public void contentlist(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.factureMaterielManager.contentlist(connection)).close();
        connection.close();
    }
}
