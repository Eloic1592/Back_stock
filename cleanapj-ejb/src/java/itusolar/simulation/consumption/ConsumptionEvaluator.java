package itusolar.simulation.consumption;

import itusolar.predict.etatconsommation.ConsumptionStateEvaluatorSignature;
import itusolar.predict.historique.model.HistoriqueFilterSignature;
import itusolar.lieu.Section;
import itusolar.simulation.*;
import itusolar.simulation.material.Material;
import itusolar.simulation.classification.PlaceClassifierSignature;
import itusolar.simulation.response.SimulPred;

import java.util.Arrays;
import java.util.Vector;

public class ConsumptionEvaluator implements ConsumptionEvaluatorSignature {

    HistoriqueFilterSignature storyFilter;
    PlaceClassifierSignature placeClassifier;
    ConsumptionStateEvaluatorSignature consumptionStateEvaluator;

    public ConsumptionEvaluator(HistoriqueFilterSignature storyFilter, PlaceClassifierSignature placeClassifier,
                                ConsumptionStateEvaluatorSignature consumptionStateEvaluator) {
        this.storyFilter = storyFilter;
        this.placeClassifier = placeClassifier;
        this.consumptionStateEvaluator = consumptionStateEvaluator;
    }

    @Override
    public SimulData[][] evaluate(itusolar.simulation.response.SimulPred pred) {
        SimulData[][] result = new SimulData[pred.getSolaires().length][];
        for (int i = 0; i < pred.getSolaires().length; i++) {
            result[i] = pred.stack(pred.getSolaires()[i]);
        }
        SimulData[] allDatas = this.extract(result);
        allDatas = this.leftJoin(pred,allDatas);
        return this.placeClassifier.split(allDatas);
    }

    public SimulData[] leftJoin(SimulPred pred, SimulData[] datas) {
        int index = 0;
        SimulData[] result = new SimulData[pred.getSection().length*pred.getDates().length];
        for (Section section : pred.getSection()) {
            for (DateConcern dateConcern : pred.getDates()) {
                SimulData histMean = null;
                int heur = dateConcern.getTime().getHours();
                try {
                    histMean = (SimulData) this.storyFilter.filter(datas,heur,section.getId());
                    if (histMean.getAttachments().length > 0) {
                        histMean.configAttachments(this.consumptionStateEvaluator.extract(histMean.getAttachments()));
                        for (SimulData att : histMean.getAttachments()) {
                            att.evalConsommation(-1);
                        }
                    }
                } catch (Exception e) {
                    histMean = new SimulData();
                    histMean.setHeur(heur);
                    histMean.setDebut(dateConcern.getTimestamp());
                    histMean.setSection(section.getId());
                    histMean.setConsommation(0);
                    Material material = new Material();
                    histMean.setNb(1);
                    histMean.setAppareil(material);
                }
                result[index] = histMean;
                index++;
            }
        }
        return result;
    }

    public SimulData[] extract(SimulData[][] datas) {
        Vector<SimulData> temp = new Vector<>();
        for (SimulData[] data : datas) {
            temp.addAll(Arrays.asList(data));
        }
        SimulData[] result = new SimulData[temp.size()];
        temp.copyInto(result);
        return result;
    }
}
