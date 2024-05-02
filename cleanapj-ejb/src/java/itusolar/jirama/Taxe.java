package itusolar.jirama;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.jirama.facture.DetailFacture;
import itusolar.jirama.facture.Facture;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Taxe extends MappedInteger {
    String titre;
    double pourcentage;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Taxe() {
        this.setNomTable("taxejirama");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSEQTAXEJIRAMA");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public itusolar.jirama.facture.DetailFacture toDetailFacture(Facture facture) {
        itusolar.jirama.facture.DetailFacture result = new DetailFacture();
        result.setTaxe(this);
        result.setAmount(facture.getTotalNTaxe() * this.getPourcentage()/100.);
        return result;
    }
}
