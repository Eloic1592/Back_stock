package inventorymanagement.mouvement.mouvementstock;


import inventorymanagement.article.Article;
import inventorymanagement.article.Listearticle;
import inventorymanagement.depot.Depot;
import inventorymanagement.etudiant.ListeEtudiant;
import inventorymanagement.materiel.materiel.Listemateriel;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictif;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictifview;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysiqueview;
import inventorymanagement.mouvement.mouvementphysique.totalarticleentree;
import inventorymanagement.mouvement.mouvementphysique.totalarticlesortie;
import inventorymanagement.mouvement.naturemouvement.Naturemouvement;

public class MouvementstockPageList {
    Mouvementstockview[] mouvementStocks;
    Mouvementphysiqueview[] mouvementphysiques;
    Mouvementfictifview[] mouvementfictifs;
    Naturemouvement[] naturemouvements;
    Depot[] depots;
    Listearticle[] listearticles;
    Article[] articles;
    Listemateriel[] listemateriels;
    ListeEtudiant[] etudiants;

    Mouvementphysiqueview mouvementphysique;
    Mouvementfictif mouvementfictif;
    Mouvementstockview mouvementstock;


    public MouvementstockPageList() {
    }

    public Mouvementstockview[] getMouvementStocks() {
        return mouvementStocks;
    }

    public void setMouvementStocks(Mouvementstockview[] mouvementStocks) {
        this.mouvementStocks = mouvementStocks;
    }

    public Mouvementphysiqueview[] getMouvementphysiques() {
        return mouvementphysiques;
    }

    public void setMouvementphysiques(Mouvementphysiqueview[] mouvementphysiques) {
        this.mouvementphysiques = mouvementphysiques;
    }

    public Mouvementfictifview[] getMouvementfictifs() {
        return mouvementfictifs;
    }

    public void setMouvementfictifs(Mouvementfictifview[] mouvementfictifs) {
        this.mouvementfictifs = mouvementfictifs;
    }

    public Naturemouvement[] getNaturemouvements() {
        return naturemouvements;
    }

    public void setNaturemouvements(Naturemouvement[] naturemouvements) {
        this.naturemouvements = naturemouvements;
    }


    public Listemateriel[] getListemateriels() {
        return listemateriels;
    }

    public void setListemateriels(Listemateriel[] listemateriels) {
        this.listemateriels = listemateriels;
    }

    public Depot[] getDepots() {
        return depots;
    }

    public void setDepots(Depot[] depots) {
        this.depots = depots;
    }

    public Listearticle[] getArticles() {
        return listearticles;
    }

    public Listearticle[] getListearticles() {
        return listearticles;
    }

    public void setListearticles(Listearticle[] listearticles) {
        this.listearticles = listearticles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public ListeEtudiant[] getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(ListeEtudiant[] etudiants) {
        this.etudiants = etudiants;
    }

    public Mouvementphysiqueview getMouvementphysique() {
        return mouvementphysique;
    }

    public void setMouvementphysique(Mouvementphysiqueview mouvementphysique) {
        this.mouvementphysique = mouvementphysique;
    }

    public Mouvementfictif getMouvementfictif() {
        return mouvementfictif;
    }

    public void setMouvementfictif(Mouvementfictif mouvementfictif) {
        this.mouvementfictif = mouvementfictif;
    }

    public Mouvementstock getMouvementstock() {
        return mouvementstock;
    }

    public void setMouvementstock(Mouvementstockview mouvementstock) {
        this.mouvementstock = mouvementstock;
    }


}
