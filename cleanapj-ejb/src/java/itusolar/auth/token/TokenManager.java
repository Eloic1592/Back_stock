package itusolar.auth.token;

import bean.CGenUtil;
import itusolar.auth.PermissionDeniedException;
import itusolar.auth.token.HTokenExpManager;
import itusolar.auth.token.HTokenManager;
import itusolar.auth.token.Token;
import itusolar.auth.token.TokenExpManager;
import itusolar.auth.token.TokenExpire;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class TokenManager extends HServiceManager implements HTokenManager {
    HTokenExpManager tokenExpManager = new TokenExpManager();

    @Override
    public void create(itusolar.auth.token.Token token, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        token.construirePK(connection);
        CGenUtil.save(token,connection);
    }

    @Override
    public itusolar.auth.token.Token hasToken(itusolar.auth.token.Token user, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        this.deleteExp(connection);
        String where = " and token = '"+user.getToken()+"'";
        Object[] results = CGenUtil.rechercher(new Token(), new String[0], new String[0], connection, where);
        if (results.length == 0)
            throw new PermissionDeniedException();
        return (itusolar.auth.token.Token) results[0];
    }

    @Override
    public void refresh(itusolar.auth.token.Token token, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        token.setDateins(token.getDatefin());
        TokenExpire tokenExpire = this.tokenExpManager.getDefaultTokenExpire(connection);
        tokenExpire.configToken(token);
        tokenExpire.updateToTable(connection);
    }

    @Override
    public void deleteExp(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        itusolar.auth.token.Token token = new Token();
        String where = " datefin < current_timestamp";
        token.deleteToTable(where,connection);
    }


}
