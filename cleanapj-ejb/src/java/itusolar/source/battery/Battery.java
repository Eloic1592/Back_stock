package itusolar.source.battery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Battery extends MappedInteger {
    String titre;
    double capacite;
    public Battery() {
        this.setNomTable("batterie");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqBatterie");
        this.setId(Integer.parseInt(makePK(c)));
    }
    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
