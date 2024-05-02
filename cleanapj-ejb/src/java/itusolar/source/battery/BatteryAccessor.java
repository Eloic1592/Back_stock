package itusolar.source.battery;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class BatteryAccessor extends HServiceManager implements BatteryAccessorSignature {

    @Override
    public void save(Battery battery, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (battery.getId() > 0) {
            battery.updateToTable(connection);
            return;
        }
        battery.construirePK(connection);
        CGenUtil.save(battery, connection);
    }

    @Override
    public BatteryForm formContent(Battery battery, Connection connection) throws Exception {
        String where = " and id = "+ battery.getId();
        Battery[] batteries = this.search(where, connection);
        battery = batteries.length > 0 ? batteries[0] : null;
        return new BatteryForm(battery);
    }

    @Override
    public Battery[] getAll(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return this.search("", connection);
    }

    @Override
    public Battery[] search(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] intervals = {}, values = {};
        Object[] datas = CGenUtil.rechercher(new Battery(), intervals, values, connection, where);
        return this.cast(datas);
    }

    public Battery[] cast(Object[] datas) {
        Battery[] battery = new Battery[datas.length];
        for (int i = 0; i < battery.length; i++) {
            battery[i] = (Battery) datas[i];
        }
        return battery;
    }

    @Override
    public BatteryList listContent(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return new BatteryList(this.getAll(connection));
    }
}
