package inventorymanagement.devis.proforma;

import inventorymanagement.devis.devis.DevisManager;
import inventorymanagement.devis.devis.DevisParams;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "ProformaController",urlPatterns = "/proforma/*")
public class ProformaController extends HController {

    ProformaManager proformaManager=new ProformaManager();
    DevisManager devisManager=new DevisManager();

    @HPost(url = "/createproforma")
    public void saveproforma(HttpServletRequest request, HttpServletResponse response, Proforma[] proforma) throws Exception {
        this.activeJson(response);
        System.out.println(proforma.length);
        Connection connection = this.getConnection();
        this.proformaManager.createproforma(proforma, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/createsingleproforma")
    public void savesingleproforma(HttpServletRequest request, HttpServletResponse response, Proforma proforma) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.proformaManager.createsingleproforma(proforma, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url = "/detailproforma")
    public void details(HttpServletRequest request, HttpServletResponse response, DevisParams devisParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        devisManager.contentdetaillist(connection,devisParams.getIddevis()).setArticles(null);
        this.writeSuccess(response, null,this.devisManager.contentdetaillist(connection,devisParams.getIddevis())).close();
        connection.close();
    }


    @HPost(url = "/proformaclient")
    public void getallproforma(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.proformaManager.getallproforma(connection)).close();
        connection.close();
    }



}
