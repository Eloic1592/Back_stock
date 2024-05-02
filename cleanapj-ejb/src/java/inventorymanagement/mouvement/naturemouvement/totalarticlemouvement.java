package inventorymanagement.mouvement.naturemouvement;

import itusolar.prepare.MappedInteger;

public class totalarticlemouvement extends MappedInteger {
    double total;
    String idnaturemouvement;
    String naturemouvement;

    public totalarticlemouvement() {
        setNomTable("total_article_mouvement");
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getIdnaturemouvement() {
        return idnaturemouvement;
    }

    public void setIdnaturemouvement(String idnaturemouvement) {
        this.idnaturemouvement = idnaturemouvement;
    }

    public String getNaturemouvement() {
        return naturemouvement;
    }

    public void setNaturemouvement(String naturemouvement) {
        this.naturemouvement = naturemouvement;
    }
}
