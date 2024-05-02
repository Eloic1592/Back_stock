package inventorymanagement.login;

import historique.MapUtilisateur;
import itusolar.auth.LoginResult;


import java.sql.Connection;

public interface LoginManagerSignature {
    public MapUtilisateur getOne(UserParam userParam,Connection connection) throws Exception;
}
