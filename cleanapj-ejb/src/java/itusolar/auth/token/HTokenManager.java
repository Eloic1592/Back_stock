package itusolar.auth.token;

import java.sql.Connection;

public interface HTokenManager {
    public void create(Token token, Connection connection) throws Exception;
    public Token hasToken(Token user, Connection connection) throws Exception;

    public void refresh(Token token, Connection connection) throws Exception;

    public void deleteExp(Connection connection) throws Exception;

}
