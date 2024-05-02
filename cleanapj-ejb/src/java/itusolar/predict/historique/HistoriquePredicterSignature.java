package itusolar.predict.historique;

import itusolar.historique.HistEnergyCalc;
import itusolar.lieu.Section;
import itusolar.simulation.DateConcern;

import java.sql.Connection;


public interface HistoriquePredicterSignature {

    public HistMean[] predict(HistEnergyCalc[] historiques, DateConcern[] dateConcerns, Section[] lieux) throws Exception;

    public HistMean[] predict(DateConcern[] dateConcerns, Connection connection) throws Exception;
    public HistMean[] predict(DateConcern[] dateConcerns, HistFinalPred[] pred) throws Exception;

    public void refresh();
}
