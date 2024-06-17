package inventorymanagement.mouvement.mouvementstock;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;
import inventorymanagement.depot.DepotManager;
import inventorymanagement.etudiant.EtudiantManager;
import inventorymanagement.materiel.materiel.MaterielManager;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictifview;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictif;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysiqueview;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysique;
import inventorymanagement.mouvement.mouvementphysique.totalarticleentree;
import inventorymanagement.mouvement.mouvementphysique.totalarticlesortie;
import inventorymanagement.mouvement.naturemouvement.NaturemouvementManager;
import inventorymanagement.mouvement.naturemouvement.NaturemouvementPageList;
import itusolar.prepare.HServiceManager;


import java.sql.Connection;
import java.util.Arrays;

public class MouvementstockManager extends HServiceManager implements MouvementstockManagerSignature {

    MaterielManager materielManager=new MaterielManager();
    ArticleManager articleManager= new ArticleManager();
    DepotManager depotManager=new DepotManager();
    NaturemouvementManager naturemouvementManager=new NaturemouvementManager();
    EtudiantManager etudiantManager=new EtudiantManager();



    @Override
    public void createmouvementfictif(Mouvementstock mouvementstocks, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (mouvementstocks.getIdmouvementstock()!=null) {
            mouvementstocks.updateToTable(connection);
            return;
        }else {
            mouvementstocks.construirePK(connection);
            Mouvementstock saved = (Mouvementstock) CGenUtil.save(mouvementstocks, connection);
            Arrays.stream(mouvementstocks.getMouvementfictifs()).forEach(item -> item.setIdmouvement(saved.getIdmouvementstock()));
            this.createmvf(mouvementstocks.getMouvementfictifs(), connection);
        }
    }

    @Override
    public void createmouvementphysique(Mouvementphysique mouvementphysique, Connection connection) throws Exception {
        connection = this.getConnection(connection);
//        double total=((mouvementphysique.getQuantite()*mouvementphysique.getPu())*mouvementphysique.getTypemouvement()*-1);
//        mouvementphysique.setTotal(total);

        if (mouvementphysique.getIddetailmouvementphysique()!=null) {
                mouvementphysique.updateToTable(connection);
            return;
        }else {
            mouvementphysique.construirePK(connection);
            CGenUtil.save(mouvementphysique, connection);

        }
    }

     double getmaxstock(String idarticle, Connection connection)throws Exception{
        double sommequantite=0;
        connection=this.getConnection(connection);
        Mouvementphysiqueview[] data=(Mouvementphysiqueview[])CGenUtil.rechercher(new Mouvementphysiqueview(), null, null, connection, " AND idarticle='"+idarticle+"'");
        if(data.length==0) {
            return 0;
        }else {
            for (Mouvementphysiqueview datas : data) {
                sommequantite += datas.getQuantite();
            }
        }
         return sommequantite;
    }


    @Override
    public void createmvf(Mouvementfictif[] mouvementfictif, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Mouvementfictif fictif :mouvementfictif) {
            if (fictif.getIddetailmouvementfictif() != null) {
                fictif.updateToTable(connection);
            }
            fictif.construirePK(connection);
            CGenUtil.save(fictif, connection);
        }
    }

    public void createsinglemvf(Mouvementfictif mouvementfictif, Connection connection) throws Exception {
        connection = this.getConnection(connection);
            if (mouvementfictif.getIddetailmouvementfictif() != null) {
                mouvementfictif.updateToTable(connection);
            }else {
                mouvementfictif.construirePK(connection);
                CGenUtil.save(mouvementfictif, connection);
            }

    }

    public Mouvementfictifview[] castdatamf(Object[] datas) {

        Mouvementfictifview[] mouvementfictifs = new Mouvementfictifview[datas.length];
        for (int i = 0; i < datas.length; i++) {
            mouvementfictifs[i] = (Mouvementfictifview) datas[i];
        }
        return mouvementfictifs;
    }

    @Override
    public void createmvp(Mouvementphysique[] mouvementphysique, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Mouvementphysique physique :mouvementphysique) {
            if (physique.getIddetailmouvementphysique() != null) {
                physique.updateToTable(connection);
            }
            physique.construirePK(connection);
            CGenUtil.save(physique, connection);
        }
    }

    public void createsinglemvp(Mouvementphysique mouvementphysique, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (mouvementphysique.getIddetailmouvementphysique() != null) {
            mouvementphysique.updateToTable(connection);
        }
        else {
            mouvementphysique.construirePK(connection);
            CGenUtil.save(mouvementphysique, connection);
        }

    }
    public Mouvementphysiqueview[] castdatamp(Object[] datas) {
        Mouvementphysiqueview[] mouvementphysiques = new Mouvementphysiqueview[datas.length];
        for (int i = 0; i < datas.length; i++) {
            mouvementphysiques[i] = (Mouvementphysiqueview) datas[i];
        }
        return mouvementphysiques;
    }


    public Mouvementstockview[] castdatamvt(Object[] datas) {
        Mouvementstockview[] mouvementstocks = new Mouvementstockview[datas.length];
        for (int i = 0; i < datas.length; i++) {
            mouvementstocks[i] = (Mouvementstockview) datas[i];
        }
        return mouvementstocks;
    }

    public Mouvementphysiqueview[] castmvp(Object[] datas) {
        Mouvementphysiqueview[] mouvementphysiqueviews = new Mouvementphysiqueview[datas.length];
        for (int i = 0; i < datas.length; i++) {
            mouvementphysiqueviews[i] = (Mouvementphysiqueview) datas[i];
        }
        return mouvementphysiqueviews;
    }

//    Liste mouvement stock physique
    public Mouvementphysiqueview[] getliststockphysique(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Mouvementphysiqueview(), null, null, connection, "");
        return castmvp(data);
    }

    public Mouvementstockview[] getliststockfictif(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Mouvementstockview(), new String[0], new String[0], connection, "");
        return castdatamvt(data);
    }

//Detail mouvement
    public Mouvementfictifview[] getdetailfictif(String idmouvementstock,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Mouvementfictifview(), new String[0], new String[0], connection, " AND idmouvementstock='"+idmouvementstock+"'");
        return castdatamf(data);
    }

    public Mouvementphysiqueview[] getdetailphysique(String idmouvementstock,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data=CGenUtil.rechercher(new Mouvementphysiqueview(), new String[0], new String[0], connection, " AND idmouvementstock='"+idmouvementstock+"'");
        return castdatamp(data);
    }

//    Contenu page
    @Override
    public MouvementstockPageList contentlistphysique(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementphysiques(this.getliststockphysique(connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setListearticles(articleManager.getlistarticle(connection));
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getmouvementphysiques(connection));

        return mouvementstockPageList;
    }

    @Override
    public MouvementstockPageList contentlistfictif(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementStocks(this.getliststockfictif(connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setListemateriels(materielManager.getall(connection));
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getmouvementfictif(connection));
        mouvementstockPageList.setEtudiants(etudiantManager.getall(connection));

        return mouvementstockPageList;
    }


    public MouvementstockPageList contentdetaillistephysique(String idmouvementstock,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementphysiques(this.getdetailphysique(idmouvementstock,connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setArticles(articleManager.getall(connection));
        return mouvementstockPageList;
    }


    public MouvementstockPageList contentdetaillistefictif(String idmouvementstock,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementfictifs(this.getdetailfictif(idmouvementstock,connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setListemateriels(materielManager.getall(connection));
        mouvementstockPageList.setEtudiants(etudiantManager.getall(connection));
        return mouvementstockPageList;
    }

    //Formulaire
    public MouvementstockPageList contentformphysique(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getmouvementphysiques(connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setArticles(articleManager.getall(connection));
        return mouvementstockPageList;
    }


    public MouvementstockPageList contentformfictif(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getmouvementfictif(connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setListemateriels(materielManager.getall(connection));
        mouvementstockPageList.setEtudiants(etudiantManager.getall(connection));
        return mouvementstockPageList;
    }

    public Mouvementphysiqueview getOne(String iddetailmouvementphysique, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Mouvementphysiqueview[] data=(Mouvementphysiqueview[])CGenUtil.rechercher(new Mouvementphysiqueview(), new String[0], new String[0], connection, "and iddetailmouvementphysique='"+iddetailmouvementphysique+"'");
        return data[0];
    }

    public Mouvementfictif getOnef(String iddetailmouvementfictif, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Mouvementfictif[] data=(Mouvementfictif[])CGenUtil.rechercher(new Mouvementfictif(), new String[0], new String[0], connection, "and iddetailmouvementfictif='"+iddetailmouvementfictif+"'");
        return data[0];
    }

    public Mouvementstockview getOnestock(String idmouvementstock, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Mouvementstockview[] data=(Mouvementstockview[])CGenUtil.rechercher(new Mouvementstockview(), new String[0], new String[0], connection, "and idmouvementstock='"+idmouvementstock+"'");
        return data[0];
    }


    public MouvementstockPageList getOnePage(String iddetailmouvementphysique,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementphysique(getOne(iddetailmouvementphysique,connection));
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getmouvementphysiques(connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setListearticles(articleManager.getlistarticle(connection));
        return mouvementstockPageList;
    }

    public MouvementstockPageList getOnePagef(String iddetailmouvementfictif,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementfictif(getOnef(iddetailmouvementfictif,connection));
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getmouvementphysiques(connection));
        mouvementstockPageList.setDepots(depotManager.getall(connection));
        mouvementstockPageList.setListemateriels(materielManager.getall(connection));
        mouvementstockPageList.setEtudiants(etudiantManager.getall(connection));
        return mouvementstockPageList;
    }

    public MouvementstockPageList OnePageStock(String idmouvementstock,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setEtudiants(etudiantManager.getall(connection));
        mouvementstockPageList.setNaturemouvements(naturemouvementManager.getall(connection));
        mouvementstockPageList.setMouvementstock(getOnestock(idmouvementstock,connection));
        return mouvementstockPageList;
    }
    @Override
    public void importexcelfile(Connection connection,String filename) throws Exception {

    }

    public MouvementstockPageList discharge(String idmouvementstock,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        MouvementstockPageList mouvementstockPageList=new MouvementstockPageList();
        mouvementstockPageList.setMouvementstock(getOnestock(idmouvementstock,connection));
        mouvementstockPageList.setMouvementfictifs(getdetailfictif(idmouvementstock,connection));
        return mouvementstockPageList;
    }



    public double sommeentree(Connection connection) throws Exception {
        double sommeentree=0;
        totalarticleentree[] data = naturemouvementManager.totalarticleentree(connection);
        if(data[0]!=null){
            sommeentree=data[0].getTotal();
        }

        return sommeentree;
    }
    public double sommesortie(Connection connection) throws Exception {
        double sommesortie=0;
        totalarticlesortie[] data = naturemouvementManager.totalarticlesortie(connection);
        if(data[0]!=null){
            sommesortie=data[0].getTotal();
        }

        return sommesortie;
    }

    public NaturemouvementPageList statnaturelist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        NaturemouvementPageList naturemouvementPageList = new NaturemouvementPageList();
        naturemouvementPageList.setStatnaturemouvements(naturemouvementManager.getstatmouvementparannee(connection));
        naturemouvementPageList.setTotalentree(sommeentree(connection));
        naturemouvementPageList.setTotalsortie(sommesortie(connection));
        naturemouvementPageList.setTotalarticleentrees(naturemouvementManager.gettotalarticleentreeparannee(connection));
        naturemouvementPageList.setTotalarticlesorties(naturemouvementManager.gettotalarticlesortieparannee(connection));
        return naturemouvementPageList;
    }

    public NaturemouvementPageList statnature(Connection connection,String idnaturemouvement) throws Exception {
        connection=this.getConnection(connection);
        NaturemouvementPageList naturemouvementPageList = new NaturemouvementPageList();
        naturemouvementPageList.setTotalarticlemouvements(naturemouvementManager.totalarticlemouvement(connection,idnaturemouvement));
        return naturemouvementPageList;
    }


}
