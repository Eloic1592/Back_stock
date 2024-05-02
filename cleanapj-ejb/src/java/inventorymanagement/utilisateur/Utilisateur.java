package inventorymanagement.utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Utilisateur extends MappedInteger {
        int reuser;
        String loginuser;
        String pwduser;
        String nomuser;
        String aduser;
        String teluser;
        String idrole;

    public Utilisateur() {
        setNomTable("utilisateur");
    }
    public int getReuser() {
        return reuser;
    }

    public void setReuser(int reuser) {
        this.reuser = reuser;
    }

    public String getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(String loginuser) {
        this.loginuser = loginuser;
    }

    public String getPwduser() {
        return pwduser;
    }

    public void setPwduser(String pwduser) {
        this.pwduser = pwduser;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getAduser() {
        return aduser;
    }

    public void setAduser(String aduser) {
        this.aduser = aduser;
    }

    public String getTeluser() {
        return teluser;
    }

    public void setTeluser(String teluser) {
        this.teluser = teluser;
    }

    public String getIdrole() {
        return idrole;
    }

    public void setIdrole(String idrole) {
        this.idrole = idrole;
    }
}
