package itusolar.predict.historique.model;

import itusolar.simulation.HourFilter;

import java.util.Vector;

public class HistoriqueFilter implements HistoriqueFilterSignature {

    @Override
    public HourFilter filter(HourFilter[] hists, int heur, int lieu) throws Exception {
        HourFilter[] result = this.filter(hists,heur);
        for(HourFilter pred : result) {
            if (pred.section() == lieu)
                return pred;
        }
        throw new Exception("Invalid");
    }

    public HourFilter[] filter(HourFilter[] hists, int heur) throws Exception {
        Vector<HourFilter> result = new Vector<>();
        for (HourFilter hist : hists) {
            if (hist.heur() == heur) {
                result.add(hist);
            }
        }
        if (result.size() == 0)
            throw new Exception("Invalid heur");
        HourFilter[] answer = new HourFilter[result.size()];
        result.copyInto(answer);
        return answer;
    }
}
