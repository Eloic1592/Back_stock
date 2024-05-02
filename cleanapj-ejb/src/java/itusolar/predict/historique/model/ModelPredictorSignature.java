package itusolar.predict.historique.model;

import itusolar.predict.historique.HistFinalPred;
import itusolar.predict.historique.HistMean;
import itusolar.simulation.DateConcern;

public interface ModelPredictorSignature {
    public HistMean[] predict(HistFinalPred[] hists, DateConcern[] dateConcerns) throws Exception;
}
