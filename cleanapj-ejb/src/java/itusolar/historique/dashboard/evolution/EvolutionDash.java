package itusolar.historique.dashboard.evolution;

import java.util.Arrays;

public class EvolutionDash {
    EvolutionState[] evolutionStates;

    public EvolutionDash(EvolutionState[] evolutionStates) {
        this.setEvolutionStates(evolutionStates);
    }

    public EvolutionState[] getEvolutionStates() {
        return evolutionStates;
    }

    public void setEvolutionStates(EvolutionState[] evolutionStates) {
        Arrays.sort(evolutionStates);
        this.evolutionStates = evolutionStates;
    }
}
