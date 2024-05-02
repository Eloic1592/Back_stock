package inventorymanagement.depot;

public class DepotPageList {
    Depot[] depotlist;
    Stockarticledepot[] stockarticledepots;
    Stocktypematerieldepot [] stocktypematerieldepots;

    public DepotPageList() {
    }

    public DepotPageList(Depot[] depotlist) {
        this.depotlist = depotlist;
    }

    public Depot[] getDepotlist() {
        return depotlist;
    }

    public void setDepotlist(Depot[] depotlist) {
        this.depotlist = depotlist;
    }

    public Stockarticledepot[] getStockarticledepots() {
        return stockarticledepots;
    }

    public void setStockarticledepots(Stockarticledepot[] stockarticledepots) {
        this.stockarticledepots = stockarticledepots;
    }

    public Stocktypematerieldepot[] getStocktypematerieldepots() {
        return stocktypematerieldepots;
    }

    public void setStocktypematerieldepots(Stocktypematerieldepot[] stocktypematerieldepots) {
        this.stocktypematerieldepots = stocktypematerieldepots;
    }
}
