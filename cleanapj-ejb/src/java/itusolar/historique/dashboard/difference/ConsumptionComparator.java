package itusolar.historique.dashboard.difference;

import itusolar.historique.dashboard.evolution.EvolutionDash;
import itusolar.historique.dashboard.evolution.EvolutionDashManager;
import itusolar.historique.dashboard.evolution.EvolutionParams;
import itusolar.historique.dashboard.evolution.EvolutionState;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.schedule.predict.SchedulePreparatorySignature;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ConsumptionComparator extends EvolutionDashManager {
    RealConsumptionPreparatorSignature historiqueManager;

    public ConsumptionComparator(SchedulePreparatorySignature schedulePreparatory, LoaderSignature loader,
                                 RealConsumptionPreparatorSignature historiqueManager) {
        super(schedulePreparatory, loader);
        this.historiqueManager = historiqueManager;
    }

    @Override
    public EvolutionDash dashContent(EvolutionParams params, Connection connection) throws Exception {
        EvolutionDash dash = super.dashContent(params, connection);
        EvolutionState[] states = this.sum(dash);
        EvolutionState[] realConsumptions = this.historiqueManager.evaluate(params,connection);
        return new ComparedDash(states,realConsumptions);
    }



    public EvolutionState[] sum(EvolutionDash dash) {
        Map<String, EvolutionState> responses = new HashMap<>();
        for (EvolutionState state : dash.getEvolutionStates()) {
            String key = ""+state.getOrder();
            EvolutionState response = responses.get(key);
            if (response == null) {
                response = state;
                responses.put(key, response);
            } else {
                response.setValue(response.getValue() + state.getValue());
            }
        }
        EvolutionState[] states = new EvolutionState[responses.size()];
        int[] index = {0};
        responses.forEach((key, value) -> {
            states[index[0]] = value;
            index[0]++;
        });
        return states;
    }
}
