package itusolar.historique.dashboard.section;

public class InverterDash {
    InverterState[] sections;

    public InverterDash(InverterState[] sections) {
        this.setSections(sections);
    }

    public InverterState[] getSections() {
        return sections;
    }

    public void setSections(InverterState[] sections) {
        this.sections = sections;
    }
}