package inventorymanagement.emplacement;

import bean.CGenUtil;
import inventorymanagement.article.Article;
import inventorymanagement.article.ArticlePageList;
import inventorymanagement.article.Listearticle;
import inventorymanagement.depot.DepotManager;
import inventorymanagement.etudiant.ListeEtudiant;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class EmplacementManager extends HServiceManager implements  EmplacementManagerSignature {
    EmplacementManagerSignature emplacementManagerSignature;
    DepotManager depotManager= new DepotManager();


    @Override
    public Listeemplacement[] getall(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Listeemplacement[] data = (Listeemplacement[])CGenUtil.rechercher(new Listeemplacement(), null, null, connection, "");
        return data;
    }

    @Override
    public void create(Emplacement emplacement, Connection connection) throws Exception {
        connection = this.getConnection(connection);

        if (emplacement.getIdemplacement()!=null) {
            emplacement.updateToTable(connection);
            return;
        }
        emplacement.construirePK(connection);
        CGenUtil.save(emplacement, connection);
    }


    @Override
    public Emplacement getOne(String idemplacement,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Emplacement[] data=(Emplacement[])CGenUtil.rechercher(new Emplacement(), new String[0], new String[0], connection, "and idemplacement='"+idemplacement+"'");
        return data[0];
    }

    @Override
    public EmplacementPageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        EmplacementPageList emplacementPageList=new EmplacementPageList();
        emplacementPageList.setListeemplacements(this.getall(connection));
        emplacementPageList.setDepot(depotManager.getall(connection));
        return emplacementPageList;
    }

    public EmplacementPageList getOnePage(String idemplacement,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        EmplacementPageList emplacementPageList=new EmplacementPageList();
        emplacementPageList.setEmplacement(getOne(idemplacement,connection));
        emplacementPageList.setDepot(depotManager.getall(connection));
        return emplacementPageList;
    }

    public Listeemplacement[] getbydepot(String iddepot,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Listeemplacement[] data = (Listeemplacement[])CGenUtil.rechercher(new Listeemplacement(), null, null, connection, "and iddepot='"+iddepot+"'");
        return data;
    }
    public EmplacementPageList getlistemplacement(String iddepot,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        EmplacementPageList emplacementPageList=new EmplacementPageList();
        emplacementPageList.setListeemplacements(getbydepot(iddepot,connection));
        return emplacementPageList;
    }




}
