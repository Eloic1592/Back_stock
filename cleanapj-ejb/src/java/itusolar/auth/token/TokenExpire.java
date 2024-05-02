package itusolar.auth.token;

import itusolar.auth.token.Token;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TokenExpire extends MappedInteger {
    int duree;

    public TokenExpire() {
        this.setNomTable("tokenexpires");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqtoken");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public void configToken(Token token) {
        long current = token.getDateins().getTime();
        current += this.getMillisValue();
        token.setDatefin(new Timestamp(current));
    }

    public long getMillisValue() {
        return (long) this.getDuree() *60*1000;
    }


    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
