package itusolar.simulation.material;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class MaterialManager extends HServiceManager implements MaterialManagerSignature {

    @Override
    public CompoForm[] searchCompo(String where, Connection connection) throws Exception {
        String[] intervals = {}, values = {};
        Object[] datas = CGenUtil.rechercher(new CompoForm(),intervals,values,connection,where);
        return this.castCompo(datas,connection);
    }

    public CompoForm[] castCompo(Object[] datas, Connection connection) throws Exception {
        CompoForm[] compoForms = new CompoForm[datas.length];
        for (int i = 0; i < datas.length; i++) {
            compoForms[i] = (CompoForm) datas[i];
            compoForms[i].setMaterial(this.getById(compoForms[i].getMembreid(),connection));
        }
        return compoForms;
    }

    @Override
    public CompoForm[] searchIgnore(Material material, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String where = " and appareilid = "+material.getId();
        return this.searchCompo(where, connection);
    }

    @Override
    public Material[] search(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Material[] result = this.cast(CGenUtil.rechercher(new itusolar.simulation.material.Material(), new String[0],new String[0],connection, where));
        for (Material m : result)
            this.load(m,connection);
        return result;
    }

    @Override
    public MaterialForm formContent(Material material,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        CompoForm[] materials = this.searchIgnore(material,connection);
        Material mat = this.getById(material.getId(),connection);
        return new MaterialForm(mat,materials);
    }

    @Override
    public MaterialList listContent(Connection connection) throws Exception {
        MaterialList list = new MaterialList();
        list.setMaterials(this.getAll(connection));
        return list;
    }

    @Override
    public Material[] getAll(Connection connection) throws Exception {
        return this.search("", connection);
    }

    @Override
    public Material getById(int id, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Material[] result = this.cast(CGenUtil.rechercher(new Material(), new String[0],new String[0],connection, " and id="+id));
        if (result.length == 0)
            throw new Exception("No material found for id " + id);
        Material response = result[0];
        this.load(response,connection);
        return response;
    }

    @Override
    public void save(Material material, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (material.getId() > 0) {
            material.updateToTable(connection);
        } else {
            material.construirePK(connection);
            CGenUtil.save(material, connection);
        }
        for (MaterialComposition composition : material.getMaterialCompositions()) {
            this.save(composition, connection);
        }
    }

    public void save(MaterialComposition composition, Connection connection) throws Exception {
        composition.setId(composition.getMcid());
        if (composition.getMcid() >= 0 && composition.isCheck()) {
            composition.updateToTable(connection);
        } else if (composition.getMcid() >= 0 && !composition.isCheck()) {
            composition.deleteToTable(connection);
        } else if (composition.isCheck()) {
            composition.construirePK(connection);
            CGenUtil.save(composition, connection);
        }
    }

    public void load(Material material,Connection connection) throws Exception {
        MaterialComposition composition = new MaterialComposition();
        String[] colInterval = {}, valInterval = {};
        String where = " and appareilid = "+material.getId();
        Object[] results = CGenUtil.rechercher(composition,colInterval,valInterval,connection, where);
        this.cast(material, results,connection);
    }

    public void cast(Material material, Object[] datas,Connection connection) throws Exception {
        MaterialComposition[] response = new MaterialComposition[datas.length];
        for (int i = 0; i < response.length; i++) {
            response[i] = (MaterialComposition) datas[i];
            response[i].setMaterial(this.getById(response[i].getMembreid(),connection));
            response[i].setCheck(true);
        }
        material.setMaterialCompositions(response);
    }

    public Material[] cast(Object[] datas) {
        Material[] result = new Material[datas.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (Material) datas[i];
        }
        return result;
    }
}
