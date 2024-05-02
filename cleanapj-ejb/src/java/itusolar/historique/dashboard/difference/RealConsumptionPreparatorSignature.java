package itusolar.historique.dashboard.difference;

import itusolar.historique.dashboard.evolution.EvolutionParams;
import itusolar.historique.dashboard.evolution.EvolutionState;

import java.sql.Connection;

public interface RealConsumptionPreparatorSignature {
    public EvolutionState[] evaluate(EvolutionParams params, Connection connection) throws Exception;
}
