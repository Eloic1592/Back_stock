package itusolar.source;


import itusolar.historique.InverterFormRequirements;

import java.sql.Connection;

public interface SourceManagerSignature {
    public void save(Source source,Connection connection) throws Exception;
    public SourceForm formContent(Connection connection) throws Exception;
    public VSource[] getAll(Connection connection) throws Exception;
    public InverterFormRequirements inverterRequirements(Connection connection) throws Exception;

    public VSource[] cast(Object[] data);

    PageSourceResult pageSourceContent(Connection connection) throws Exception;
}
