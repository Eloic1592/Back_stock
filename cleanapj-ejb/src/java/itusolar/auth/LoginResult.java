package itusolar.auth;

import historique.MapUtilisateur;
import itusolar.auth.token.Token;
import itusolar.auth.token.TokenExpire;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LoginResult {
    MapUtilisateur utilisateur;
    itusolar.auth.token.Token token;

    public itusolar.auth.token.Token getToken() {
        return token;
    }

    public void setToken(itusolar.auth.token.Token token) {
        this.token = token;
    }

    public void generateToken(TokenExpire tokenExpire) throws NoUserForTokenException, NoSuchAlgorithmException {
        if (this.utilisateur == null)
            throw new NoUserForTokenException();
        String tokenValue = this.generateToken(this.getUtilisateur());
        itusolar.auth.token.Token token = new Token();
        token.setUtilisateurid(this.getUtilisateur().getRefuser());
        token.setDateins(Timestamp.valueOf(LocalDateTime.now()));
        tokenExpire.configToken(token);
        token.setToken(tokenValue);
        this.setToken(token);
    }

    private String generateToken(MapUtilisateur utilisateur) throws NoSuchAlgorithmException {
        String ans =utilisateur.getLoginuser() + utilisateur.getNomuser()+utilisateur.getEtat() + LocalDateTime.now().toString();
        MessageDigest message = MessageDigest.getInstance("SHA-1");
        byte[] mesDig = message.digest(ans.getBytes());
        BigInteger no = new BigInteger(1, mesDig);
        ans = no.toString(32);
        while (ans.length() < 32) {
            ans = "0" + ans;
        }
        return ans;
    }


    public MapUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(MapUtilisateur utilisateur) throws NoSuchAlgorithmException, NoUserForTokenException {
        this.utilisateur = utilisateur;
    }
}
