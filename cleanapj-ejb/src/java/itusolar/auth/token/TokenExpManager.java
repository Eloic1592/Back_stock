package itusolar.auth.token;

import bean.CGenUtil;
import itusolar.auth.token.HTokenExpManager;
import itusolar.auth.token.TokenExpire;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class TokenExpManager extends HServiceManager implements HTokenExpManager {

    @Override
    public itusolar.auth.token.TokenExpire getTokenExpire(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] columns = {};
        String[] values = {};
        Object[] datas = CGenUtil.rechercher(new itusolar.auth.token.TokenExpire(),columns,values,connection,"");
        return datas.length > 0 ? (itusolar.auth.token.TokenExpire) datas[0]:this.getDefaultTokenExpire(connection);
    }

    @Override
    public itusolar.auth.token.TokenExpire getDefaultTokenExpire(Connection connection) throws Exception {
        itusolar.auth.token.TokenExpire tokenExpire = new TokenExpire();
        int min = 60*24;
        tokenExpire.setDuree(min);
        return tokenExpire;
    }
}
