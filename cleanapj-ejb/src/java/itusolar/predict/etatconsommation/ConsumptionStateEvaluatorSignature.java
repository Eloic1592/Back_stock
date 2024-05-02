package itusolar.predict.etatconsommation;

import itusolar.predict.historique.HistMean;
import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;

public interface ConsumptionStateEvaluatorSignature {
    public void consume(SimulPred pred, HistMean[] histMeans);
    public long estimeFin(HistMean hist, SimulData data, double conso);
    public long estimeFin(HistMean hist, SimulData data, double conso,boolean absolute);

    public SimulData[] extract(SimulData[] datas);
}
