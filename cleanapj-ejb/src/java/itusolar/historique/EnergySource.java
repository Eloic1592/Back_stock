package itusolar.historique;

import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class EnergySource extends MappedInteger {
    String titre;
    Integer typeid;

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("id", "GETSeqEnergySource");
        this.setId(Integer.valueOf(makePK(c)));
    }
    @Override
    public String getNomTable() {
        return "energysource";
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}
