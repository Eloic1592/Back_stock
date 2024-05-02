package itusolar.prepare.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.SimulData;
import itusolar.simulation.material.Material;

import java.sql.Connection;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleDetails extends MappedInteger {
    double nombre;
    int materialid,source, lieuid,scheduleid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp debut,fin;

    public ScheduleDetails() {
        this.setNomTable("scheduledetails");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqScheduleDetails");
        this.setId(Integer.parseInt(makePK(c)));

    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getLieuid() {
        return lieuid;
    }

    public void setLieuid(int lieuid) {
        this.lieuid = lieuid;
    }

    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
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

    public SimulData toSimulData() {
        SimulData data = new SimulData();
        data.setIdValue(this.getId());
        data.setNb(this.getNombre());
        Material material = new Material();
        material.setId(this.getMaterialid());
        data.setAppareilidValue(this.getMaterialid());
        data.setAppareil(material);
        data.setSourceValue(this.getSource());
        data.setLieuValue(this.getLieuid());
        data.setDebut(this.getDebut());
        data.setFin(this.getFin());
        data.setChanged(true);
        return data;
    }
}
