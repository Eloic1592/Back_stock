package itusolar.jirama;

public class PageTarifResponse {
    Tarif tarif;

    Taxe[] taxes;
    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    public Taxe[] getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxe[] taxes) {
        this.taxes = taxes;
    }
}
