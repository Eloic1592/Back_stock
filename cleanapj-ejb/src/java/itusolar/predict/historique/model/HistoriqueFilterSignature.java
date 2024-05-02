package itusolar.predict.historique.model;

import itusolar.simulation.HourFilter;

public interface HistoriqueFilterSignature {
    public HourFilter filter(HourFilter[] hists, int heur, int lieu) throws Exception;

    public HourFilter[] filter(HourFilter[] hists, int heur) throws Exception;
}
