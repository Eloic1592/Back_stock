package itusolar.jirama;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.jirama.facture.DetailFacture;
import itusolar.jirama.facture.Facture;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.SimulData;

import java.sql.Connection;
import java.sql.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tarif extends MappedInteger {
    Date dateins;
    double compteur, valeur;

    Taxe[] taxes = new Taxe[0];

    public Tarif() {
        this.setNomTable("tarifjirama");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqTarifJIRAMA");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public Taxe[] getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxe[] taxes) {
        this.taxes = taxes;
    }

    public Date getDateins() {
        return dateins;
    }

    public void setDateins(Date dateins) {
        this.dateins = dateins;
    }

    public double getCompteur() {
        return compteur;
    }

    public void setCompteur(double compteur) {
        this.compteur = compteur;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public itusolar.jirama.facture.Facture toFacture(SimulData[] datas) {
        System.out.println("Jirama : ");
        for (SimulData data : datas) {
            System.out.println(data);
        }
        double conso = 0.;
        for (SimulData data : datas) {
            data.evalConsommation(-1);
            conso += data.getConsommation();
        }
        conso /= 1000.;
        System.out.println(conso);
        return this.toFacture(conso);
    }

    public itusolar.jirama.facture.Facture toFacture(double conso) {
        itusolar.jirama.facture.Facture facture = new Facture();
        facture.setTarif(this);
        facture.setConsommation(conso);
        facture.setTotalNTaxe(conso*this.getValeur());
        itusolar.jirama.facture.DetailFacture[] detailFactures = new DetailFacture[this.getTaxes().length];
        for (int i = 0; i < detailFactures.length; i++) {
            detailFactures[i] = this.getTaxes()[i].toDetailFacture(facture);
        }
        facture.setDetailFactures(detailFactures);
        return facture;
    }
}
