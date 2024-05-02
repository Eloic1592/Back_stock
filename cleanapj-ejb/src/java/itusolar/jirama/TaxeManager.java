package itusolar.jirama;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;
import itusolar.prepare.schedule.Schedule;
import itusolar.prepare.schedule.ScheduleDetails;

import java.sql.Connection;
import java.sql.SQLException;

public class TaxeManager extends HServiceManager implements HTaxeManager{

    @Override
    public Taxe getById(Taxe taxe) throws Exception {
        Connection connection = this.getConnection(null);
        taxe = this.getById(taxe,connection);
        connection.close();
        return taxe;
    }

    @Override
    public Taxe getById(Taxe taxe, Connection connection) throws Exception {
        String where = " and id = "+taxe.getId();
        Taxe[] results = this.searchTaxes(where, connection);
        if (results.length > 0) {
            return results[0];
        }
        return null;
    }

    @Override
    public TaxeForm formContent(Taxe taxe) throws Exception {
        Connection connection = this.getConnection(null);
        TaxeForm form = this.formContent(taxe, connection);
        connection.close();
        return form;
    }

    @Override
    public TaxeForm formContent(Taxe taxe, Connection connection) throws Exception {
        connection = this.getConnection(null);
        taxe = this.getById(taxe, connection);
        return new TaxeForm(taxe);
    }

    @Override
    public void addTaxe(Taxe taxe, Connection connection) throws Exception {
        if (taxe.getId() > 0) {
            taxe.updateToTable(connection);
            return;
        }
        taxe.construirePK(connection);
        CGenUtil.save(taxe,connection);
    }

    @Override
    public void addTaxe(Taxe taxe) throws Exception {
        Connection connection = this.getConnection(null);
        this.addTaxe(taxe, connection);
        connection.close();
    }

    @Override
    public Taxe[] getAllTaxes(Connection connection) throws Exception {
        return this.searchTaxes("",connection);
    }

    public Taxe[] searchTaxes(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] columns = {};
        String[] values = {};
        Object[] data = CGenUtil.rechercher(new Taxe(),columns,values,connection,where);
        return this.castAll(data);
    }

    @Override
    public Taxe[] castAll(Object[] args) {
        Taxe[] taxes = new Taxe[args.length];
        for (int i = 0; i < args.length; i++) {
            taxes[i] = (Taxe) args[i];
        }
        return taxes;
    }

    @Override
    public TaxeForm formContent(TaxeRequest params, Connection connection) throws Exception {
        return this.formContent(params.getTaxe(),connection);
    }
}
