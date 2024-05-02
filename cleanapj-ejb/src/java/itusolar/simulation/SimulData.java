package itusolar.simulation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.lieu.Building;
import itusolar.prepare.schedule.ScheduleDetails;
import itusolar.simulation.material.Material;
import itusolar.simulation.response.ScheduleRenderingSignature;

import java.sql.Timestamp;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulData implements HourFilter, ScheduleRenderingSignature,DataReload {
    public static int JIRAMA = 50;
    public static int SOLAIRE = 0;
    int id;
    double nb,consommation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp debut;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp fin;
    boolean changed, skip=false;
    int appareilidValue,sourceValue, lieux,heur,minute,jour,section,lieuValue;
    Material appareil;
    Building lieuTemp;

    SimulData[] attachments = new SimulData[0];

    SimulData pere;

    public int getIdValue() {
        return id;
    }

    public void setIdValue(int id) {
        this.id = id;
    }

    public Building getLieuTemp() {
        return lieuTemp;
    }

    public void setLieuTemp(Building lieuTemp) {
        this.lieuTemp = lieuTemp;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public SimulData[] getAttachments() {
        return attachments;
    }

    public void configAttachments(SimulData[] attachments) {
        this.attachments = attachments;
    }

    public void setAttachments(SimulData[] attachments) {
        this.configAttachments(attachments);
        if (this.getAttachments() != null && this.getAttachments().length > 0) {
            Material appareil = new Material();
            double conso = 0.;
            for (SimulData data : attachments)
                conso += data.getVitesse();
            appareil.setConsommation(conso);
            this.setAppareil(appareil);
            this.setNb(1.);
            this.evalConsommation(-1);
        }
    }

    @Override
    public String toString() {
        return "SimulData{" +
                "nb=" + nb +
                ", consommation=" + consommation +
                ", debut=" + debut +
                ", fin=" + fin +
                ", changed=" + changed +
                ", skip=" + skip +
                ", appareilidValue=" + appareilidValue +
                ", sourceValue=" + sourceValue +
                ", lieu=" + lieux +
                ", heur=" + heur +
                ", minute=" + minute +
                ", jour=" + jour +
                ", section=" + section +
                ", appareil=" + appareil +
                ", attachments=" + Arrays.toString(attachments) +
                ", pere=" + pere +
                '}';
    }

    public SimulData getPere() {
        return pere;
    }

    public void setPere(SimulData pere) {
        this.pere = pere;
    }

    public long countMinutes() {
        long start= this.getDebut().getTime();
        long end = this.getFin().getTime();
        long diff = end - start;
        return diff / 60000;
    }

    public SimulData() {
    }

    public SimulData(SimulData data) {
        this.setNb(data.getNb());
        this.setAppareil(data.getAppareil());
        this.setAppareilidValue(data.getAppareilidValue());
        this.setDebut(data.getDebut());
        this.setFin(data.getFin());
        this.setSourceValue(data.getSourceValue());
        this.setLieux(data.getLieux());
        this.setChanged(data.isChanged());
        this.setHeur(data.getHeur());
        this.setMinute(data.getMinute());
        this.setJour(data.getJour());
        this.setAttachments(data.getAttachments());
        this.setPere(this.getPere());
        this.setSection(data.getSection());
    }

    public boolean isSet() {
        boolean verified = this.getNb() >= 0.;
        verified &= this.getAppareilidValue() >= 0;
        verified &= this.getSourceValue() >= 0;
        verified &= this.getDebut() != null;
        verified &= this.getFin() != null;
        return verified;
    }

    public Material getAppareil() {
        return appareil;
    }

    public int getHeur() {
        return this.getDebut().getHours();
    }

    public void setHeur(int heur) {
        this.heur = heur;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }


    public void setAppareil(Material appareil) {
        this.appareil = appareil;
    }

    public double getNb() {
        return nb;
    }

    public void setNb(double nb) {
        this.nb = nb;
    }

    public int getAppareilidValue() {
        return appareilidValue;
    }

    public void setAppareilidValue(int appareilidValue) {
        this.appareilidValue = appareilidValue;

    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public int getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(int sourceValue) {
        this.sourceValue = sourceValue;
    }

    @Override
    public String getAppareilid() {
        return this.getAppareil().getLabel();
    }

    public String getLieu() {
        if (this.getLieuTemp() == null) return "";
        return this.getLieuTemp().getTitre();
    }

    public int getLieux() {
        return lieux;
    }

    @Override
    public String getSource() {
        return this.getSourceValue() == SimulData.SOLAIRE ? "Solaire" : "JIRAMA";
    }

    @JsonIgnore
    public void setLieux(int lieu) {
        this.lieux = lieu;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean sourcedByJirama() {
        return this.getSourceValue() == SimulData.JIRAMA;
    }

    public boolean sourcedBySolaire() {
        return this.getSourceValue() == SimulData.SOLAIRE;

    }

    /**
     * @param count : set count to -1 if you don't have the minutes count.
     */
    public void evalConsommation(long count) {
        count = count >= 0 ? count : this.countMinutes();
        double vitesseConso = this.getVitesse();
        vitesseConso = vitesseConso / 60.;
        double consommation = vitesseConso * ((double)count);
        this.setConsommation(consommation);
    }

    @JsonIgnore
    public double getVitesse() {
        return this.getAppVitesse() *this.getNb();
    }

    @JsonIgnore
    public double getAppVitesse() {
        return this.getAppareil().getConsommation();
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public boolean hasInterval(long start) {
        return (this.getDebut().getTime() <= start) && (start < this.getFin().getTime());
    }

    @Override
    public int heur() {
        return this.getHeur();
    }

    @Override
    public int section() {
        return this.getSection();
    }

    public int getLieuValue() {
        return lieuValue;
    }

    public void setLieuValue(int lieuValue) {
        this.lieuValue = lieuValue;
        this.setLieux(this.lieuValue);
    }

    @Override
    public String getTitle() {
        return "";
//        return this.getAppareil().getLabel();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Override
    public Timestamp getStart() {
        return this.getDebut();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Override
    public Timestamp getEnd() {
        return this.getFin();
    }

    @Override
    public String getBackgroundColor() {
        return "#02a159";
    }

    @Override
    public int getState() {
        return 150;
    }

    public ScheduleDetails toScheduleDetails() {
        ScheduleDetails detail = new ScheduleDetails();
        detail.setId(this.getIdValue());
        detail.setNombre(this.getNb());
        detail.setMaterialid(this.getAppareilidValue());
        detail.setSource(this.getSourceValue());
        detail.setLieuid(this.getLieuValue());
        detail.setDebut(this.getDebut());
        detail.setFin(this.getFin());
        return detail;
    }
}
