package inventorymanagement.commande.commande;

import inventorymanagement.commande.detailcommande.Detailcommande;
import inventorymanagement.commande.reception.Reception;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "CommandeController",urlPatterns = "/commande/*")
public class CommandeController extends HController {

    CommandeManager commandeManager =new CommandeManager();

    @HPost(url = "/createcommande")
    public void savecommande(HttpServletRequest request, HttpServletResponse response, Commande commande) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.commandeManager.createcommande(commande, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/createdetailcommande")
    public void savedetails(HttpServletRequest request, HttpServletResponse response, Detailcommande[] detailcommandes) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.commandeManager.createdetails(detailcommandes, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url = "/singledetails")
    public void singledetail(HttpServletRequest request, HttpServletResponse response, Detailcommande detailcommande) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.commandeManager.createsingledetail(detailcommande, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }


    @HPost(url = "/commandecontentpage")
    public void contentlist(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.commandcontentpage(connection)).close();
        connection.close();
    }

    @HPost(url = "/detailcontentpage")
    public void details(HttpServletRequest request, HttpServletResponse response, CommandeParams commandeParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.detailcontentpage(connection, commandeParams.getIdcommande())).close();
        connection.close();
    }


    @HPost(url = "/contentform")
    public void contentform(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.contentform(connection)).close();
        connection.close();
    }
    @HPost(url = "/getcommande")
    public void getcommande(HttpServletRequest request, HttpServletResponse response, CommandeParams commandeParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.editform(commandeParams.getIdcommande(),connection)).close();
        connection.close();
    }

    @HPost(url = "/getdetailcommande")
    public void getdetailcommande(HttpServletRequest request, HttpServletResponse response, CommandeParams commandeParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.editformdetails(commandeParams.getIddetailcommande(),connection)).close();
        connection.close();
    }

    @HPost(url = "/totalcommandeannee")
    public void totalcommandeannee(HttpServletRequest request, HttpServletResponse response, CommandeParams commandeParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.totalcommandeannee(commandeParams.getAnnee(),commandeParams.getMois(),connection)).close();
        connection.close();
    }


    @HPost(url = "/createreception")
    public void saveereceip(HttpServletRequest request, HttpServletResponse response, Reception reception) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.commandeManager.createreception(reception, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/listreception")
    public void getreception(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.reception(connection)).close();
        connection.close();
    }

    @HPost(url = "/getreception")
    public void getreception(HttpServletRequest request, HttpServletResponse response,CommandeParams commandeParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.getreception(commandeParams.getIdreception(),connection)).close();
        connection.close();
    }

    @HPost(url = "/pdfcommande")
    public void pdfcommande(HttpServletRequest request, HttpServletResponse response,CommandeParams commandeParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.commandeManager.pdfcommande(commandeParams.getIdcommande(),connection)).close();
        connection.close();
    }
}
