package itusolar.jirama;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class TarifManager extends HServiceManager implements HTarifManager{
    TaxeManager taxeManager = new TaxeManager();

    @Override
    public void create(Tarif tarif) throws Exception {
        Connection connection = this.getConnection(null);
        this.create(tarif, connection);
        connection.close();
    }

    @Override
    public void create(Tarif tarif, Connection connection) throws Exception {
        tarif.updateToTable(connection);
    }

    @Override
    public TarifForm formContent(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return new TarifForm(this.getTarif(connection));
    }

    @Override
    public Tarif getTarif(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] colInt = {};
        String[] valInt = {};
        Object[] data = CGenUtil.rechercher(new Tarif(),colInt,valInt,connection,"");
        return data.length > 0 ? ((Tarif) data[0]) : new Tarif();
    }

    @Override
    public Tarif getTarifComplete(Connection connection) throws Exception {
        Tarif response = this.getTarif(connection);
        response.setTaxes(this.taxeManager.getAllTaxes(connection));
        return response;
    }

    @Override
    public PageTarifResponse pageTarifContent(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        PageTarifResponse response = new PageTarifResponse();
        response.setTarif(this.getTarif(connection));
        response.setTaxes(this.taxeManager.getAllTaxes(connection));
        return response;
    }
}
