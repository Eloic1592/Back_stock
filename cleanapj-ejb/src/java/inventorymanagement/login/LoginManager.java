package inventorymanagement.login;

import bean.CGenUtil;
import historique.MapUtilisateur;
import historique.UtilisateurUtil;
import inventorymanagement.article.Stockarticle;
import itusolar.auth.LoginResult;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class LoginManager extends HServiceManager implements LoginManagerSignature {
    UtilisateurUtil utilisateurUtil = new UtilisateurUtil();
    @Override
    public MapUtilisateur getOne(UserParam userParam, Connection connection) throws Exception {
        return this.utilisateurUtil.testeValide(userParam.getLoginuser(),userParam.getPwduser());
    }
}
