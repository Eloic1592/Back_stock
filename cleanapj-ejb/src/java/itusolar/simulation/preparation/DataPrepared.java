package itusolar.simulation.preparation;

import itusolar.simulation.SimulData;

public class DataPrepared {

    SimulData[] jirama;
    SimulData[][] solaires;

    public DataPrepared(SimulData[] jirama, SimulData[][] solaires) {
        this.setJirama(jirama);
        this.setSolaires(solaires);
    }

    public SimulData[] getJirama() {
        return jirama;
    }

    public void setJirama(SimulData[] jirama) {
        this.jirama = jirama;
    }

    public SimulData[][] getSolaires() {
        return solaires;
    }

    public void setSolaires(SimulData[][] solaires) {
        this.solaires = solaires;
    }
}
