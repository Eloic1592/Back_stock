package itusolar.predict.loader;

import itusolar.lieu.Building;
import itusolar.lieu.BuildingManagerSignature;
import itusolar.lieu.SectionManagerSignature;
import itusolar.predict.historique.HistMean;
import itusolar.simulation.material.Material;
import itusolar.simulation.material.MaterialManagerSignature;
import itusolar.simulation.DateConcern;
import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;
import itusolar.source.SectionCapacity;
import itusolar.source.SectionCapacityManagerSignature;
import utilitaire.UtilDB;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Loader implements LoaderSignature {
    BuildingManagerSignature lieuManager;
    MaterialManagerSignature appareilManager;
    SectionCapacityManagerSignature sectionCapacityManager;
    SectionManagerSignature sectionManager;

    public Loader(BuildingManagerSignature lieuManager, MaterialManagerSignature appareilManager,
                  SectionCapacityManagerSignature sectionCapacityManager, SectionManagerSignature sectionManager) {
        this.lieuManager = lieuManager;
        this.appareilManager = appareilManager;
        this.sectionCapacityManager = sectionCapacityManager;
        this.sectionManager = sectionManager;
    }

    @Override
    public void loadAppareil(SimulData[] datas, Connection connection) throws Exception {
        Map<String, Material> materials = new HashMap<String, Material>();
        for (SimulData data : datas) {
            this.loadAppareil(data,materials, connection);
        }
    }

    @Override
    public void loadAppareil(SimulData datas, Map<String, Material> temps, Connection connection) throws Exception {
        int appareilId = datas.getAppareilidValue();
        Material temp = null;
        String keys = ""+appareilId;
        if ((temp = temps.get(keys)) == null) {
            temp = this.appareilManager.getById(appareilId,connection);
            temps.put(keys,temp);
        }
        datas.setAppareil(temp);
    }

    @Override
    public void loadTemperature(DateConcern[] dates, Connection connection) throws IOException {
        for (DateConcern date : dates) {
            date.setTemperature(30.);
        }
    }

    @Override
    public void loadSection(SimulData[] datas, Connection connection) throws Exception {
        connection= connection == null ? new UtilDB().GetConn() : connection;
        HashMap<String, Building> temps = new HashMap<>();
        for (SimulData data : datas) {
            String keys = String.valueOf(data.getLieux());
            Building lieu = null;
            if ( (lieu = temps.get(keys)) == null) {
                lieu = this.lieuManager.getById(data.getLieux(), connection);
                temps.put(keys, lieu);
            }
            data.setSection(lieu.getSectionid());
            data.setLieuTemp(lieu);
        }
    }

    @Override
    public void loadSection(SimulPred datas, Connection connection) throws Exception {
        datas.setSection(this.sectionManager.getAll(connection));
    }

    @Override
    public void loadSectionAndTemperature(SimulPred datas, Connection connection) throws Exception {
        this.loadSection(datas, connection);
        this.loadTemperature(datas.getDates(),connection);
    }

    @Override
    public void loadAppareilAndSection(SimulData[] datas, Connection connection) throws Exception {
        this.loadAppareil(datas, connection);
        this.loadSection(datas, connection);
    }

    @Override
    public void loadCapacity(HistMean[] histMeans, Connection connection) throws Exception {
        HashMap<Integer, SectionCapacity> sections = new HashMap<>();
        for (itusolar.predict.historique.HistMean histMean : histMeans) {
            SectionCapacity section = null;
            int sectionId = histMean.getSection();
            if ((section = sections.get(sectionId)) == null) {
                section = this.sectionCapacityManager.getBySection(sectionId,connection);
                sections.put(sectionId, section);
            }
            histMean.setCapacity(section);
        }
    }
}
