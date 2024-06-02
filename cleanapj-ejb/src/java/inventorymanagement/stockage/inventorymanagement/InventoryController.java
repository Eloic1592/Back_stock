package inventorymanagement.stockage.inventorymanagement;

import inventorymanagement.mouvement.mouvementphysique.Mouvementphysique;
import inventorymanagement.mouvement.mouvementstock.MouvementstockParams;
import inventorymanagement.stockage.distribution.Distribution;
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

    @HPost(url="/distribution")
    public void distribution(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.distribution(connection)).close();
        connection.close();
    }

    @HPost(url="/stockage")
    public void stockage(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.stockage(connection)).close();
        connection.close();
    }

    @HPost(url="/inventaire")
    public void inventaire(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.inventoryManager.inventaire(connection)).close();
        connection.close();
    }
}
