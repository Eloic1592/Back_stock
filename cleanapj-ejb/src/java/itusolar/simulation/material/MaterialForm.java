package itusolar.simulation.material;

public class MaterialForm {
    Material material;
    CompoForm[] others;

    public MaterialForm(Material material,CompoForm[] materials) {
        this.setMaterial(material);
        this.setOthers(materials);
    }

    public CompoForm[] getOthers() {
        return others;
    }

    public void setOthers(CompoForm[] others) {
        this.others = others;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
