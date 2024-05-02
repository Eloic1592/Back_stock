package itusolar.lieu;

public class BuildingForm {
    Building building;
    Section[] sections;

    public BuildingForm(Building building, Section[] sections) {
        this.setBuilding(building);
        this.setSections(sections);
    }

    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
