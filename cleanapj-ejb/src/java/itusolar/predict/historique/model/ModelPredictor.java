package itusolar.predict.historique.model;

import itusolar.predict.historique.HistFinalPred;
import itusolar.predict.historique.HistMean;
import itusolar.simulation.DateConcern;
import itusolar.simulation.HourFilter;

import java.sql.Date;
import java.util.Vector;

public class ModelPredictor implements ModelPredictorSignature {

    HistoriqueFilterSignature historiqueFilter;

    public ModelPredictor(HistoriqueFilterSignature historiqueFilter) {
        this.historiqueFilter = historiqueFilter;
    }

    @Override
    public HistMean[] predict(HistFinalPred[] hists, DateConcern[] dateConcerns) throws Exception {
        Vector<HistMean> stockTemp = new Vector<>();
        for (int i = 0; i < dateConcerns.length;i++) {
            int heur = dateConcerns[i].getTime().getHours();
            if (i == dateConcerns.length - 1 && dateConcerns[i-1].getTime().getHours() == heur)
                continue;
            HistFinalPred[] preds = this.cast(this.historiqueFilter.filter(hists,heur));
            for (HistFinalPred pred : preds) {
                HistMean hist = new HistMean();
                stockTemp.add(hist);
                hist.setPuissanceentree(pred.getYprediction()*dateConcerns[i].getTemperature());
                System.out.println(hist.getPuissanceentree());
                hist.setHeure(heur);
                hist.setDate(new Date(dateConcerns[i].getTimestamp().getTime()));
                hist.setLieuid(pred.getLieuid());
                hist.setSection(pred.getSection());
            }
        }
        HistMean[] result = new HistMean[stockTemp.size()];
        stockTemp.copyInto(result);
        return result;
    }

    public HistFinalPred[] cast(HourFilter[] datas) {
        HistFinalPred[] answer = new HistFinalPred[datas.length];
        for (int i = 0; i < datas.length; i++) {
            answer[i] = (HistFinalPred) datas[i];
        }
        return answer;
    }
}
