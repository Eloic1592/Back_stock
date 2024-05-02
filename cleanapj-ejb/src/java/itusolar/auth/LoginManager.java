package itusolar.auth;

import bean.CGenUtil;
import historique.MapRoles;
import historique.MapUtilisateur;
import historique.RoleUtil;
import historique.UtilisateurUtil;
import itusolar.auth.token.HTokenExpManager;
import itusolar.auth.token.HTokenManager;
import itusolar.auth.token.Token;
import itusolar.auth.token.TokenExpManager;
import itusolar.auth.token.TokenExpire;
import itusolar.auth.token.TokenManager;
import itusolar.prepare.HServiceManager;
import user.UserEJB;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager extends HServiceManager implements HLoginManager {

    HTokenManager manager = new TokenManager();
    HTokenExpManager expManager = new TokenExpManager();
    UtilisateurUtil loginManager = new UtilisateurUtil();

    @Override
    public void checkLogin(UserParam params, int grade, Connection connection) throws Exception {
        Logger logger = Logger.getLogger("LoginManager");
        logger.log(Level.INFO, String.format("%s grade : %s", params, grade));
        if (grade < 0)
            return;
        Token token = params.toToken();
        token = this.manager.hasToken(token, connection);
        MapUtilisateur user = this.getUserByRef(token, connection);
        MapRoles role = this.getRoleById(user, connection);
        if (role.getRang() > grade) {
            throw new PermissionDeniedException();
        }
    }

    public MapUtilisateur getUserByRef(Token token, Connection connection) throws Exception {
        String ref = String.valueOf(token.getUtilisateurid());
        String[] temp = {};
        String where = " and refuser = " + ref;
        Object[] datas = CGenUtil.rechercher(new MapUtilisateur(), temp, temp, connection, where);
        if (datas.length > 0)
            return (MapUtilisateur) datas[0];
        throw new Exception("Could not find user");
    }

    public MapRoles getRoleById(MapUtilisateur user, Connection connection) throws Exception {
        String[] temp = {};
        String where = " and idrole = '"+user.getIdrole()+"'";
        Object[] datas = (CGenUtil.rechercher(new MapRoles(), temp, temp, connection, where));
        if (datas.length > 0)
            return (MapRoles) datas[0];
        throw new Exception("Could not find roles");
    }

    @Override
    public MapUtilisateur getOne(UserParam param) throws Exception {
        return this.loginManager.testeValide(param.getIdentifiant(),param.getPasse());
    }

    @Override
    public LoginResult login(UserParam param, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        this.getOne(param);
        MapUtilisateur userResult = this.getOne(param);
        LoginResult result = new LoginResult();
        result.setUtilisateur(userResult);
        TokenExpire tokenExpire = this.expManager.getTokenExpire(connection);
        result.generateToken(tokenExpire);
        this.manager.create(result.getToken(),connection);
        return result;
    }
}
