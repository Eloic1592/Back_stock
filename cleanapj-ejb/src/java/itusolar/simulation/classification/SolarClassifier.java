package itusolar.simulation.classification;

import itusolar.simulation.SimulData;
import itusolar.simulation.SourceSolaire;

public class SolarClassifier extends SimulDataMotherClassifier{
    @Override
    public SimulData initialize(SimulData data) {
        return new SourceSolaire(data);
    }

    @Override
    public SimulData[] initialize(int count) {
        return new SourceSolaire[count];
    }

    @Override
    public boolean isMine(SimulData data) {
        return data.sourcedBySolaire();
    }
}
