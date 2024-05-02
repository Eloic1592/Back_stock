package inventorymanagement.materiel.categoriemateriel;

import bean.CGenUtil;

import inventorymanagement.depot.Depot;
import inventorymanagement.depot.DepotParams;
import inventorymanagement.materiel.typemateriel.TypematerielPageList;
import itusolar.controller.HPost;
import itusolar.prepare.HServiceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class CategoriematerielManager extends HServiceManager implements  CategoriematerielManagerSignature {
    CategoriematerielManagerSignature categoriematerielManagerSignature;

    public CategoriematerielManager() {
        this.categoriematerielManagerSignature = categoriematerielManagerSignature;
    }

    @Override
    public void create(Categoriemateriel categoriemateriel, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (categoriemateriel.getIdcategoriemateriel()!=null) {
            categoriemateriel.updateToTable(connection);
            return;
        }
        categoriemateriel.construirePK(connection);
        CGenUtil.save(categoriemateriel, connection);
    }


    @Override
    public Categoriemateriel[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Categoriemateriel(), new String[0], new String[0], connection, "");
        return cast(data);
    }

    public Categoriemateriel[] cast(Object[] datas) {
        Categoriemateriel[] categoriemateriels = new Categoriemateriel[datas.length];
        for (int i = 0; i < datas.length; i++) {
            categoriemateriels[i] = (Categoriemateriel) datas[i];
        }
        return categoriemateriels;
    }
    @Override
    public CategoriematerielPageList contentlist(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        CategoriematerielPageList categoriematerielPageList = new CategoriematerielPageList();
        categoriematerielPageList.setCategoriemateriels(this.getall(connection));
        return categoriematerielPageList;
    }

    public Categoriemateriel getOne(String idcategoriemateriel, Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Categoriemateriel[] data=(Categoriemateriel[])CGenUtil.rechercher(new Categoriemateriel(), new String[0], new String[0], connection, "and idcategoriemateriel='"+idcategoriemateriel+"'");
        return data[0];
    }



}
