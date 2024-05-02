package itusolar.predict.historique.classificater;

import itusolar.historique.HistEnergyCalc;
import itusolar.predict.historique.HistMean;

public interface HistoriqueClassificatorSignature {
    public HistEnergyCalc[][] classify(HistEnergyCalc[] historiques);

    public itusolar.predict.historique.HistMean[] filterBySection(HistMean[] datas, int section);
}
