package inventorymanagement.dashboard.dashboard;

import bean.CGenUtil;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysiqueview;
import inventorymanagement.mouvement.mouvementphysique.totalarticleentree;
import inventorymanagement.mouvement.utils.Utils;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DashboardManager extends HServiceManager implements DashboardManangerSignature {

    static int debutannee=2011;

    @Override
    public FIFO[] getFIFO(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        FIFO[] data= (FIFO[])CGenUtil.rechercher(new FIFO(), null, null, connection, "");
        return data;
    }

    @Override
    public LIFO[] getLIFO(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        LIFO[] data= (LIFO[])CGenUtil.rechercher(new LIFO(), null, null, connection, "");
        return data;
    }

    @Override
    public Rotationstock[] getrotation(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Rotationstock[] data= (Rotationstock[])CGenUtil.rechercher(new Rotationstock(), null, null, connection, "");
        return data;
    }

    public Dashboard dashboard(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Dashboard dashboard= new Dashboard();
        dashboard.setFifos(this.getFIFO(connection));
        dashboard.setLifos(this.getLIFO(connection));
        dashboard.setRotationstocks(rotationstocks (connection));
        return dashboard;
    }
    public Stockinitialmois[] stockinitialmois(Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Stockinitialmois[] data=(Stockinitialmois[])CGenUtil.rechercher(new Stockinitialmois(), null, null, connection, "");
        return data;
    }
    public Stockfinalmois[] stockfinalmois(Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Stockfinalmois[] data=(Stockfinalmois[])CGenUtil.rechercher(new Stockfinalmois(), null, null, connection, "");
        return data;
    }

    public Stockinitialmois stockinitialunmois(int annee,int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Stockinitialmois[] data=(Stockinitialmois[])CGenUtil.rechercher(new Stockinitialmois(), null, null, connection, "and annee="+annee+" and mois="+mois+"");
        return (data != null && data.length != 0) ? data[0] : null;
    }

    public Stockfinalmois stockfinalunmois(int annee,int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Stockfinalmois[] data=(Stockfinalmois[])CGenUtil.rechercher(new Stockfinalmois(), null, null, connection, "and annee="+annee+" and mois="+mois+"");
//        System.out.print("Stockfinalmois:" +data[0]);
        return (data != null && data.length != 0) ? data[0] : null;
    }

    public double stockmoyen(int annee,int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Stockinitialmois stockinitialmois=stockinitialunmois(annee,mois,connection);
        Stockfinalmois stockfinalmois=stockfinalunmois(annee,mois,connection);
        double quantitestockinitialmois=(stockinitialmois!=null) ? stockinitialmois.getStockinitial():0;
        double quantitestockfinalmois=(stockfinalmois!=null) ? stockfinalmois.getStockfinal():0;
        return (quantitestockinitialmois+quantitestockfinalmois)/2;
    }

    public double valeurstockmoyen(int annee,int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Stockinitialmois stockinitialmois=stockinitialunmois(annee,mois,connection);
        Stockfinalmois stockfinalmois=stockfinalunmois(annee,mois,connection);
        double valeurstockinitialmois=(stockinitialmois!=null) ? stockinitialmois.getPrixinitial():0;
        double valeurstockfinalmois=(stockfinalmois!=null) ? stockfinalmois.getPrixfinal():0;
        return (valeurstockinitialmois+valeurstockfinalmois)/2;
    }




    public Quantitevendumois quantitevendu(int annee,int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        Quantitevendumois[] data=(Quantitevendumois[])CGenUtil.rechercher(new Quantitevendumois(), null, null, connection, "and annee="+annee+" and mois="+mois+"");
        return (data != null && data.length != 0) ? data[0] : null;
    }

    public double rotationstockquantite(int annee,int mois,Connection connection) throws  Exception{
        double quantitemoyenstock=this.stockmoyen(annee,mois,connection);
        Quantitevendumois quantite=quantitevendu(annee, mois, connection);
        double ventequantite=(quantite!=null) ? quantite.getQuantitevendue():0;
        return ventequantite/quantitemoyenstock;
    }

    public double rotationstockvaleur(int annee,int mois,Connection connection) throws  Exception{
        double valeurmoyenstock=this.valeurstockmoyen(annee,mois,connection);
        Quantitevendumois quantite=quantitevendu(annee, mois, connection);
        double totalvente=(quantite!=null) ? quantite.getPrixtotalvendus():0;
        return totalvente/valeurmoyenstock;
    }


    public Stockinitialmois[] stockinitialparmois(Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        List<Stockinitialmois> dataList = new ArrayList<>();
        Stockinitialmois[] stockinitialmois=this.stockinitialmois(connection);
        double stock_initial=0;
        for(int annee = debutannee; annee<= Utils.getCurrentYear(); annee++){
            for (int mois = 1; mois <= 12; mois++) {
                boolean found = false;
                for(Stockinitialmois stockinitialmois1 : stockinitialmois){
                    if(stockinitialmois1.getAnnee()==annee && stockinitialmois1.getMois()==mois){
                        found=true;
                        stock_initial=stockinitialmois1.getStockinitial();
                        break;
                    }
                }
                if (!found) {
                    Stockinitialmois stat = new Stockinitialmois();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setStockinitial(0);
                    dataList.add(stat);
                }else{
                    Stockinitialmois stat = new Stockinitialmois();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setStockinitial(stock_initial);
                    dataList.add(stat);
                }
            }
        }
        return dataList.toArray(new Stockinitialmois[0]);
    }

    public Stockfinalmois[] stockfinalparmois(Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        List<Stockfinalmois> dataList = new ArrayList<>();
        Stockfinalmois[] stockfinalmois=this.stockfinalmois(connection);
        double stock_final=0;
        for(int annee = debutannee; annee<= Utils.getCurrentYear(); annee++){
            for (int mois = 1; mois <= 12; mois++) {
                boolean found = false;
                for(Stockfinalmois stockfinalmois1 : stockfinalmois){
                    if(stockfinalmois1.getAnnee()==annee && stockfinalmois1.getMois()==mois){
                        found=true;
                        stock_final=stockfinalmois1.getStockfinal();
                        break;
                    }
                }
                if (!found) {
                    Stockfinalmois stat = new Stockfinalmois();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setStockfinal(0);
                    dataList.add(stat);
                }else{
                    Stockfinalmois stat = new Stockfinalmois();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setStockfinal(stock_final);
                    dataList.add(stat);
                }
            }
        }
        return dataList.toArray(new Stockfinalmois[0]);
    }

    public Stockfinalmois[] stockfinalunmois(int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        List<Stockfinalmois> dataList = new ArrayList<>();
        Stockfinalmois[] stockfinalmois=this.stockfinalmois(connection);
        double stock_final=0;
        double prix_final=0;
        for(int annee = debutannee; annee<= Utils.getCurrentYear(); annee++){
                boolean found = false;
                for(Stockfinalmois stockfinalmois1 : stockfinalmois){
                    if(stockfinalmois1.getAnnee()==annee && stockfinalmois1.getMois()==mois){
                        found=true;
                        stock_final=stockfinalmois1.getStockfinal();
                        prix_final=stockfinalmois1.getPrixfinal();
                        break;
                }
                if (!found) {
                    Stockfinalmois stat = new Stockfinalmois();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setStockfinal(0);
                    stat.setPrixfinal(0);
                    dataList.add(stat);
                }else{
                    Stockfinalmois stat = new Stockfinalmois();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setNom_mois(Utils.getNomMois(mois));
                    stat.setStockfinal(stock_final);
                    stat.setPrixfinal(prix_final);
                    dataList.add(stat);
                }
            }
        }
        return dataList.toArray(new Stockfinalmois[0]);
    }

    public Stockinitialmois[] stockinitialunparmois(int mois,Connection connection) throws  Exception{
        connection = this.getConnection(connection);
        List<Stockinitialmois> dataList = new ArrayList<>();
        Stockinitialmois[] stockinitialmois=this.stockinitialmois(connection);
        double stock_initial=0;
        double prix_final=0;
        for(int annee = debutannee; annee<= Utils.getCurrentYear(); annee++){
                boolean found = false;
                for(Stockinitialmois stockinitialmois1 : stockinitialmois){
                    if(stockinitialmois1.getAnnee()==annee && stockinitialmois1.getMois()==mois){
                        found=true;
                        stock_initial=stockinitialmois1.getStockinitial();
                        prix_final=stockinitialmois1.getPrixinitial();
                        break;
                    }
                     if (!found) {
                         Stockinitialmois stat = new Stockinitialmois();
                         stat.setAnnee(annee);
                         stat.setMois(mois);
                         stat.setNom_mois(Utils.getNomMois(mois));
                         stat.setStockinitial(0);
                         dataList.add(stat);
                     }else{
                         Stockinitialmois stat = new Stockinitialmois();
                         stat.setAnnee(annee);
                         stat.setMois(mois);
                         stat.setNom_mois(Utils.getNomMois(mois));
                         stat.setStockinitial(stock_initial);
                         stat.setPrixinitial(prix_final);
                         dataList.add(stat);
                     }
            }
        }
        return dataList.toArray(new Stockinitialmois[0]);
    }

    public Rotationstock[] rotationstocks(Connection connection) throws Exception {
        List<Rotationstock> dataList = new ArrayList<>();
        for (int annee = debutannee; annee <= Utils.getCurrentYear(); annee++) {
            for (int mois = 1; mois <= 12; mois++) {
                Stockinitialmois stockInitialMois = stockinitialunmois(annee, mois, connection);
                Stockfinalmois stockFinalMois = stockfinalunmois(annee, mois, connection);
                Quantitevendumois quantite = quantitevendu(annee, mois, connection);

                double rotationstockquantite = 0.0;
                double rotationstockvaleur = 0.0;

                if (stockInitialMois != null && stockFinalMois != null && quantite != null) {
                    double quantiteMoyenStock = (stockInitialMois.getStockinitial() + stockFinalMois.getStockfinal()) / 2;
                    double valeurMoyenStock = (stockInitialMois.getPrixinitial() + stockFinalMois.getPrixfinal()) / 2;
                    double venteQuantite = quantite.getQuantitevendue();
                    double totalVente = quantite.getPrixtotalvendus();

                    rotationstockquantite = venteQuantite / quantiteMoyenStock;
                    rotationstockvaleur = totalVente / valeurMoyenStock;
                }

                System.out.println("rotationstockquantite" + rotationstockquantite);
                System.out.println("rotationstockvaleur" + rotationstockvaleur);

                Rotationstock rotationstock = new Rotationstock(annee, mois, Utils.getNomMois(mois), rotationstockquantite, rotationstockvaleur);
                dataList.add(rotationstock);
            }
        }
        return dataList.toArray(new Rotationstock[0]);
    }


}
