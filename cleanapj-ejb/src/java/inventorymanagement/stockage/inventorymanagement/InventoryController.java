package inventorymanagement.stockage.inventorymanagement;

import inventorymanagement.stockage.distribution.Distribution;
import inventorymanagement.stockage.inventaire.Calendrierinventaire;
import inventorymanagement.stockage.inventaire.Inventaire;
import inventorymanagement.stockage.stockage.Stockage;
import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "InventoryController",urlPatterns = {"/inventory/*"})
public class InventoryController extends HController {
        InventoryManager inventoryManager=new InventoryManager();

    @HPost(url="/createdistribution")
    public void savedist(HttpServletRequest request, HttpServletResponse response, Distribution distribution) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.inventoryManager.createdistribution(distribution, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/createstockage")
    public void savestock(HttpServletRequest request, HttpServletResponse response, Stockage stockage) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.inventoryManager.createstockage(stockage, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }
    @HPost(url="/createinventaire")
    public void saveinv(HttpServletRequest request, HttpServletResponse response, Inventaire inventaire) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.inventoryManager.createinventaire(inventaire, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/listdistribution")
    public void distribution(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.distribution(connection)).close();
        connection.close();
    }

    @HPost(url="/liststockage")
    public void stockage(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.stockage(connection)).close();
        connection.close();
    }

    @HPost(url="/listinventaire")
    public void inventaire(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.inventaire(connection)).close();
        connection.close();
    }

    @HPost(url="/getdistribution")
    public void getdistribution(HttpServletRequest request, HttpServletResponse response,InventoryParams inventoryParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.editdistribution(inventoryParams.getIddistribution(),connection)).close();
        connection.close();
    }

    @HPost(url="/getstockage")
    public void getstockage(HttpServletRequest request, HttpServletResponse response,InventoryParams inventoryParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.editstockage(inventoryParams.getIdstockage(),connection)).close();
        connection.close();
    }

    @HPost(url="/getinventaire")
    public void getinventaire(HttpServletRequest request, HttpServletResponse response,InventoryParams inventoryParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.editinventaire(inventoryParams.getIdinventaire(),connection)).close();
        connection.close();
    }

    @HPost(url="/distributionform")
    public void distributionform(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.distributionform(connection)).close();
        connection.close();
    }

    @HPost(url="/stockageform")
    public void stockageform(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.stockageform(connection)).close();
        connection.close();
    }

    @HPost(url="/inventaireform")
    public void inventaireform(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.inventaireform(connection)).close();
        connection.close();
    }

    @HPost(url="/createcalendrier")
    public void createcalendrier(HttpServletRequest request, HttpServletResponse response, Calendrierinventaire calendrierinventaire) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.inventoryManager.createcalendrier(calendrierinventaire, connection);
        this.writeSuccess(response, null,"lasa ny donnees").close();
        connection.close();
    }

    @HPost(url="/getcalendrier")
    public void getcalendrier(HttpServletRequest request, HttpServletResponse response,InventoryParams inventoryParam) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.getcalendrier(inventoryParam.getIdcalendrierinventaire(),connection)).close();
        connection.close();
    }

    @HPost(url="/calendrierinventaire")
    public void calendrierinventaire(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.calendrier(connection)).close();
        connection.close();
    }

    @HPost(url="/calendriernotif")
    public void calendriernotif(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.calendriernotif(connection)).close();
        connection.close();
    }
}
