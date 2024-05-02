package itusolar.eltricSensor;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class ElectricAccessor extends HServiceManager implements ElectricAccessorSignature {



    @Override
    public ElectricState[] getAll(ElectricSensor sensor,Connection connection) throws Exception {
        return this.search(sensor,"", connection);
    }
    
    public ElectricState[] search(ElectricSensor sensor,String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] intervals = {}, values = {};
        Object[] datas = CGenUtil.rechercher(sensor, intervals, values, connection, where);
        return this.cast(datas);
    }

    public ElectricState[] cast(Object[] values) {
        ElectricState[] results = new ElectricState[values.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = (ElectricState) values[i];
        }
        return results;
    }
}
