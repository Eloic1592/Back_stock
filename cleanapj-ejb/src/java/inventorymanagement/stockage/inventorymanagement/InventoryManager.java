package inventorymanagement.stockage.inventorymanagement;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;

import inventorymanagement.stockage.distribution.Distribution;
import inventorymanagement.stockage.distribution.Vuedistribution;
import inventorymanagement.stockage.inventaire.Inventaire;
import inventorymanagement.stockage.inventaire.Vueinventaire;
import inventorymanagement.stockage.stockage.Stockage;
import inventorymanagement.stockage.stockage.Vuestockage;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class InventoryManager extends HServiceManager implements InventorySignature {

    ArticleManager articleManager=new ArticleManager();
    @Override
    public void createdistribution(Distribution distribution, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (distribution.getIddistribution()!=null) {
            distribution.updateToTable(connection);
            return;
        }else {
            distribution.construirePK(connection);
            CGenUtil.save(distribution, connection);

        }
    }

    @Override
    public void createstockage(Stockage stockage, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (stockage.getIdstockage()!=null) {
            stockage.updateToTable(connection);
            return;
        }else {
            stockage.construirePK(connection);
            CGenUtil.save(stockage, connection);

        }
    }

    @Override
    public void createinventaire(Inventaire inventaire, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (inventaire.getIdinventaire()!=null) {
            inventaire.updateToTable(connection);
            return;
        }else {
            inventaire.construirePK(connection);
            CGenUtil.save(inventaire, connection);

        }
    }

    @Override
    public Vuedistribution[] listdistribution(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Vuedistribution[] data=(Vuedistribution[])CGenUtil.rechercher(new Vuedistribution(), null, null, connection, "");
        return data;
    }

    @Override
    public Vuestockage[] liststockage(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Vuestockage[] data=(Vuestockage[])CGenUtil.rechercher(new Vuestockage(), null, null, connection, "");
        return data;
    }

    @Override
    public Vueinventaire[] listinventaire(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Vueinventaire[] data=(Vueinventaire[])CGenUtil.rechercher(new Vueinventaire(), null, null, connection, "");
        return data;
    }

    public InventoryPagelist distribution(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setVuedistributions(this.listdistribution(connection));
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist stockage(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setVuestockages(this.liststockage(connection));
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist inventaire(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setVueinventaires(this.listinventaire(connection));
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

}
