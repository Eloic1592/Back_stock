package itusolar.simulation.classification;

import itusolar.simulation.SimulData;

public class SimulDataClassifier implements  SimulDataClassifierSignature {
    SimulDataMotherClassifierSignature jiramaClassifier;
    SimulDataMotherClassifierSignature solarClassifier;

    public SimulDataClassifier(SimulDataMotherClassifierSignature jiramaClassifier, SimulDataMotherClassifierSignature solarClassifier) {
        this.jiramaClassifier = jiramaClassifier;
        this.solarClassifier = solarClassifier;
    }

    @Override
    public SimulData[] classifySolar(SimulData[] datas) {
        return this.solarClassifier.classify(datas);
    }

    @Override
    public SimulData[] classifyJirama(SimulData[] datas) {
        return this.jiramaClassifier.classify(datas);
    }
}
