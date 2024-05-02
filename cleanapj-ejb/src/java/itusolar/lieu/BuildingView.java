package itusolar.lieu;

public class BuildingView extends Building {
    String section;
    public BuildingView() {
        this.setNomTable("lieuList");
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
