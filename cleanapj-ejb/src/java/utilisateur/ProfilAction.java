/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur;

import bean.ClassMAPTable;
import java.sql.Connection;

/**
 * @deprecated 
 * Pour gerer les actions et leurs restrictions, il est mieux d'utiliser {@link utilisateur.Restriction}
 * @author rasol
 */
public class ProfilAction extends ClassMAPTable{
    private String id, entite, profil, createUpdate, validate, annulerVisa, lecture;

    public ProfilAction() {
        this.setNomTable("ProfilAction");
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUpdate() {
        return createUpdate;
    }

    public void setCreateUpdate(String createUpdate) {
        this.createUpdate = createUpdate;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getAnnulerVisa() {
        return annulerVisa;
    }

    public void setAnnulerVisa(String annulerVisa) {
        this.annulerVisa = annulerVisa;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getEntite() {
        return entite;
    }

    public void setEntite(String entite) {
        this.entite = entite;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PFA", "getseqprofilaction");
        this.setId(makePK(c));
    }
}
