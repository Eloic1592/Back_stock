package inventorymanagement.mouvement.naturemouvement;

import bean.CGenUtil;

import inventorymanagement.mouvement.mouvementphysique.totalarticleentree;
import inventorymanagement.mouvement.mouvementphysique.totalarticlesortie;
import inventorymanagement.mouvement.utils.Utils;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class NaturemouvementManager extends HServiceManager implements NaturemouvementManagerSignature {

    public NaturemouvementManagerSignature naturemouvementManagerSignature;

    public NaturemouvementManager() {
        this.naturemouvementManagerSignature=naturemouvementManagerSignature;
    }

    @Override
    public void create(Naturemouvement naturemouvement, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (naturemouvement.getIdnaturemouvement()!=null) {
            naturemouvement.updateToTable(connection);
            return;
        }
        naturemouvement.construirePK(connection);
        CGenUtil.save(naturemouvement, connection);
    }


    @Override
    public Naturemouvement[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Naturemouvement[] data= (Naturemouvement[]) CGenUtil.rechercher(new Naturemouvement(), new String[0], new String[0], connection, "");
        return data;
    }

    public Listenature[] getlistenature(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Listenature[] data= (Listenature[]) CGenUtil.rechercher(new Listenature(), new String[0], new String[0], connection, "");
        return data;
    }

    @Override
    public NaturemouvementPageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        NaturemouvementPageList naturemouvementPageList = new NaturemouvementPageList();
        naturemouvementPageList.setNaturemouvements(this.getlistenature(connection));
        return naturemouvementPageList;
    }

    public Naturemouvement[] getmouvementphysiques(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Naturemouvement[] data = (Naturemouvement[])CGenUtil.rechercher(new Naturemouvement(), new String[0], new String[0], connection, "");
        return data;
    }

    public Naturemouvement[] getmouvementfictif(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Naturemouvement[] data =(Naturemouvement[]) CGenUtil.rechercher(new Naturemouvement(), new String[0], new String[0], connection, "");
        return data;
    }

//    Statistiques nature mouvement
    public Statnaturemouvement[] getstat(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Statnaturemouvement[] data = (Statnaturemouvement[])CGenUtil.rechercher(new Statnaturemouvement(), new String[0], new String[0], connection, "");
        return data;
    }


//    Mois actuel


    //    Statistiques nature mouvement
    public Statnaturemouvement[] getstatmouvementparannee(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        List<Statnaturemouvement> dataList = new ArrayList<>();
        Naturemouvement[] listnaturemouvement=this.getall(connection);
        Statnaturemouvement[] data=getstat(connection);
        Statnaturemouvement statnaturemouvement=null;
        for (int annee = 2011; annee <= Utils.getCurrentYear(); annee++) {
            for (int mois = 1; mois <= 12; mois++) {
                for (Naturemouvement naturemouvement : listnaturemouvement) {
                    boolean found = false;
                    for (Statnaturemouvement stat : data) {
                        statnaturemouvement=new Statnaturemouvement();
                        if (stat.getAnnee()==annee&& stat.getMois() == mois && stat.getIdnaturemouvement().equals(naturemouvement.getIdnaturemouvement())) {
                            found = true;
                            statnaturemouvement.setDepense(stat.getDepense());
                            statnaturemouvement.setGain(stat.getGain());
                            statnaturemouvement.setBenefice(stat.getBenefice());
                            break;
                        }
                    }
                    if (!found) {
                        // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                        Statnaturemouvement stat = new Statnaturemouvement();
                        stat.setAnnee(annee);
                        stat.setMois(mois);
                        stat.setMois_nom(Utils.getNomMois(mois));
                        stat.setIdnaturemouvement(naturemouvement.getIdnaturemouvement());
                        stat.setNaturemouvement(naturemouvement.getNaturemouvement());
                        stat.setDepense(0);
                        stat.setGain(0);
                        stat.setBenefice(0);
                        // Ajout de l'objet à la liste
//                        System.out.println(stat.getAnnee()+"annee"+stat.getMois()+"mois tsy misy");
                        dataList.add(stat);
                    }else {
                        // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                        Statnaturemouvement stat = new Statnaturemouvement();
                        stat.setAnnee(annee);
                        stat.setMois(mois);
                        stat.setMois_nom(Utils.getNomMois(mois));
                        stat.setMois_nom(stat.getMois_nom());
                        stat.setIdnaturemouvement(naturemouvement.getIdnaturemouvement());
                        stat.setNaturemouvement(naturemouvement.getNaturemouvement());
                        stat.setDepense(statnaturemouvement.getDepense());
                        stat.setGain(statnaturemouvement.getGain());
                        stat.setBenefice(statnaturemouvement.getBenefice());
                        dataList.add(stat);
//                        System.out.println(stat.getAnnee()+"annee"+stat.getMois()+"mois misy");

                    }
                }
            }
        }

        List<Statnaturemouvement> currentMonthData =new ArrayList<>();
        List<Statnaturemouvement> otherData =new ArrayList<>();

        for (Statnaturemouvement stat : dataList) {
            if (stat.getAnnee()==Utils.getCurrentYear()) {
                if(stat.getMois() == Utils.getCurrentMonth()) {
                    currentMonthData.add(stat);
                }
            } else {
                otherData.add(stat);
            }
        }
        currentMonthData.addAll(otherData);
        dataList = currentMonthData;

        return  dataList.toArray(new Statnaturemouvement[0]);

    }

    public Naturemouvement getOne(String idnaturemouvement, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Naturemouvement[] data=(Naturemouvement[])CGenUtil.rechercher(new Naturemouvement(), new String[0], new String[0], connection, "and idnaturemouvement='"+idnaturemouvement+"'");
        return data[0];
    }

    //    Statistiques nature mouvement
    public Cyclemouvement[] getcyclemouvement(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Cyclemouvement[] data = (Cyclemouvement[])CGenUtil.rechercher(new Cyclemouvement(), new String[0], new String[0], connection, "");
        return data;

    }

    public Statnaturemouvement getstatanneemois(Connection connection,int annee,int mois,String naturemouvement) throws Exception{
        connection=this.getConnection(connection);
        Statnaturemouvement[] data = (Statnaturemouvement[])CGenUtil.rechercher(new Statnaturemouvement(), new String[0], new String[0], connection, "AND annee="+annee+" and mois="+mois+" and idnaturemouvement='"+naturemouvement+"'");
        return data[0];
    }

    public totalarticlemouvement[] totalarticlemouvement(Connection connection,String idnaturemouvement) throws Exception{
        connection=this.getConnection(connection);
        totalarticlemouvement[] data = (totalarticlemouvement[])CGenUtil.rechercher(new totalarticlemouvement(), new String[0], new String[0], connection, " AND typemouvement='"+idnaturemouvement+"'");
        return data;
    }

    public totalarticleentree[] totalarticleentree(Connection connection)throws  Exception{
        connection=this.getConnection(connection);
        totalarticleentree[] totalarticleentrees=(totalarticleentree[]) CGenUtil.rechercher(new totalarticleentree(),null,null,connection,"");
        return totalarticleentrees;
    }



    public totalarticlesortie[] totalarticlesortie(Connection connection)throws  Exception{
        connection=this.getConnection(connection);
        totalarticlesortie[] totalarticlesorties=(totalarticlesortie[]) CGenUtil.rechercher(new totalarticlesortie(),null,null,connection,"");
        return totalarticlesorties;
    }

    public totalarticleentree getotalentreecurrentMonth(int annee, int mois,Connection connection)throws  Exception{
        connection=this.getConnection(connection);
        totalarticleentree[] totalarticleentrees=(totalarticleentree[]) CGenUtil.rechercher(new totalarticleentree(),null,null,connection,"and annee="+annee+" and mois="+mois+"");
        return totalarticleentrees[0];
    }



    public totalarticlesortie getotalsortiecurrentMonth(int annee,int mois,Connection connection)throws  Exception{
        connection=this.getConnection(connection);
        totalarticlesortie[] totalarticlesorties=(totalarticlesortie[]) CGenUtil.rechercher(new totalarticlesortie(),null,null,connection,"and annee="+annee+" and mois="+mois+"");
        return totalarticlesorties[0];
    }

    public totalarticleentree[] gettotalarticleentreeparannee(Connection connection)throws  Exception{
        connection = this.getConnection(connection);
        List<totalarticleentree> dataList = new ArrayList<>();
        totalarticleentree[] totalarticleentrees=this.totalarticleentree(connection);
        double total=0;
        for(int annee=2011; annee<=Utils.getCurrentYear(); annee++){
            for (int mois = 1; mois <= 12; mois++) {
                boolean found = false;
                for(totalarticleentree totalarticleentree:totalarticleentrees){
                    if(totalarticleentree.getAnnee()==annee && totalarticleentree.getMois()==mois){
                        found = true;
                        total=totalarticleentree.getTotal();
                        break;
                    }

                }
                if (!found) {
                    // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                    totalarticleentree stat = new totalarticleentree();
                    stat.setAnnee(annee);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setMois(mois);
                    stat.setTotal(0);
                    // Ajout de l'objet à la liste
//                    System.out.println(stat.getAnnee()+"annee"+stat.getMois()+"mois tsy misy");
                    dataList.add(stat);
                }else {
                    // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                    totalarticleentree stat = new totalarticleentree();
                    stat.setAnnee(annee);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setMois(mois);
                    stat.setTotal(total);
                    dataList.add(stat);
//                    System.out.println(stat.getAnnee()+"annee"+stat.getMois()+"mois misy");

                }
            }

        }
        List<totalarticleentree> currentMonthData =new ArrayList<>();
        List<totalarticleentree> otherData =new ArrayList<>();

        for (totalarticleentree stat : dataList) {
            if (stat.getAnnee() ==Utils.getCurrentYear()) {
                if(stat.getMois() == Utils.getCurrentMonth()) {
                    currentMonthData.add(stat);
                }
            } else {
                otherData.add(stat);
            }
        }
        currentMonthData.addAll(otherData);
        dataList = currentMonthData;
        return dataList.toArray(new totalarticleentree[0]);
    }


    public totalarticlesortie[] gettotalarticlesortieparannee(Connection connection)throws  Exception{
        connection = this.getConnection(connection);
        List<totalarticlesortie> dataList = new ArrayList<>();
        totalarticlesortie[] totalarticlesorties=this.totalarticlesortie(connection);
        double total=0;
        for(int annee=2011; annee<=Utils.getCurrentYear(); annee++){
            for (int mois = 1; mois <= 12; mois++) {
                boolean found = false;
                for(totalarticlesortie totalarticlesortie:totalarticlesorties){
                    if(totalarticlesortie.getAnnee()==annee && totalarticlesortie.getMois()==mois){
                        found = true;
                        total=totalarticlesortie.getTotal();
                        break;
                    }

                }
                if (!found) {
                    // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                    totalarticlesortie stat = new totalarticlesortie();
                    stat.setAnnee(annee);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setMois(mois);
                    stat.setTotal(0);
                    // Ajout de l'objet à la liste
                    dataList.add(stat);
                }else {
                    // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                    totalarticlesortie stat = new totalarticlesortie();
                    stat.setAnnee(annee);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setMois(mois);
                    stat.setTotal(total);
                    dataList.add(stat);


                }
            }

        }
        List<totalarticlesortie> currentMonthData =new ArrayList<>();
        List<totalarticlesortie> otherData =new ArrayList<>();

        for (totalarticlesortie stat : dataList) {
            if (stat.getAnnee() ==Utils.getCurrentYear()) {
                if(stat.getMois() == Utils.getCurrentMonth()) {
                    currentMonthData.add(stat);
                }
            } else {
                otherData.add(stat);
            }
        }
        currentMonthData.addAll(otherData);
        dataList = currentMonthData;
        return dataList.toArray(new totalarticlesortie[0]);
    }


//    Cycle de mouvement
    public NaturemouvementPageList cyclenaturelist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        NaturemouvementPageList naturemouvementPageList = new NaturemouvementPageList();
        naturemouvementPageList.setCyclemouvements(this.cyclemouvementsparannee(connection));
        naturemouvementPageList.setNaturemouvements(this.getlistenature(connection));
        return naturemouvementPageList;
    }

    public Cyclemouvement[] cyclemouvementsparannee(Connection connection) throws Exception{
        connection = this.getConnection(connection);
        List<Cyclemouvement> dataList = new ArrayList<>();
        Cyclemouvement[] cyclemouvements=this.getcyclemouvement(connection);
        Naturemouvement[] naturemouvements=this.getall(connection);
        Cyclemouvement data =null;
        for (Naturemouvement naturemouvement : naturemouvements) {
        for(int annee=2011; annee<=Utils.getCurrentYear(); annee++){
            for (int mois = 1; mois <= 12; mois++) {
                boolean found = false;
                    for (Cyclemouvement cyclemouvement : cyclemouvements) {
                        data = new Cyclemouvement();
                        data.setIdnaturemouvement(naturemouvement.getIdnaturemouvement());
                        data.setNaturemouvement(naturemouvement.getNaturemouvement());
                        if (cyclemouvement.getAnnee() == annee && cyclemouvement.getMois() == mois && cyclemouvement.getIdnaturemouvement().equals(naturemouvement.getIdnaturemouvement())) {
                            found = true;
                            data.setEntree(cyclemouvement.getEntree());
                            data.setSortie(cyclemouvement.getSortie());
                            break;
                        }
                    }
                    if (!found) {
                        // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                        Cyclemouvement stat = new Cyclemouvement();
                        stat.setAnnee(annee);
                        stat.setMois_nom(Utils.getNomMois(mois));
                        stat.setMois(mois);
                        stat.setEntree(0);
                        stat.setSortie(0);
                        stat.setIdnaturemouvement(data.getIdnaturemouvement());
                        stat.setNaturemouvement(data.getNaturemouvement());
                        System.out.println(stat.getAnnee() + "annee" + stat.getMois() + "mois tsy misy");
                        dataList.add(stat);
                    } else {
                        // Création d'un objet Statnaturemouvement avec des valeurs par défaut (0 pour depense et gain)
                        Cyclemouvement stat = new Cyclemouvement();
                        stat.setAnnee(annee);
                        stat.setMois_nom(Utils.getNomMois(mois));
                        stat.setMois(mois);
                        stat.setEntree(data.getEntree());
                        stat.setSortie(data.getSortie());
                        stat.setIdnaturemouvement(data.getIdnaturemouvement());
                        stat.setNaturemouvement(data.getNaturemouvement());
                        System.out.println(stat.getAnnee() + "annee" + stat.getMois() + "mois misy");
                        dataList.add(stat);


                    }
                }
            }
        }
        List<Cyclemouvement> currentMonthData =new ArrayList<>();
        List<Cyclemouvement> otherData =new ArrayList<>();

        for (Cyclemouvement stat : dataList) {
            if (stat.getAnnee() ==Utils.getCurrentYear() && stat.getMois() == Utils.getCurrentMonth()) {
                    currentMonthData.add(stat);
            } else {
                otherData.add(stat);
            }
        }
        currentMonthData.addAll(otherData);
        dataList = currentMonthData;
        return dataList.toArray(new Cyclemouvement[0]);
    }

}
