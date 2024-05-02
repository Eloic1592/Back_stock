package itusolar.historique.dashboard.repartition;

import itusolar.historique.dashboard.evolution.EvolutionDash;
import itusolar.historique.dashboard.evolution.EvolutionDashManager;
import itusolar.historique.dashboard.evolution.EvolutionState;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.schedule.predict.SchedulePreparatorySignature;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RepartitionDashManager extends EvolutionDashManager {

    public RepartitionDashManager(SchedulePreparatorySignature schedulePreparatory, LoaderSignature loader) {
        super(schedulePreparatory, loader);
    }

    @Override
    public EvolutionDash dashContent(Date start, Date end, Connection connection) throws Exception {
        EvolutionDash response = super.dashContent(start, end, connection);
        return this.sum(response);
    }

    public EvolutionDash sum(EvolutionDash dash) {
        Map<String, EvolutionState> responses = new HashMap<>();
        for (EvolutionState state : dash.getEvolutionStates()) {
            String key = state.getType()+"";
            EvolutionState result = responses.get(key);
            if (result == null) {
                result = new EvolutionState();
                result.setType(state.getType());
                responses.put(key, result);
            }
            result.setValue(result.getValue()+state.getValue());
        }
        EvolutionState[] states = new EvolutionState[responses.size()];
        int[] index = {0};
        responses.forEach((key,value)-> {
            states[index[0]] = value;
            index[0]++;
        });
        return new EvolutionDash(states);
    }
}
