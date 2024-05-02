package itusolar.predict;

import itusolar.jirama.HTarifManager;
import itusolar.jirama.Tarif;
import itusolar.predict.etatconsommation.ConsumptionStateEvaluatorSignature;
import itusolar.predict.historique.HistMean;
import itusolar.predict.historique.HistoriquePredicterSignature;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.HServiceManager;
import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;
import itusolar.simulation.SimulationSignature;
import itusolar.simulation.filter.DataIgnoreSignature;
import utilitaire.UtilDB;

import java.sql.Connection;

public class Predicter extends HServiceManager implements PredicterSignature{
    SimulationSignature simulationManager;
    HTarifManager tarifManager;
    HistoriquePredicterSignature histManager;
    LoaderSignature loader;
    DataIgnoreSignature dataIgnore;
    ConsumptionStateEvaluatorSignature consoEvaluator;

    public Predicter(SimulationSignature simulationManager, HTarifManager tarifManager,
                     HistoriquePredicterSignature histManager, LoaderSignature loader, DataIgnoreSignature dataIgnore,
                     ConsumptionStateEvaluatorSignature consoEvaluator) {
        this.simulationManager = simulationManager;
        this.tarifManager = tarifManager;
        this.histManager = histManager;
        this.loader = loader;
        this.dataIgnore = dataIgnore;
        this.consoEvaluator = consoEvaluator;
    }

    @Override
    public SimulPred predict(PredictionParameter data, Connection connection) throws Exception {
        SimulData[] datas = data.getDatas();
        datas = this.dataIgnore.removeNotChanged(datas);
        data.setDatas(datas);
        boolean verifConnection = connection == null;
        connection= verifConnection ? new UtilDB().GetConn() : connection;
        this.loader.loadAppareilAndSection(datas,connection);
        Tarif tarif = this.tarifManager.getTarifComplete(connection);
        SimulPred pred = this.simulationManager.prepareToPredict(data, tarif,connection);
        this.loader.loadSectionAndTemperature(pred,connection);
        HistMean[] histMeans = this.histManager.predict(pred.getDates(),connection);
        pred.setHistMeans(histMeans);
        this.loader.loadCapacity(histMeans,connection);
        this.consoEvaluator.consume(pred,histMeans);
        if (verifConnection)
            connection.close();
        pred.loadStat();
        pred.loadExcess();
        pred.loadDates();
        return pred;
    }

}
