package itusolar.historique.dashboard;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class SectionStateEvaluator extends HServiceManager implements SectionStateSignature {
    @Override
    public SectionStateResult[] evalState(SectionStateParams params, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] intervals = {}, values = {};
        Object[] datas = CGenUtil.rechercher(new SectionState(), intervals, values, connection, "");
        return this.cast(datas);
    }

    public SectionState[] cast(Object[] datas) {
        SectionState[] results = new SectionState[datas.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = (SectionState) datas[i];
        }
        return results;
    }
}
