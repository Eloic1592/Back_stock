package itusolar.simulation.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.UtilsSignature;

import java.sql.Connection;
import java.util.Arrays;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Material extends MappedInteger implements UtilsSignature {
    String titre;
    double consommation;
    Material[] appareils;
    MaterialComposition[] materialCompositions = {};
    boolean evaluated = false;

    @Override
    public String getLabel() {
        return String.format("%s %sW", this.getTitre(), this.getConsommation());
    }

    @Override
    public String toString() {
        return "Material{" +
                "titre='" + titre + '\'' +
                ", consommation=" + this.consommation +
//                ", appareils=" + Arrays.toString(appareils) +
//                ", materialCompositions=" + Arrays.toString(materialCompositions) +
                ", evaluated=" + evaluated +
                '}';
    }

    public Material() {
        this.setNomTable("appareil");
    }

    public Material(Material material) {
        this(material.getTitre(),
                material.getConsommation(),
                material.getAppareils(),
                material.getMaterialCompositions(),
                material.isEvaluated());
    }
    public Material(String titre, double consommation, Material[] appareils, MaterialComposition[] materialCompositions, boolean evaluated) {
        this();
        this.setTitre(titre);
        this.setConsommation(consommation);
        this.setAppareils(appareils);
        this.setMaterialCompositions(this.cloneMaterialCompositions(materialCompositions));
        this.setEvaluated(evaluated);
    }

    public MaterialComposition[] cloneMaterialCompositions(MaterialComposition[] compositions) {
        MaterialComposition[] results = new MaterialComposition[compositions.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = new MaterialComposition(compositions[i]);
        }
        return results;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqAppareil");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public MaterialComposition[] getMaterialCompositions() {
        return materialCompositions;
    }

    public void configConsommation(double consommation) throws NegativeConsumptionException {
        if (consommation < 0.)
            throw new NegativeConsumptionException();
        this.setConsommation(consommation);
    }
    public void setMaterialCompositions(MaterialComposition[] materialCompositions) {
        this.materialCompositions = materialCompositions;
    }

    public Material[] getAppareils() {
        return appareils;
    }

    public void setAppareils(Material[] appareils) {
        this.appareils = appareils;
    }


    public String getTitre() {
        return titre;
    }

    public double getConsommation() {
//        System.out.println(this.consommation);
        if (this.isEvaluated()) {
            return consommation;
        }
        this.setEvaluated(true);
        if (this.isComposed()) {
//            System.out.println("composed");
            double value = 0.;
            for (MaterialComposition materialComposition : this.getMaterialCompositions())
                value += materialComposition.getConsommation();
            this.setConsommation(value);
        }
        return consommation;
    }

    public boolean isComposed() {
        int count = 0;
        for (MaterialComposition material : this.getMaterialCompositions()) {
            if (material.isCheck())
                count++;
        }
        return count > 0;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }


    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }
}
