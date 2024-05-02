package itusolar.historique.type;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;
import org.apache.poi.ss.formula.functions.T;

import java.sql.Connection;

public class TypeManager extends HServiceManager implements TypeManagerSignature {
    @Override
    public Type[] getAll(Connection connection) throws Exception {
        String[] intervals = {}, contents = {};
        return this.cast(CGenUtil.rechercher(new Type(),intervals,contents,connection,""));
    }

    public Type[] cast(Object[] datas) {
        Type[] results = new Type[datas.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = (Type)(datas[i]);
        }
        return results;
    }
}
