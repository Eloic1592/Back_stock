package itusolar.simulation.filter;

import itusolar.simulation.SimulData;

public class DataIgnore implements DataIgnoreSignature {

    @Override
    public SimulData[] removeNotChanged(SimulData[] datas) {
        int notChanged = this.countNotSet(datas);
        SimulData[] result = new SimulData[datas.length-notChanged];
        int index = 0;
        for (SimulData data : datas) {
            if (data.isChanged() && data.isSet()) {
                result[index++] = data;
            }
        }
        return result;
    }


    private int countNotSet(SimulData[] datas) {
        int count = 0;
        for (SimulData data : datas)
            count += (data.isChanged() && data.isSet()) ? 0 : 1;
        return count;
    }
}
