package itusolar.simulation.classification;

import itusolar.simulation.SimulData;
import itusolar.simulation.SourceSolaire;

public abstract class SimulDataMotherClassifier implements SimulDataMotherClassifierSignature {
    @Override
    public SimulData[] classify(SimulData[] datas) {
        int count = this.count(datas);
        SimulData[] result = this.initialize(count);
        int index = 0;
        for (SimulData data : datas) {
            if (this.isMine(data)) {
                result[index] = this.initialize(data);
                index++;
            }
        }
        return result;
    }

    @Override
    public int count(SimulData[] datas) {
        int count = 0;
        for (SimulData data : datas)
            count += this.isMine(data)? 1 : 0;
        return count;
    }

    public abstract SimulData initialize(SimulData data);

    public abstract SimulData[] initialize(int count);

    public abstract boolean isMine(SimulData data);
}
