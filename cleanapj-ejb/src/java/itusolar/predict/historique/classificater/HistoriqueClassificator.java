package itusolar.predict.historique.classificater;

import itusolar.historique.HistEnergyCalc;
import itusolar.predict.historique.HistMean;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Vector;

public class HistoriqueClassificator implements HistoriqueClassificatorSignature, Comparator<HistMean> {

    @Override
    public HistEnergyCalc[][] classify(HistEnergyCalc[] historizes) {
        Vector<Vector<HistEnergyCalc>> results = new Vector<>();
        Vector<HistEnergyCalc> vTemp = new Vector<>();
        HistEnergyCalc temp = null;
        for (int i = 0; i < historizes.length; i++) {
            HistEnergyCalc hist = historizes[i];
            if (temp == null) {
                temp = hist;
            }
            if (this.memeTranche(temp, hist)) {
                vTemp.add(hist);
                if (i == historizes.length-1)
                    results.add(vTemp);
            }else  {
                results.add(vTemp);
                temp = hist;
                vTemp = new Vector<>();
                vTemp.add(temp);
            }
        }
        HistEnergyCalc[][] answer = new HistEnergyCalc[results.size()][];
        int indice = 0;
        for (Vector<HistEnergyCalc> result: results) {
            answer[indice] = new HistEnergyCalc[result.size()];
            result.copyInto(answer[indice]);
            indice++;
        }
        return answer;
    }

    public boolean memeTranche(HistEnergyCalc first, HistEnergyCalc second) {
        boolean verif = (Objects.equals(first.getHr(), second.getHr()));
        verif &= (Objects.equals(first.getDy(), second.getDy()));
        verif &= (Objects.equals(first.getMt(), second.getMt()));
        verif &= (Objects.equals(first.getYr(), second.getYr()));
        return verif;
    }

    @Override
    public itusolar.predict.historique.HistMean[] filterBySection(itusolar.predict.historique.HistMean[] datas, int section) {
        Vector<itusolar.predict.historique.HistMean> stockTemp = new Vector<>();
        for (itusolar.predict.historique.HistMean data : datas) {
            if (data.getSection() == section) {
                stockTemp.add(data);
            }
        }
        itusolar.predict.historique.HistMean[] result = new itusolar.predict.historique.HistMean[stockTemp.size()];
        stockTemp.copyInto(result);
        Arrays.sort(result, this);
        return result;
    }

    @Override
    public int compare(HistMean o1, HistMean o2) {
        int comparedValue = Long.compare(o1.getDate().getTime(),o2.getDate().getTime());
        if (comparedValue == 0) {
            return Integer.compare(o1.getHeure(),o2.getHeure());
        }
        return comparedValue;
    }
}
