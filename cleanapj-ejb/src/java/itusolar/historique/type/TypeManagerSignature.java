package itusolar.historique.type;

import java.sql.Connection;

public interface TypeManagerSignature {
    public Type[] getAll(Connection connection) throws Exception;
}
