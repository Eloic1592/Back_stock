package itusolar.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.auth.token.Token;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserParam {
    String identifiant;
    String passe;
    String interim;
    String service;
    String token;

    public Token toToken() {
        Token token = new Token();
        token.setToken(this.getToken());
        return token;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "identifiant='" + identifiant + '\'' +
                ", passe='" + passe + '\'' +
                ", interim='" + interim + '\'' +
                ", service='" + service + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }

    public String getInterim() {
        return interim;
    }

    public void setInterim(String interim) {
        this.interim = interim;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
