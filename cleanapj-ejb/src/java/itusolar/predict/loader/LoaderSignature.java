package itusolar.predict.loader;

import itusolar.simulation.DateConcern;
import itusolar.simulation.SimulData;
import itusolar.simulation.material.Material;
import itusolar.simulation.response.SimulPred;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public interface LoaderSignature {
    public void loadAppareil(SimulData[] datas, Connection connection) throws Exception;
    public void loadAppareil(SimulData datas, Map<String, Material> temps, Connection connection) throws Exception;
    public void loadTemperature(DateConcern[] dates, Connection connection) throws IOException;
    public void loadSection(SimulData[] datas,Connection connection) throws Exception;
    public void loadSection(SimulPred datas, Connection connection) throws Exception;
    public void loadSectionAndTemperature(SimulPred datas, Connection connection) throws Exception;
    public void loadAppareilAndSection(SimulData[] datas,Connection connection) throws Exception;
    public void loadCapacity(itusolar.predict.historique.HistMean[] histMeans, Connection connection) throws Exception;
}
