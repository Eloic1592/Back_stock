package itusolar.lieu;

import java.sql.Connection;

public interface SectionManagerSignature {
    public Section[] getAll(Connection connection) throws Exception;
}
