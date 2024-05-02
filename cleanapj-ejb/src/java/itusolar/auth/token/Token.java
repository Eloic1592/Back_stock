package itusolar.auth.token;

import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class Token extends MappedInteger {
    int utilisateurid;
    String token;
    Timestamp dateins, datefin;

    public Token() {
        this.setNomTable("token");
    }

    public void init() {
         String token = this.generateToken();
         token = this.encryptToken(token, "pymkly");
         this.setToken(token);
    }

    public String generateToken() {
        long timestamp = Instant.now().toEpochMilli();
        String token = Long.toString(timestamp, 36)+this.getUtilisateurid() + UUID.randomUUID().toString().replace("-", "");
        return token.substring(0, 32); // Truncate to 32 characters
    }

    public String encryptToken(String token, String key) {
        char[] tokenChars = token.toCharArray();
        char[] keyChars = key.toCharArray();
        char[] encrypted = new char[tokenChars.length];
        for (int i = 0; i < tokenChars.length; i++) {
            encrypted[i] = (char) (tokenChars[i] ^ keyChars[i % keyChars.length]);
        }
        return new String(encrypted);
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqtoken");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public int getUtilisateurid() {
        return utilisateurid;
    }

    public void setUtilisateurid(int utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateins() {
        return dateins;
    }

    public void setDateins(Timestamp dateins) {
        this.dateins = dateins;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }
}
