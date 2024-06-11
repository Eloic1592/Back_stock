package inventorymanagement.materiel.materiel;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;
import inventorymanagement.article.ArticlePageList;
import inventorymanagement.article.Listearticle;
import inventorymanagement.article.Stockarticle;
import inventorymanagement.materiel.categoriemateriel.CategoriematerielManager;
import inventorymanagement.materiel.typemateriel.TypematerielManager;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictif;
import itusolar.prepare.HServiceManager;
import utilitaire.XlsImport;

import java.sql.Connection;

public class MaterielManager extends HServiceManager implements  MaterielManagerSignature{
    MaterielManagerSignature materielManagerSignature;
    TypematerielManager typematerielManager=new TypematerielManager();

    public MaterielManager() {
        this.materielManagerSignature=materielManagerSignature;

    }

    @Override
    public void create(Materiel materiel, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (materiel.getIdmateriel()!=null) {
            materiel.updateToTable(connection);
            return;
        }
        materiel.construirePK(connection);
        CGenUtil.save(materiel, connection);
    }

    @Override
    public Listemateriel[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Listemateriel(), new String[0], new String[0], connection, "");
        return cast(data);
    }

    @Override
    public Materiel[] getallmateriel(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Materiel(), new String[0], new String[0], connection, "");
        return castmateriel(data);
    }
    public Listemateriel[] cast(Object[] datas) {
        Listemateriel[] materiels = new Listemateriel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            materiels[i] = (Listemateriel) datas[i];
        }
        return materiels;
    }
    public Materiel[] castmateriel(Object[] datas) {
        Materiel[] materiels = new Materiel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            materiels[i] = (Materiel) datas[i];
        }
        return materiels;
    }

    public Stockmateriel[] getstockmateriel(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stockmateriel[] data=(Stockmateriel[])CGenUtil.rechercher(new Stockmateriel(), new String[0], new String[0], connection, "");
        return data;
    }
    public Stocktypemateriel[] getstocktypemateriel(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stocktypemateriel[] data=(Stocktypemateriel[])CGenUtil.rechercher(new Stocktypemateriel(), new String[0], new String[0], connection, "");
        return data;
    }


    public UtilisationMateriel[] getstockutilisation(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        UtilisationMateriel[] data=(UtilisationMateriel[])CGenUtil.rechercher(new UtilisationMateriel(), new String[0], new String[0], connection, "");
        return data;
    }


    @Override
    public MaterielPageList contentlist(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        MaterielPageList materielPageList=new MaterielPageList();
        materielPageList.setTypemateriels(typematerielManager.getall(connection));
        materielPageList.setListemateriels(this.getall(connection));
        return materielPageList;
    }

    @Override
    public MaterielPageList contentform(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        MaterielPageList materielPageList=new MaterielPageList();
        materielPageList.setTypemateriels(typematerielManager.getall(connection));
        return materielPageList;
    }

    @Override
    public Listemateriel getOne(String idmateriel, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Listemateriel[] data=(Listemateriel[])CGenUtil.rechercher(new Listemateriel(), new String[0], new String[0], connection, "and idmateriel='"+idmateriel+"'");
        return data[0];
    }

    public double countmateriel(Connection connection) throws Exception {
        double count=0;
        connection=this.getConnection(connection);
        Listemateriel[] data=(Listemateriel[])CGenUtil.rechercher(new Listemateriel(), new String[0], new String[0], connection, "");
        for(Listemateriel datas: data){
            count++;
        }
        return count;
    }
    public double materiellibre(Connection connection) throws Exception {
        double count=0;
        connection=this.getConnection(connection);
        Listemateriel[] data=(Listemateriel[])CGenUtil.rechercher(new Listemateriel(), new String[0], new String[0], connection, "and statutmateriel=0");
        for(Listemateriel datas: data){
            count++;
        }
        return count;
    }
    public double materieloccupe(Connection connection) throws Exception {
        double count=0;
        connection=this.getConnection(connection);
        Listemateriel[] data=(Listemateriel[])CGenUtil.rechercher(new Listemateriel(), new String[0], new String[0], connection, "and statutmateriel=1");
        for(Listemateriel datas: data){
            count++;
        }
        return count;
    }



    public MaterielPageList stocklist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MaterielPageList materielPageList=new MaterielPageList();
        materielPageList.setStockmateriels(this.getstockmateriel(connection));
        materielPageList.setTypemateriels(this.typematerielManager.getall(connection));
        materielPageList.setCountmateriel(this.countmateriel(connection));
        materielPageList.setCountmateriellibre(this.materiellibre(connection));
        materielPageList.setCountmaterieloccupe(this.materieloccupe(connection));
        materielPageList.setStocktypemateriels(this.getstocktypemateriel(connection));

       return materielPageList;
    }
    public MaterielPageList stockmateriellist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MaterielPageList materielPageList=new MaterielPageList();
        materielPageList.setUtilisationMateriels(this.getstockutilisation(connection));
        materielPageList.setTypemateriels(this.typematerielManager.getall(connection));

        return materielPageList;
    }

    public MaterielPageList getOnePage(String idmateriel,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MaterielPageList materielPageList=new MaterielPageList();
        materielPageList.setListemateriel(getOne(idmateriel,connection));
        materielPageList.setTypemateriels(typematerielManager.getall(connection));
        return materielPageList;
    }

    public void importexcelfile(Connection connection,Materiel [] materiel) throws Exception {
            connection = this.getConnection(connection);
            for(Materiel materiel1 :materiel) {
                if (materiel1.getIdmateriel() != null) {
                    materiel1.updateToTable(connection);
                    return;
                }
                materiel1.construirePK(connection);
                CGenUtil.save(materiel1, connection);
            }
    }
}
