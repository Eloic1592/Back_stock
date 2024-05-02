package itusolar.historique;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.historique.filter.BloomData;
import itusolar.utils.HUtils;
import itusolar.prepare.MappedInteger;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistEnergy extends MappedInteger implements BloomData {
    private int sourceid;
    private double tensionentre,tensionsortie,intensiteentree,intensitesortie,puissancebatt,temperature;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp datins;

    private double duree;

    public HistEnergy(int sourceid, double tensionentre, double tensionsortie, double intensiteentree, double intensitesortie, double puissancebatt, double temperature, Timestamp datins) {
        this();
        this.setSourceid(sourceid);
        this.setTensionentre(tensionentre);
        this.setTensionsortie(tensionsortie);
        this.setIntensiteentree(intensiteentree);
        this.setIntensitesortie(intensitesortie);
        this.setPuissancebatt(puissancebatt);
        this.setTemperature(temperature);
        this.setDatins(datins);
    }

    public void check() throws Exception {
        if (this.getTemperature() <= 0.) {
            throw new Exception("Temperature must be greater than zero");
        }
    }

    public void evalDuree(Timestamp end) {
        if (end != null && this.getDatins() != null) {
            end = this.eval(end);
            long endTime = HUtils.timestampToLong(end);
            long startTime = HUtils.timestampToLong(this.getDatins());
            long diff = endTime - startTime;
            double diffMinutes =  (((double)diff)/60000.);
            this.setDuree(diffMinutes);
        }
    }

    private Timestamp eval(Timestamp end) {
        LocalDateTime ldEnd = end.toLocalDateTime();
        LocalDateTime ldStart = this.getDatins().toLocalDateTime();
        if (ldEnd.getYear() == ldStart.getYear() && ldStart.getMonth().getValue() == ldStart.getMonth().getValue()
                && ldStart.getDayOfMonth() == ldEnd.getDayOfMonth()) {
            return end;
        }
        ldEnd = LocalDateTime.of(ldStart.toLocalDate(), LocalTime.of(23, 59));
        return Timestamp.valueOf(ldEnd);
    }


    public void setDuree(double duree) {
        this.duree = duree;
    }

    public double toWaltPerSecond(double seconds) {
        return ((double)seconds) / 60.;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
    }

    public double getDuree() {
        return duree;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqHistEnergie");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public HistEnergy() {
        this.setNomTable("HistEnergie");
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public double getTensionentre() {
        return tensionentre;
    }

    public void setTensionentre(double tensionentre) {
        this.tensionentre = tensionentre;
    }

    public double getTensionsortie() {
        return tensionsortie;
    }

    public void setTensionsortie(double tensionsortie) {
        this.tensionsortie = tensionsortie;
    }

    public double getIntensiteentree() {
        return intensiteentree;
    }

    public void setIntensiteentree(double intensiteentree) {
        this.intensiteentree = intensiteentree;
    }

    public double getIntensitesortie() {
        return intensitesortie;
    }

    public void setIntensitesortie(double intensitesortie) {
        this.intensitesortie = intensitesortie;
    }

    public double getPuissancebatt() {
        return puissancebatt;
    }

    public void setPuissancebatt(double puissancebatt) {
        this.puissancebatt = puissancebatt;
    }

    public Timestamp getDatins() {
        return datins;
    }

    public void setDatins(Timestamp datins) {
        this.datins = datins;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getData() {
        return String.format("%s-%s", this.getSourceid(), this.getDatins());
    }
}
