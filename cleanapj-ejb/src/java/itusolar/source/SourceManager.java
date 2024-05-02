package itusolar.source;

import bean.CGenUtil;
import itusolar.historique.InverterFormRequirements;
import itusolar.historique.type.Type;
import itusolar.historique.type.TypeManagerSignature;
import itusolar.lieu.Section;
import itusolar.lieu.SectionManagerSignature;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class SourceManager extends HServiceManager implements SourceManagerSignature {
    SectionManagerSignature sectionManager;
    TypeManagerSignature typeManager;

    public SourceManager(SectionManagerSignature sectionManager,TypeManagerSignature typeManager) {
        this.sectionManager = sectionManager;
        this.typeManager = typeManager;
    }

    @Override
    public void save(Source source, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        source.construirePK(connection);
        CGenUtil.save(source,connection);
    }

    @Override
    public SourceForm formContent(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Section[] sections = this.sectionManager.getAll(connection);
        Type[] types = this.typeManager.getAll(connection);
        return new SourceForm(sections,types);
    }

    @Override
    public VSource[] getAll(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] columns = {};
        String[] values = {};
        Object[] datas = CGenUtil.rechercher(new VSource(),columns,values,connection,"");
        return this.cast(datas);
    }

    @Override
    public InverterFormRequirements inverterRequirements(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        VSource[] sources = this.getAll(connection);
        return new InverterFormRequirements(sources);
    }

    @Override
    public VSource[] cast(Object[] data) {
        VSource[] result = new VSource[data.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = (VSource) data[i];
        }
        return result;
    }

    @Override
    public PageSourceResult pageSourceContent(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        PageSourceResult result = new PageSourceResult();
        result.setSources(this.getAll(connection));
        return result;
    }
}
