package itusolar.lieu;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class SectionManager extends HServiceManager implements SectionManagerSignature {
    @Override
    public Section[] getAll(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return this.cast(CGenUtil.rechercher(new Section(),new String[0],new String[0],connection,""));
    }

    public Section[] cast(Object[] datas) {
        Section[] sections = new Section[datas.length];
        for (int i = 0; i < datas.length; i++) {
            sections[i] = (Section) datas[i];
        }
        return sections;
    }
}
