package itusolar.historique.dashboard.difference;

import itusolar.historique.dashboard.evolution.EvolutionDash;
import itusolar.historique.dashboard.evolution.EvolutionState;

public class ComparedDash extends EvolutionDash {
    EvolutionState[] real;
    public ComparedDash(EvolutionState[] evolutionStates,EvolutionState[] real) {
        super(evolutionStates);
        this.setReal(real);
    }

    public EvolutionState[] getReal() {
        return real;
    }

    public void setReal(EvolutionState[] real) {
        this.real = real;
    }
}
