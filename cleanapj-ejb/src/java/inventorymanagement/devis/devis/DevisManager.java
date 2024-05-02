package inventorymanagement.devis.devis;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;
import inventorymanagement.client.ClientManager;
import inventorymanagement.devis.detaildevis.Detaildevisview;
import itusolar.prepare.HServiceManager;
import inventorymanagement.devis.detaildevis.Detaildevis;

import java.sql.Connection;
import java.util.Arrays;

public class DevisManager extends HServiceManager implements DevisManagerSignature {

    DevisManagerSignature devisManagerSignature;
    ClientManager clientManager= new ClientManager();
    ArticleManager articleManager= new ArticleManager();

    public DevisManager() {
        this.devisManagerSignature = devisManagerSignature;
    }


//    Devis
    @Override
    public void createdevis(Devis devis, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (devis.getIddevis()!=null) {
            devis.updateToTable(connection);
            return;
        }
        devis.construirePK(connection);
        Devis devis1=(Devis) CGenUtil.save(devis, connection);
        Arrays.stream(devis1.getDetaildevis()).forEach(item -> item.setIddevis(devis1.getIddevis()));
        this.createdetails(devis1.getDetaildevis(),connection);

    }

    //Details du devis
    public void createsingledetail(Detaildevis detaildevis, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (detaildevis.getIddetaildevis() != null) {
            detaildevis.updateToTable(connection);
        }else {
            detaildevis.construirePK(connection);
            CGenUtil.save(detaildevis, connection);
        }
    }

    public void createdetails(Detaildevis[] detaildevis, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Detaildevis detaildevis1 :detaildevis) {
            if (detaildevis1.getIddetaildevis() != null) {
                detaildevis1.updateToTable(connection);
            }
            detaildevis1.construirePK(connection);
            CGenUtil.save(detaildevis1, connection);
        }
    }
//    Proforma
    public Clientdevis[] getsingleproforma(String iddevis,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Clientdevis(), new String[0], new String[0], connection, "AND iddevis='"+iddevis+"'");
        return castdevis(data);
    }


    @Override
    public Clientdevis[] getalldevis(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Clientdevis(), new String[0], new String[0], connection, "");
        return castdevis(data);
    }

    @Override
    public inventorymanagement.devis.detaildevis.Detaildevisview[] getalldetailsdevis(String iddevis, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new inventorymanagement.devis.detaildevis.Detaildevisview(), new String[0], new String[0], connection, " AND iddevis='"+iddevis+"'");
        return castdetaildevis(data);
    }

    public Clientdevis[] castdevis(Object[] datas) {
        Clientdevis[] clientdevis = new Clientdevis[datas.length];
        for (int i = 0; i < datas.length; i++) {
            clientdevis[i] = (Clientdevis) datas[i];
        }
        return clientdevis;
    }

    public inventorymanagement.devis.detaildevis.Detaildevisview[] castdetaildevis(Object[] datas) {
        inventorymanagement.devis.detaildevis.Detaildevisview[] detaildevis = new inventorymanagement.devis.detaildevis.Detaildevisview[datas.length];
        for (int i = 0; i < datas.length; i++) {
            detaildevis[i] = (Detaildevisview) datas[i];
        }
        return detaildevis;
    }


    @Override
    public DevisPageList contentlist(Connection connection,String iddevis) throws Exception {
        connection = this.getConnection(connection);
        DevisPageList devisPageList=new DevisPageList();
        devisPageList.setClientdevis(this.getalldevis(connection));
        devisPageList.setClients(clientManager.getall(connection));
        devisPageList.setArticles(articleManager.getall(connection));
        return devisPageList;
    }

    @Override
    public DevisPageList contentform(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        DevisPageList devisPageList=new DevisPageList();
        devisPageList.setClients(clientManager.getall(connection));
        devisPageList.setArticles(articleManager.getall(connection));
        return devisPageList;
    }



    public DevisPageList contentdetaillist(Connection connection,String iddevis) throws Exception {
        connection = this.getConnection(connection);
        DevisPageList devisPageList=new DevisPageList();
        devisPageList.setDetaildevis(this.getalldetailsdevis(iddevis,connection));
        devisPageList.setArticles(articleManager.getall(connection));
        devisPageList.setSomme(devisPageList.sommeproforma());
        devisPageList.setClientdevis(getsingleproforma(iddevis,connection));
        return devisPageList;
    }




}
