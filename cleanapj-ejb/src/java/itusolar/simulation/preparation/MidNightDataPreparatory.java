package itusolar.simulation.preparation;

import itusolar.simulation.SimulData;
import itusolar.simulation.material.Material;
import itusolar.simulation.classification.PlaceClassifierSignature;
import itusolar.simulation.classification.SimulDataClassifierSignature;

public class MidNightDataPreparatory extends DataPreparator{

    public MidNightDataPreparatory(SimulDataClassifierSignature simulDataClassifier, PlaceClassifierSignature placeClassifier) {
        super(simulDataClassifier, placeClassifier);
    }

    @Override
    public SimulData[] sort(SimulData[] datas) {
        SimulData[] result = super.sort(datas);
        if (result.length == 0)
            return result;
        SimulData[] response = new SimulData[result.length+1];
        SimulData temp = new SimulData();
        temp.setAppareil(new Material());
        temp.setNb(0);
        temp.setDebut(new MidNightPreparatory(this).getDebut(datas));
        temp.setFin(datas[0].getDebut());
        temp.setSourceValue(SimulData.SOLAIRE);
        temp.setSection(datas[0].getSection());
        response[0] = temp;
        System.arraycopy(result, 0, response, 1, result.length);
        return response;
    }
}
