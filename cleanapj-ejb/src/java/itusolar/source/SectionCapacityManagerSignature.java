package itusolar.source;

import java.sql.Connection;

public interface SectionCapacityManagerSignature {
    public SectionCapacity[] getAll(Connection connection) throws Exception;

    public SectionCapacity getBySection(int sectionid, Connection connection) throws Exception;
}
