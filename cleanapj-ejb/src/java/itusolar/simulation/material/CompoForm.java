package itusolar.simulation.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompoForm extends MaterialComposition {
    int checked;

    public CompoForm() {
        this.setNomTable("vmatComposition");
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}
