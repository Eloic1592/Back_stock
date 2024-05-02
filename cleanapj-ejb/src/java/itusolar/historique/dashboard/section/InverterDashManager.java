package itusolar.historique.dashboard.section;

import bean.CGenUtil;
import itusolar.lieu.Section;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class InverterDashManager extends HServiceManager implements InverterDashManagerSignature {

    @Override
    public InverterDash dashContent(Section section, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String where = "";
        if (section.getId() >= 0) {
            where = " and sectionid=" + section.getId();
        }
        String[] intervals = {}, values = {};
        Object[] datas = CGenUtil.rechercher(new InverterState(),intervals,values, connection, where);
        return new InverterDash(this.cast(datas));
    }

    public InverterState[] cast(Object[] values) {
        InverterState[] results = new InverterState[values.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = (InverterState) values[i];
        }
        return results;
    }
}
