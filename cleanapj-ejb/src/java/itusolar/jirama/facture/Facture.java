package itusolar.jirama.facture;
import itusolar.jirama.Tarif;
import utilitaire.ChiffreLettre;

public class Facture {
    double totalNTaxe, consommation;
    double pTotal;
    Tarif tarif;
    DetailFacture[] detailFactures;
    ListeFacture[] listeFactures;

    public double getpTotal() {
        this.getRepresentation();
        return pTotal;
    }

    public String getPriceLiteral() {
        return ChiffreLettre.convertRealToString(this.getpTotal());
    }

    public void setpTotal(double pTotal) {
        this.pTotal = pTotal;
    }

    public double getSousTotal() {
        double result = this.getTotalNTaxe();
        for (DetailFacture detail : this.getDetailFactures()) {
            result += detail.getAmount();
        }
        return result;
    }

    public void initRepresentation() {
        ListeFacture[] consumptions = this.consoRepresentation();
        ListeFacture[] taxes = this.taxesRepresentation();
        ListeFacture[] result = new ListeFacture[consumptions.length+taxes.length+1];
        int index = this.addTo(result,0,consumptions);
        this.addTo(result,index,taxes);
        result[result.length-1] = this.sum(result);
        this.listeFactures = result;
    }

    public ListeFacture[] getRepresentation() {
        if (this.listeFactures == null)
            this.initRepresentation();
        return listeFactures;
    }

    public ListeFacture sum(ListeFacture[] listes) {
        double sum = 0.;
        for (ListeFacture li : listes)
            if (li != null && li.isTotal())
                sum += li.getMontant();
        this.setpTotal(sum);
        return new ListeFacture("Total (1)+(2) : ", "","",sum, true);
    }

    public int addTo(ListeFacture[] result, int index,ListeFacture[] datas) {
        for (ListeFacture data : datas ) {
            result[index] = data;
            index++;
        }
        return index;
    }

    public ListeFacture[] consoRepresentation() {
        String conso = ""+ this.getConsommation()+"Kw";
        String title = "Consommation";
        String pu = ""+this.getTarif().getValeur()+"Ariary/Kw";
        ListeFacture consumption = new ListeFacture(title,conso, pu,this.getTotalNTaxe(),false);
        ListeFacture[] result = new ListeFacture[] {consumption, null};
        result[1] = this.sum(result, result.length-1, "Total consommation (1) : ");
        return result;
    }

    public ListeFacture sum(ListeFacture[] datas,int offSet, String intitule) {
        double sum = 0.;
        for (int i = 0; i < datas.length && i < offSet; i++)
            sum += datas[i].getMontant();
        return new ListeFacture(intitule, "","",sum,true);
    }

    public ListeFacture[] taxesRepresentation() {
        int plus = 3;
        ListeFacture[] result = new ListeFacture[this.getDetailFactures().length+plus];
        result[0] = this.compteurRepresentation();
        result[1] = this.tvaRepresentation();
        this.addTo(result, plus-1, this.uniqueTaxesRepresentation());
        result[result.length-1] = this.sum(result, result.length-1, "Total taxe (2) :");
        return result;
    }

    public ListeFacture tvaRepresentation() {
        return new ListeFacture("TVA ","","",this.getTvaValue(),false);
    }


    public ListeFacture[] uniqueTaxesRepresentation() {
        ListeFacture[] result = new ListeFacture[this.getDetailFactures().length];
        int index = 0;
        for (DetailFacture detailFacture : this.getDetailFactures()) {
            String title = detailFacture.getTaxe().getTitre();
            double sum = detailFacture.getAmount();
            result[index] = new ListeFacture(title,"","",sum,false);
            index++;
        }
        return result;
    }

    public ListeFacture compteurRepresentation() {
        double nb = 1;
        double compteur = this.getTarif().getCompteur();
        String title = "Compteur";
        String quantity = ""+nb;
        String pu = ""+compteur+"Ariary" ;
        double sum = compteur*nb;
        return new ListeFacture(title, quantity,pu,sum,false);
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public double getTotal() {
        return this.getSousTotal() + this.getTvaValue();
    }

    public double getTvaValue() {
        return this.tva(this.getTotalNTaxe());
    }

    public double tva(double amount) {
        return amount * 0.2;
    }

    public double getTotalNTaxe() {
        return totalNTaxe;
    }

    public void setTotalNTaxe(double totalNTaxe) {
        this.totalNTaxe = totalNTaxe;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    public DetailFacture[] getDetailFactures() {
        return detailFactures;
    }

    public void setDetailFactures(DetailFacture[] detailFactures) {
        this.detailFactures = detailFactures;
    }
}
