package itusolar.simulation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.lieu.*;
import itusolar.prepare.schedule.Schedule;
import itusolar.prepare.schedule.ScheduleAccessorSignature;
import itusolar.simulation.material.Material;
import itusolar.simulation.material.MaterialManager;
import itusolar.simulation.material.MaterialManagerSignature;
import utilitaire.UtilDB;

import java.sql.Connection;

public class SimulationUtils {
    Material[] appareils;
    Building[] lieux;

    Section[] sections;
    Schedule[] schedules;
    boolean connexOpen;
    @JsonIgnore
    ScheduleAccessorSignature accessor;
    @JsonIgnore
    MaterialManagerSignature materialManager;

    public SimulationUtils() {
    }

    public SimulationUtils(Schedule schedule, ScheduleAccessorSignature accessor, Connection connection) throws Exception {
        this(schedule,accessor,new MaterialManager(),new BuildingManager(null),new SectionManager(),connection);
    }



    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }

    public boolean isConnexOpen() {
        return connexOpen;
    }

    public void setConnexOpen(boolean connexOpen) {
        this.connexOpen = connexOpen;
    }

    public SimulationUtils(Schedule schedule, ScheduleAccessorSignature accessor, MaterialManagerSignature materialManager, BuildingManagerSignature lieuxManager, SectionManagerSignature sectionManager, Connection connection) throws Exception {
        ((BuildingManager)lieuxManager).setSectionManager(sectionManager);
        this.connexOpen = (connection != null);
        this.accessor = accessor;
        this.materialManager = materialManager;
        connection = (this.connexOpen)? connection : new UtilDB().GetConn();
        this.config(lieuxManager,connection);
        this.config(materialManager,connection);
        this.config(sectionManager,connection);
        this.config(schedule,connection);
        this.config(null,lieuxManager,connection);
        if (this.isConnexOpen())
            connection.close();
    }

    public void config(ScheduleAccessorSignature accessor, BuildingManagerSignature lieuManager, Connection connection) throws Exception {
        if (this.schedules != null) {
            for (Schedule schedule : this.schedules) {
                for (SimulData detail : schedule.getDatas()) {
                    detail.setAppareil(this.find(detail,connection));
                    detail.setLieuTemp(this.find(detail,connection,lieuManager));
                }
            }
        }
    }

    public Building find(SimulData detail, Connection connection, BuildingManagerSignature lieuManager) throws Exception {
        return lieuManager.getById(detail.getLieux(), connection);
    }

    public void config(Schedule schedule,Connection connection) throws Exception {
        this.setSchedules(this.accessor.getById(schedule, connection));
    }

    public void config(SectionManagerSignature manager, Connection connection) throws Exception {
        this.setSections(manager.getAll(connection));
    }

    public void config(MaterialManagerSignature appareilManager, Connection connection) throws Exception {
        this.setAppareils(appareilManager.getAll(connection));
    }

    public void config(BuildingManagerSignature lieuxManager, Connection connection) throws Exception {
        this.setLieux(lieuxManager.getAll(connection));
    }

    public Material[] getAppareils() {
        return appareils;
    }

    public void setAppareils(Material[] appareils) {
        this.appareils = appareils;
    }

    public Building[] getLieux() {
        return lieux;
    }

    public void setLieux(Building[] lieux) {
        this.lieux = lieux;
    }

    public Schedule[] getSchedules() {
        return schedules;
    }

    public Material find(SimulData detail,Connection connection) throws Exception {
        return this.materialManager.getById(detail.getAppareilidValue(),connection);
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }
}
