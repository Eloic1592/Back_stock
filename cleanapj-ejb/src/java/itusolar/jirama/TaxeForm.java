package itusolar.jirama;

public class TaxeForm {
    Taxe taxe;

    public TaxeForm(Taxe taxe) {
        this.setTaxe(taxe);
    }

    public Taxe getTaxe() {
        return taxe;
    }

    public void setTaxe(Taxe taxe) {
        this.taxe = taxe;
    }
}
