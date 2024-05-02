package inventorymanagement.depot;

import bean.CGenUtil;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysiqueview;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class DepotManager extends HServiceManager implements DepotManagerSignature {

    DepotManagerSignature depotManagerSignature;
    public DepotManager() {
        this.depotManagerSignature = depotManagerSignature;
    }

    @Override
    public void create(Depot depot, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if(depot.getIddepot()!=null) {
            depot.updateToTable(connection);
        }else {
            depot.construirePK(connection);
            CGenUtil.save(depot, connection);
        }
    }

    @Override
    public Depot[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Depot(), new String[0], new String[0], connection, "");
        return cast(data);
    }

    public Depot[] cast(Object[] datas) {
        Depot[] depots = new Depot[datas.length];
        for (int i = 0; i < datas.length; i++) {
            depots[i] = (Depot) datas[i];
        }
        return depots;
    }

    @Override
    public DepotPageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        DepotPageList pagelist = new DepotPageList();
        pagelist.setDepotlist(this.getall(connection));
        return pagelist;
    }

    public Stockarticledepot[] getstockadepot(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Stockarticledepot[] data = (Stockarticledepot[])CGenUtil.rechercher(new Stockarticledepot(), new String[0], new String[0], connection, "");
        return data;
    }

    public Stocktypematerieldepot[] getstocktpdepot(String iddepot,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Stocktypematerieldepot[] data = (Stocktypematerieldepot[])CGenUtil.rechercher(new Stocktypematerieldepot(), new String[0], new String[0], connection, "and iddepot='"+iddepot+"'");
        return data;
    }
    public Depot getOne(String iddepot, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Depot[] data=(Depot[])CGenUtil.rechercher(new Depot(), new String[0], new String[0], connection, "and iddepot='"+iddepot+"'");
        return data[0];
    }



}
