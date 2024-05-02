package inventorymanagement.mouvement.mouvementstock;

import inventorymanagement.mouvement.mouvementfictif.Mouvementfictif;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysique;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "MouvementstockController",urlPatterns = {"/mouvementstock/*"})
public class MouvementstockController extends HController {
    MouvementstockManager mouvementstockManager = new MouvementstockManager();



    @HPost(url="/createstockphysique")
    public void savemvp(HttpServletRequest request, HttpServletResponse response, Mouvementphysique mouvementphysique) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.createmouvementphysique(mouvementphysique, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/createstockfictif")
    public void savemvf(HttpServletRequest request, HttpServletResponse response, Mouvementstock stock) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.createmouvementfictif(stock, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }


    @HPost(url="/createdetailphysique")
    public void savedetailmp(HttpServletRequest request, HttpServletResponse response, Mouvementphysique [] mouvementphysique) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.createmvp(mouvementphysique, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/createsingledetailphysique")
    public void savesinglemp(HttpServletRequest request, HttpServletResponse response, Mouvementphysique mouvementphysique) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.createsinglemvp(mouvementphysique, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/createdetailfictif")
    public void savedetailmp(HttpServletRequest request, HttpServletResponse response, Mouvementfictif[] mouvementfictifs) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.createmvf(mouvementfictifs, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/createsingledetailfictif")
    public void savesinglemvf(HttpServletRequest request, HttpServletResponse response, Mouvementfictif mouvementfictifs) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.createsinglemvf(mouvementfictifs, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/detailstockphysique")
    public void detailstockphysique(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.contentdetaillistephysique(mouvementstockParams.getIdmouvementstock(),connection)).close();
        connection.close();
    }

    @HPost(url="/detailstockfictif")
    public void detailstockfictif(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.contentdetaillistefictif(mouvementstockParams.getIdmouvementstock(),connection)).close();
        connection.close();
    }

    @HPost(url="/contentstockphysique")
    public void contentlistphysique(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.contentlistphysique(connection)).close();
        connection.close();
    }

    @HPost(url="/contentstockfictif")
    public void contentlistfictif(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.contentlistfictif(connection)).close();
        connection.close();
    }


    @HPost(url="/contentformphysique")
    public void contentformphysique(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.contentformphysique(connection)).close();
        connection.close();
    }


    @HPost(url="/contentformfictif")
    public void contentformfictif(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.contentformfictif(connection)).close();
        connection.close();
    }

    @HPost(url="/getmouvementphysique")
    public void getmouvementphysique(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.getOnePage(mouvementstockParams.getIddetailmouvementphysique(),connection)).close();
        connection.close();
    }


    @HPost(url="/getmouvementfictif")
    public void getmouvementfictif(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.getOnePagef(mouvementstockParams.getIddetailmouvementfictif(),connection)).close();
        connection.close();
    }

    @HPost(url="/getmouvementstock")
    public void getmouvementstock(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.OnePageStock(mouvementstockParams.getIdmouvementstock(),connection)).close();
        connection.close();
    }

    @HPost(url="/importdatamphysique")
    public void importdatamphysique(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.mouvementstockManager.importexcelfile(connection,mouvementstockParams.getFilename());
        this.writeSuccess(response, null,"Importer ny donnees").close();
        connection.close();
    }

    @HPost(url="/discharge")
    public void discharge(HttpServletRequest request, HttpServletResponse response, MouvementstockParams mouvementstockParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.mouvementstockManager.discharge(mouvementstockParams.getIdmouvementstock(),connection)).close();
        connection.close();
    }



}
