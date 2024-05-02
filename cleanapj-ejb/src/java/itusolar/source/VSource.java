package itusolar.source;

import itusolar.simulation.UtilsSignature;

public class VSource extends Source implements UtilsSignature {
    String type;
    String section;

    public VSource() {
        this.setNomTable("vsource");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String getLabel() {
        return this.getTitre()+ " "+ this.getSection();
    }
}
