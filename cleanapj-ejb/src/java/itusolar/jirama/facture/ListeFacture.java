package itusolar.jirama.facture;

public class ListeFacture {
    String intitile, quantite, pu;
    double montant;
    boolean total;

    public ListeFacture() {
    }

    public ListeFacture(String intitile, String quantite, String pu, double montant, boolean total) {
        this.setIntitile(intitile);
        this.setQuantite(quantite);
        this.setPu(pu);
        this.setMontant(montant);
        this.setTotal(total);
    }

    public String getIntitile() {
        return intitile;
    }

    public void setIntitile(String intitile) {
        this.intitile = intitile;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public boolean isTotal() {
        return total;
    }

    public void setTotal(boolean total) {
        this.total = total;
    }
}
