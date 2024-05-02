package inventorymanagement.materiel.typemateriel;

import affichage.Liste;
import bean.CGenUtil;
import inventorymanagement.depot.Depot;
import inventorymanagement.depot.DepotPageList;
import inventorymanagement.materiel.categoriemateriel.CategoriematerielManager;
import inventorymanagement.materiel.materiel.Listemateriel;
import inventorymanagement.mouvement.naturemouvement.Naturemouvement;
import inventorymanagement.mouvement.naturemouvement.NaturemouvementManager;
import inventorymanagement.mouvement.naturemouvement.Statnaturemouvement;
import inventorymanagement.mouvement.utils.Utils;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TypematerielManager extends HServiceManager implements  TypematerielManagerSignature {
    TypematerielManagerSignature typematerielManagerSignature;
    CategoriematerielManager categoriematerielManager=new CategoriematerielManager();
    NaturemouvementManager naturemouvementManager=new NaturemouvementManager();

    public TypematerielManager() {
        this.typematerielManagerSignature = typematerielManagerSignature;
    }

    @Override
    public void create(Typemateriel typemateriel, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (typemateriel.getIdtypemateriel()!=null) {
            typemateriel.updateToTable(connection);
            return;
        }
        typemateriel.construirePK(connection);
        CGenUtil.save(typemateriel, connection);
    }

    @Override
    public Typemateriel[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Typemateriel(), new String[0], new String[0], connection, "");
        return cast(data);
    }

    public Typemateriel[] cast(Object[] datas) {
        Typemateriel[] typemateriels = new Typemateriel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            typemateriels[i] = (Typemateriel) datas[i];
        }
        return typemateriels;
    }

    public Listetypemateriel[] getlistetypemateriels(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Listetypemateriel(), new String[0], new String[0], connection, "");
        return casttype(data);
    }

    public Listetypemateriel[] casttype(Object[] datas) {
        Listetypemateriel[] listetypemateriels = new Listetypemateriel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            listetypemateriels[i] = (Listetypemateriel) datas[i];
        }
        return listetypemateriels;
    }



    @Override
    public TypematerielPageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        TypematerielPageList typematerielPageList = new TypematerielPageList();
        typematerielPageList.setTypemateriels(this.getlistetypemateriels(connection));
        typematerielPageList.setCategoriemateriels(categoriematerielManager.getall(connection));
        return typematerielPageList;
    }

    public Typemateriel getOne(String idtypemateriel, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Typemateriel[] data=(Typemateriel[])CGenUtil.rechercher(new Typemateriel(), new String[0], new String[0], connection, "and idtypemateriel='"+idtypemateriel+"'");
        return data[0];
    }

    public TypematerielPageList getOnePage(String idtypemateriel,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        TypematerielPageList typematerielPageList = new TypematerielPageList();
        typematerielPageList.setTypemateriel(this.getOne(idtypemateriel,connection));
        typematerielPageList.setCategoriemateriels(categoriematerielManager.getall(connection));
        return typematerielPageList;
    }

    public Stat_typemateriel[] getstat(int annee,int mois,String idnaturemouvement, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Stat_typemateriel[] data=(Stat_typemateriel[])CGenUtil.rechercher(new Stat_typemateriel(), new String[0], new String[0], connection, "and annee="+annee+" and MOIS_NUMERO="+mois+" and idnaturemouvement='"+idnaturemouvement+"'");
        return data;
    }


    public Stat_typemateriel[] getstattypemateriel(String idnaturemouvement, int mois, int annee, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Naturemouvement naturemouvement = naturemouvementManager.getOne(idnaturemouvement,connection);
        Typemateriel[] listtypemateriels = this.getall(connection);
        Stat_typemateriel[] data = this.getstat(annee, mois, idnaturemouvement, connection);
        List<Stat_typemateriel> dataList = new ArrayList<>();
        double depense=0;
        double gain=0;

        for (Typemateriel typemateriel : listtypemateriels) {
                boolean found = false;
                for (Stat_typemateriel stat : data) {
                    if (stat.getAnnee() == annee && stat.getMois_numero() == mois && stat.getIdnaturemouvement().equals(idnaturemouvement)
                            && stat.getIdtypemateriel().equals(typemateriel.getIdtypemateriel())) {
                        found = true;
                        depense=stat.getDepense();
                        gain=stat.getGain();
                        break;
                    }
                }
                if (!found) {
                    Stat_typemateriel stat = new Stat_typemateriel();
                    stat.setAnnee(annee);
                    stat.setMois_numero(mois);
                    stat.setMois(Utils.getNomMois(mois)); // Remplacez getMonthName par votre méthode pour obtenir le nom du mois
                    stat.setIdnaturemouvement(naturemouvement.getIdnaturemouvement());
                    stat.setNaturemouvement(naturemouvement.getNaturemouvement());
                    stat.setIdtypemateriel(typemateriel.getIdtypemateriel());
                    stat.setTypemateriel(typemateriel.getTypemateriel());
                    stat.setDepense(0);
                    stat.setGain(0);
                    dataList.add(stat);
                }else{
                    Stat_typemateriel stat = new Stat_typemateriel();
                    stat.setAnnee(annee);
                    stat.setMois_numero(mois);
                    stat.setMois(Utils.getNomMois(mois)); // Remplacez getMonthName par votre méthode pour obtenir le nom du mois
                    stat.setIdnaturemouvement(naturemouvement.getIdnaturemouvement());
                    stat.setNaturemouvement(naturemouvement.getNaturemouvement());
                    stat.setIdtypemateriel(typemateriel.getIdtypemateriel());
                    stat.setTypemateriel(typemateriel.getTypemateriel());
                    stat.setDepense(depense);
                    stat.setGain(gain);
                    dataList.add(stat);
                    // Si les données existent, les ajouter telles quelles
                }
            }


        return dataList.toArray(new Stat_typemateriel[0]);
    }


    public TypematerielPageList getstatlist(String idnaturemouvement,int mois,int annee,Connection connection) throws Exception {
        connection=this.getConnection(connection);
        TypematerielPageList typematerielPageList = new TypematerielPageList();
        typematerielPageList.setStat_typemateriels(this.getstattypemateriel(idnaturemouvement,mois,annee,connection));
        typematerielPageList.setListetypemateriels(this.getall(connection));
        return typematerielPageList;
    }
}
