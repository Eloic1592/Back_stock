package itusolar.simulation.classification;

import itusolar.simulation.SimulData;
import itusolar.simulation.SourceJirama;

public class JiramaClassifier extends SimulDataMotherClassifier{
    @Override
    public SimulData initialize(SimulData data) {
        return new SourceJirama(data);
    }

    @Override
    public SimulData[] initialize(int count) {
        return new SourceJirama[count];
    }

    @Override
    public boolean isMine(SimulData data) {
        return data.sourcedByJirama();
    }
}
