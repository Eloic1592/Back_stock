package itusolar.lieu;

public class BuildingList {
    itusolar.lieu.Building[] buildings;

    public BuildingList(itusolar.lieu.Building[] buildings) {
        this.buildings = buildings;
    }

    public itusolar.lieu.Building[] getBuildings() {
        return buildings;
    }

    public void setBuildings(Building[] buildings) {
        this.buildings = buildings;
    }
}
