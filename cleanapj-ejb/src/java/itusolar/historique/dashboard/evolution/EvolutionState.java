package itusolar.historique.dashboard.evolution;

import itusolar.simulation.SimulData;

public class EvolutionState implements Comparable<EvolutionState> {
    String index;
    String label;
    double value;
    int type, section, order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        if (type == SimulData.SOLAIRE)
            this.setLabel("Solaire");
        else
            this.setLabel("JIRAMA");
    }

    @Override
    public int compareTo(EvolutionState o) {
        return this.getOrder()-o.getOrder();
    }
}
