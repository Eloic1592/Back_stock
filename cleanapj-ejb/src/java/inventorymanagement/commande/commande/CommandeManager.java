package inventorymanagement.commande.commande;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;
import inventorymanagement.article.Listearticle;
import inventorymanagement.client.ClientManager;
import inventorymanagement.commande.detailcommande.Detailcommande;
import inventorymanagement.commande.detailcommande.Detailcommandeview;
import inventorymanagement.commande.reception.Reception;
import inventorymanagement.commande.reception.Vuereception;
import inventorymanagement.dashboard.dashboard.Etatdetailstockannee;
import inventorymanagement.dashboard.dashboard.Etatstockannee;
import inventorymanagement.materiel.typemateriel.Typemateriel;
import inventorymanagement.mouvement.utils.Utils;
import itusolar.prepare.HServiceManager;

import javax.rmi.CORBA.Util;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandeManager extends HServiceManager implements CommandeManagerSignature {

    CommandeManagerSignature commandeManagerSignature;
    ClientManager clientManager= new ClientManager();
    ArticleManager articleManager= new ArticleManager();
    static int debutannee=2011;

    public CommandeManager() {
        this.commandeManagerSignature = commandeManagerSignature;
    }


//    Commande
    @Override
    public void createcommande(Commande commande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (commande.getIdcommande()!=null) {
            commande.updateToTable(connection);
            return;
        }else {
            commande.construirePK(connection);
            Commande commande1 = (Commande) CGenUtil.save(commande, connection);
            System.out.println("Taille du tableau:" +commande1.getDetailcommandes().length);
            if (commande1.getDetailcommandes().length!=0){
                Arrays.stream(commande1.getDetailcommandes()).forEach(item -> item.setIdcommande(commande1.getIdcommande()));
                this.createdetails(commande1.getDetailcommandes(), connection);
            }

        }
    }
    public void createdetails(Detailcommande[] detailcommandes, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        for(Detailcommande detailcommande1 :detailcommandes) {
            if (detailcommande1.getIddetailcommande() != null) {
                detailcommande1.updateToTable(connection);
            }
            detailcommande1.construirePK(connection);
            CGenUtil.save(detailcommande1, connection);
        }
    }

    @Override
    public VueCommande[] getallcommande(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        VueCommande[] data =(VueCommande[]) CGenUtil.rechercher(new VueCommande(), new String[0], new String[0], connection, "");
        return data;
    }

    @Override
    public Detailcommandeview[] getalldetailcommande(String idcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Detailcommandeview[] data =(Detailcommandeview[]) CGenUtil.rechercher(new Detailcommandeview(), new String[0], new String[0], connection, "and idcommande='"+idcommande+"'");
        return data;
    }

    //Details de la commande
    public void createsingledetail(Detailcommande detailcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (detailcommande.getIddetailcommande() != null) {
            detailcommande.updateToTable(connection);
        }else {
            detailcommande.construirePK(connection);
            CGenUtil.save(detailcommande, connection);
        }
    }



    @Override
    public CommandePageList commandcontentpage(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setVueCommandes(this.getallcommande(connection));
        commandePageList.setClients(clientManager.getall(connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    @Override
    public CommandePageList contentform(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setClients(clientManager.getall(connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    @Override
    public Commande getcommande(String idcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Commande[] data =(Commande[]) CGenUtil.rechercher(new Commande(), new String[0], new String[0], connection, "and idcommande='"+idcommande+"'");
        return data[0];
    }

    @Override
    public Detailcommande getdetailcommande(String iddetailcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Detailcommande[] data =(Detailcommande[]) CGenUtil.rechercher(new Detailcommande(), new String[0], new String[0], connection, "and iddetailcommande='"+iddetailcommande+"'");
        return data[0];
    }

    @Override
    public Vuereception[] getallreception(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Vuereception[] data =(Vuereception[]) CGenUtil.rechercher(new Vuereception(), new String[0], new String[0], connection, "");
        return data;
    }

    public VueCommande getvuecommande(String idcommande, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        VueCommande[] data =(VueCommande[]) CGenUtil.rechercher(new VueCommande(), new String[0], new String[0], connection, "and idcommande='"+idcommande+"'");
        return data[0];
    }


    public CommandePageList detailcontentpage(Connection connection, String idcommande) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setDetailcommandeviews(this.getalldetailcommande(idcommande,connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    public CommandePageList editform(String idcommande,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setCommande(getcommande(idcommande,connection));
        commandePageList.setClients(clientManager.getall(connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }

    public CommandePageList editformdetails(String iddetailcommande,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setDetailcommande(getdetailcommande(iddetailcommande,connection));
        commandePageList.setArticles(articleManager.getall(connection));
        return commandePageList;
    }
    public void createreception(Reception reception, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (reception.getIdreception()!=null) {
            reception.updateToTable(connection);
            return;
        }else {
            reception.construirePK(connection);
            CGenUtil.save(reception, connection);

        }
    }

    public CommandePageList reception(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setVuereceptions(this.getallreception(connection));
        return commandePageList;
    }
    public Reception getreception(String idreception,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Reception[] data =(Reception[]) CGenUtil.rechercher(new Reception(), new String[0], new String[0], connection, "and idreception='"+idreception+"'");
        return data[0];
    }

    public Totalcommandeannee[] gettotalcommandeannee(int annee,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Totalcommandeannee[] data =(Totalcommandeannee[]) CGenUtil.rechercher(new Totalcommandeannee(), new String[0], new String[0], connection, "and annee="+annee+"");
        return data;
    }

    public Totalcommandearticle[] gettotalcommandearticle(int annee,int mois,Connection connection) throws Exception {
        annee=(annee==0)?Utils.getCurrentYear():annee;
        connection = this.getConnection(connection);
        Totalcommandearticle[] data =(Totalcommandearticle[]) CGenUtil.rechercher(new Totalcommandearticle(), new String[0], new String[0], connection, "and annee="+annee+" and mois="+mois+"");
        return data;
    }

    public Totalcommandeannee[] etatcommandeannees(int annee,Connection connection) throws Exception {
        List<Totalcommandeannee> dataList = new ArrayList<>();
        Totalcommandeannee[] totalcommandeannees = this.gettotalcommandeannee(annee,connection);
        for (int mois = 1; mois <= 12; mois++) {
            boolean found = false;
            for(Totalcommandeannee totalcommandeannee: totalcommandeannees){
                if(totalcommandeannee.getAnnee()==annee && totalcommandeannee.getMois()==mois){
                    found=true;
                    dataList.add(totalcommandeannee);
                    break;
                }

            }
            if (!found) {
                Totalcommandeannee stat = new Totalcommandeannee();
                stat.setAnnee(annee);
                stat.setMois(mois);
                stat.setMoisnom(Utils.getNomMois(mois));
                stat.setTotalcommandes(0);
                dataList.add(stat);
            }

        }
        return dataList.toArray(new Totalcommandeannee[0]);
    }

    public Totalcommandearticle[] etatcommandearticleannee(int annee,int mois,Connection connection) throws Exception {
        List<Totalcommandearticle> dataList = new ArrayList<>();
        Totalcommandearticle[] totalcommandearticles = this.gettotalcommandearticle(annee, mois, connection);
        Listearticle [] listearticles=articleManager.getlistarticle(connection);
        for (Listearticle listearticle : listearticles) {
            boolean found = false;
            for (Totalcommandearticle totalcommandearticle : totalcommandearticles) {
                if (totalcommandearticle.getAnnee() == annee && totalcommandearticle.getMois() == mois && totalcommandearticle.getIdarticle().equals(listearticle.getIdarticle())) {
                    found = true;
                    dataList.add(totalcommandearticle);
                    System.out.println(totalcommandearticle.getQuantitetotale());
                    break;
                }
            }
                if (!found) {
                    Totalcommandearticle stat = new Totalcommandearticle();
                    stat.setAnnee(annee);
                    stat.setMois(mois);
                    stat.setMoisnom(Utils.getNomMois(mois));
                    stat.setIdarticle(listearticle.getIdarticle());
                    stat.setMarque(listearticle.getMarque());
                    stat.setModele(listearticle.getModele());
                    stat.setTypemateriel(listearticle.getTypemateriel());
                    stat.setCodearticle(listearticle.getCodearticle());
                    stat.setVal(listearticle.getVal());
                    stat.setQuantitetotale(0);
                    dataList.add(stat);
                }

        }
            return dataList.toArray(new Totalcommandearticle[0]);
    }

//    Total commande par annee
//    Commande total groupee par article par mois par annee
    public CommandePageList totalcommandeannee(int annee,int mois,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setTotalcommandeannees(this.etatcommandeannees(annee,connection));
        commandePageList.setTotalcommandearticles(this.etatcommandearticleannee(annee,mois,connection));
        return commandePageList;
    }

//    PDF des commandes
    public CommandePageList pdfcommande(String idcommande,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CommandePageList commandePageList =new CommandePageList();
        commandePageList.setVueCommande(this.getvuecommande(idcommande,connection));
        commandePageList.setDetailcommandeviews(this.getalldetailcommande(idcommande,connection));
        return commandePageList;
    }





}
