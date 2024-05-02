package inventorymanagement.devis.devis;

import inventorymanagement.devis.proforma.Proforma;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import inventorymanagement.devis.detaildevis.Detaildevis;
import java.sql.Connection;

@WebServlet(name = "DevisController",urlPatterns = "/devis/*")
public class DevisController extends HController {

    DevisManager devisManager=new DevisManager();

    @HPost(url = "/createdevis")
    public void savedevis(HttpServletRequest request, HttpServletResponse response, Devis devis) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.devisManager.createdevis(devis, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/createdetaildevis")
    public void savedetails(HttpServletRequest request, HttpServletResponse response, Detaildevis [] detaildevis) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.devisManager.createdetails(detaildevis, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url = "/singledetails")
    public void singledetail(HttpServletRequest request, HttpServletResponse response,Detaildevis detaildevis) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.devisManager.createsingledetail(detaildevis, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }


    @HPost(url = "/contentdevis")
    public void contentlist(HttpServletRequest request, HttpServletResponse response,String iddevis) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.devisManager.contentlist(connection,iddevis)).close();
        connection.close();
    }

    @HPost(url = "/detaildevis")
    public void details(HttpServletRequest request, HttpServletResponse response,DevisParams devisParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.devisManager.contentdetaillist(connection,devisParams.getIddevis())).close();
        connection.close();
    }


    @HPost(url = "/contentform")
    public void contentform(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.devisManager.contentform(connection)).close();
        connection.close();
    }
}
