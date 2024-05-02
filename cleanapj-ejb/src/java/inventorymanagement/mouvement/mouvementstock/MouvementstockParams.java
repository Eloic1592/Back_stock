package inventorymanagement.mouvement.mouvementstock;

public class MouvementstockParams {
    String idmouvementstock;
    String iddetailmouvementphysique;
    String iddetailmouvementfictif;
    String filename;


    public MouvementstockParams() {
    }

    public String getIdmouvementstock() {
        return idmouvementstock;
    }

    public void setIdmouvementstock(String idmouvementstock) {
        this.idmouvementstock = idmouvementstock;
    }

    public String getIddetailmouvementphysique() {
        return iddetailmouvementphysique;
    }

    public void setIddetailmouvementphysique(String iddetailmouvementphysique) {
        this.iddetailmouvementphysique = iddetailmouvementphysique;
    }

    public String getIddetailmouvementfictif() {
        return iddetailmouvementfictif;
    }

    public void setIddetailmouvementfictif(String iddetailmouvementfictif) {
        this.iddetailmouvementfictif = iddetailmouvementfictif;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
