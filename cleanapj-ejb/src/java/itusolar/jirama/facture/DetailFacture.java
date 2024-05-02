package itusolar.jirama.facture;

import itusolar.jirama.Taxe;

public class DetailFacture {
    Taxe taxe;
    double amount;

    public Taxe getTaxe() {
        return taxe;
    }

    public void setTaxe(Taxe taxe) {
        this.taxe = taxe;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
