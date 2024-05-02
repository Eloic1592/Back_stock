package itusolar.jirama;

public class TarifForm {
    Tarif tarif;

    public TarifForm() {
    }

    public TarifForm(Tarif tarif) {
        this.setTarif(tarif);
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
}
