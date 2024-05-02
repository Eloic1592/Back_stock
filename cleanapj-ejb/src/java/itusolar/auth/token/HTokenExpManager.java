package itusolar.auth.token;

import java.sql.Connection;

public interface HTokenExpManager {
    public TokenExpire getTokenExpire(Connection connection) throws Exception;

    public TokenExpire getDefaultTokenExpire(Connection connection) throws Exception;
}
