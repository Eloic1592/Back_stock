package itusolar.source;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class SectionCapacityManager extends HServiceManager implements SectionCapacityManagerSignature {
    @Override
    public SectionCapacity[] getAll(Connection connection) throws Exception {
        return  this.search("", connection);
    }

    public SectionCapacity[] search(String sql,Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] columns = {};
        String[] values = {};
        Object[] datas = CGenUtil.rechercher(new SectionCapacity(),columns,values,connection,sql);
        return this.cast(datas);
    }

    @Override
    public SectionCapacity getBySection(int sectionid, Connection connection) throws Exception {
        SectionCapacity[] result = this.search(" and sectionid="+sectionid, connection);
        if (result.length > 0)
            return result[0];
        throw new Exception("Could not find section");
    }

    private SectionCapacity[] cast(Object[] datas) {
        SectionCapacity[] result = new SectionCapacity[datas.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = (SectionCapacity) datas[i];
        }
        return result;
    }

}
