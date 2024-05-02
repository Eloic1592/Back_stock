package itusolar.historique.dashboard.difference;

import itusolar.historique.HistEnergyCalc;
import itusolar.historique.HistoriqueManagerSignature;
import itusolar.historique.HistoriqueParams;
import itusolar.historique.dashboard.evolution.EvolutionParams;
import itusolar.historique.dashboard.evolution.EvolutionState;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RealConsumptionPreparator extends HServiceManager implements RealConsumptionPreparatorSignature{
    HistoriqueManagerSignature historyManager;

    public RealConsumptionPreparator(HistoriqueManagerSignature historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public EvolutionState[] evaluate(EvolutionParams params, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Timestamp start = new Timestamp(params.getStart().getTime());
        Timestamp end = new Timestamp(params.getEnd().getTime());
        HistoriqueParams histParams = new HistoriqueParams();
        histParams.setStart(start);
        histParams.setEnd(end);
        histParams.setPage(-1);
        Object[] datas = this.historyManager.search(null, histParams, connection, "");
        HistEnergyCalc[] responses = this.historyManager.cast(datas);
        return this.evaluate(responses, end, connection);
    }

    public EvolutionState[] evaluate(HistEnergyCalc[] hist, Timestamp end, Connection connection) {
        this.load(hist, end);
        Map<String, EvolutionState> responses = new HashMap<>();
        System.out.println("length : "+hist.length);
        for (HistEnergyCalc h : hist) {
            LocalDateTime date =h.getDatins().toLocalDateTime();
            String key = ""+ date.getDayOfMonth();
            EvolutionState state = responses.get(key);
            if (state == null) {
                state = new EvolutionState();
                int day = date.getDayOfMonth();
                state.setIndex(day+"");
                state.setOrder(day);
                state.setLabel("Real Consumption");
                responses.put(key, state);
            }
            double vitesse = h.getPuissancesortie() / 60.;
            vitesse = vitesse * h.getDuree();
            state.setValue(state.getValue()+vitesse);
        }
        EvolutionState[] answers = new EvolutionState[responses.size()];
        int[] index = {0};
        responses.forEach((key, value) -> {
            answers[index[0]] = value;
            index[0]++;
        });
        return answers;
    }

    public void load(HistEnergyCalc[] hist, Timestamp end) {
        for (int i = 0; i < hist.length-1; i++) {
            hist[i].evalDuree(hist[i+1].getDatins());
        }
        if (hist.length > 0) {
            hist[hist.length-1].evalDuree(end);
        }
    }
}
