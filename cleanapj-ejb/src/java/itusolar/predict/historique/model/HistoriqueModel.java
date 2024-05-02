package itusolar.predict.historique.model;

import itusolar.historique.HistEnergyCalc;
import itusolar.predict.historique.HistFinalPred;
import itusolar.lieu.Section;

public interface HistoriqueModel {
    public HistFinalPred[] fit(HistEnergyCalc[] historiques, Section[] lieux);
}
