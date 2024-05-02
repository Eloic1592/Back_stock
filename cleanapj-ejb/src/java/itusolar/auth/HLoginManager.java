package itusolar.auth;

import historique.MapUtilisateur;
import user.UserEJB;

import java.sql.Connection;

public interface HLoginManager {
    public void checkLogin(UserParam params, int grade, Connection connection) throws Exception;

    public MapUtilisateur getOne(itusolar.auth.UserParam param) throws Exception;
    public LoginResult login( UserParam param, Connection connection) throws Exception;
}
