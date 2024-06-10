package inventorymanagement.stockage.inventorymanagement;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;

import inventorymanagement.depot.DepotManager;
import inventorymanagement.emplacement.EmplacementManager;
import inventorymanagement.stockage.distribution.Distribution;
import inventorymanagement.stockage.distribution.Vuedistribution;
import inventorymanagement.stockage.inventaire.Calendrierinventaire;
import inventorymanagement.stockage.inventaire.Inventaire;
import inventorymanagement.stockage.inventaire.Vueinventaire;
import inventorymanagement.stockage.stockage.Stockage;
import inventorymanagement.stockage.stockage.Vuestockage;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class InventoryManager extends HServiceManager implements InventorySignature {

    ArticleManager articleManager=new ArticleManager();
    DepotManager depotManager =new DepotManager();
    EmplacementManager emplacementManager=new EmplacementManager();
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

    @Override
    public Stockage getstockage(String idstockage, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockage[] data=(Stockage[])CGenUtil.rechercher(new Stockage(), null, null, connection, "and idstockage='"+idstockage+"'");
        return data[0];
    }

    @Override
    public Distribution getdistribution(String idistribution, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Distribution[] data=(Distribution[])CGenUtil.rechercher(new Distribution(), null, null, connection, "and iddistribution='"+idistribution+"'");
        return data[0];
    }

    @Override
    public Inventaire getinventaire(String idinventaire, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Inventaire[] data=(Inventaire[])CGenUtil.rechercher(new Inventaire(), null, null, connection, "and idinventaire='"+idinventaire+"'");
        return data[0];
    }

    public InventoryPagelist distribution(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setVuedistributions(this.listdistribution(connection));

        return inventoryPagelist;
    }

    public InventoryPagelist stockage(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setVuestockages(this.liststockage(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist inventaire(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setVueinventaires(this.listinventaire(connection));
        return inventoryPagelist;
    }
    public InventoryPagelist editdistribution(String iddistribution,Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setDistribution(this.getdistribution(iddistribution,connection));
        inventoryPagelist.setArticles(articleManager.getall(connection));
        inventoryPagelist.setDepots(depotManager.getall(connection));
        inventoryPagelist.setListeemplacements(emplacementManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist editstockage(String idstockage,Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setStockage(this.getstockage(idstockage,connection));
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist editinventaire(String idinventaire,Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setInventaire(this.getinventaire(idinventaire,connection));
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist distributionform(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setArticles(articleManager.getall(connection));
        inventoryPagelist.setDepots(depotManager.getall(connection));
        inventoryPagelist.setListeemplacements(emplacementManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist stockageform(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist inventaireform(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setArticles(articleManager.getall(connection));
        return inventoryPagelist;
    }

//    Calendrier
    public void createcalendrier(Calendrierinventaire calendrierinventaire, Connection connection) throws Exception {
    connection = this.getConnection(connection);
        if (calendrierinventaire.getIdcalendrierinventaire()!=null) {
            calendrierinventaire.updateToTable(connection);
            return;
        }else {
            calendrierinventaire.construirePK(connection);
            CGenUtil.save(calendrierinventaire, connection);

         }
    }
    public Calendrierinventaire getcalendrier(String idcalendrier,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Calendrierinventaire[] data=(Calendrierinventaire[])CGenUtil.rechercher(new Calendrierinventaire(), null, null, connection, "and idcalendrierinventaire='"+idcalendrier+"'");
        return data[0];
    }

    public Calendrierinventaire[] calendriercree(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Calendrierinventaire[] data=(Calendrierinventaire[])CGenUtil.rechercher(new Calendrierinventaire(), null, null, connection, "and rownum<=5");
        return data;
    }

    public Calendrierinventaire[] listecalendrierinventaire(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Calendrierinventaire[] data=(Calendrierinventaire[])CGenUtil.rechercher(new Calendrierinventaire(), null, null, connection, "and TRUNC(datecalendrier) = TRUNC(SYSDATE)");
        return data;
    }

    public InventoryPagelist calendrier(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setCalendriercree(calendriercree(connection));
        inventoryPagelist.setCalendrierinventaires(listecalendrierinventaire(connection));
        return inventoryPagelist;
    }

    public InventoryPagelist calendriernotif(Connection connection) throws Exception{
        connection=this.getConnection(connection);
        InventoryPagelist inventoryPagelist=new InventoryPagelist();
        inventoryPagelist.setCalendrierinventaires(listecalendrierinventaire(connection));
        return inventoryPagelist;
    }



}
