package itusolar.simulation.classification;

import itusolar.simulation.SimulData;

import java.util.Vector;

public class PlaceClassifier implements PlaceClassifierSignature{
    @Override
    public SimulData[][] split(SimulData[] datas) {
        Vector<Vector<SimulData>> dataSplits = new Vector<>();
        boolean temoin = true;
        Vector<SimulData> temp = new Vector<>();
        for (SimulData data : datas) {
            temoin = true;
            for (Vector<SimulData> dataSplit: dataSplits) {
                if (dataSplit.get(0).getSection() == data.getSection()) {
                    temp.add(data);
                    temoin = false;
                    break;
                }
            }
            if (temoin) {
                temp = new Vector<>();
                temp.add(data);
                dataSplits.add(temp);
            }
        }
        SimulData[][] result = new SimulData[dataSplits.size()][];
        int index = 0;
        for(Vector<SimulData> data : dataSplits) {
            result[index] = new SimulData[data.size()];
            data.copyInto(result[index]);
            index++;
        }
        return result;
    }
}
