package itusolar.simulation.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialComposition extends MappedInteger {
    int appareilid,membreid,mcid;
    double nombre;
    boolean check;
    Material material;

    @Override
    public String toString() {
        return "MaterialComposition{" +
                "appareilid=" + appareilid +
                ", membreid=" + membreid +
                ", mcid=" + mcid +
                ", nombre=" + nombre +
                ", check=" + check +
                ", material=" + material +
                '}';
    }

    public int getMcid() {
        return mcid;
    }

    public void setMcid(int mcid) {
        this.mcid = mcid;
    }

    public void setChecked(int value) {
        this.setCheck(value == 1);
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getMembreid() {
        return membreid;
    }

    public void setMembreid(int membreid) {
        this.membreid = membreid;
    }

    public MaterialComposition() {
        this.setNomTable("materialcomposition");
    }

    public MaterialComposition(MaterialComposition composition) {
        this();
        this.setAppareilid(composition.getAppareilid());
        this.setMembreid(composition.getAppareilid());
        this.setMcid(composition.getMcid());
        this.setNombre(composition.getNombre());
        this.setCheck(composition.isCheck());
        if (composition.getMaterial() != null)
            this.setMaterial(new Material(composition.getMaterial()));
    }

    public double getConsommation() {
        return this.getNombre() * this.getMaterial().getConsommation();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqMaterialComposition");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public int getAppareilid() {
        return appareilid;
    }

    public void setAppareilid(int appareilid) {
        this.appareilid = appareilid;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }
}
