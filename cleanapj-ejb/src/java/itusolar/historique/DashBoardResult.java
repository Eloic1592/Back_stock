package itusolar.historique;

import java.sql.Connection;

public class DashBoardResult {
    HistEnergyCalc[] histEnergy;
    ConsommationResult consommation;
    StockEnergy[] stockEnergy;
    int length;

    public void setHistEnergy(HistEnergyCalc[] histEnergy) {
        this.histEnergy = histEnergy;
    }

    public StockEnergy[] getStockEnergy() {
        return stockEnergy;
    }

    public void setStockEnergy(StockEnergy[] stockEnergy) {
        this.stockEnergy = stockEnergy;
    }

    public ConsommationResult getConsommation() {
        return consommation;
    }

    public void configHistEnergy(HistoryManager manager, HistEnergyCalc histEnergy, HistoriqueParams params, Connection c) throws Exception {
        this.setHistEnergy(manager.eval(histEnergy,params,c,""));
    }

    public void setConsommation(ConsommationResult consommation) {
        this.consommation = consommation;
    }

    public HistEnergyCalc[] getHistEnergy() {
        return histEnergy;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
