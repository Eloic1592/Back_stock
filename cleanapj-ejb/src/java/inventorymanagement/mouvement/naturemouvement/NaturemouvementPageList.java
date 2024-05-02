package inventorymanagement.mouvement.naturemouvement;

import inventorymanagement.mouvement.mouvementphysique.totalarticleentree;
import inventorymanagement.mouvement.mouvementphysique.totalarticlesortie;

public class NaturemouvementPageList {
    Listenature [] naturemouvements;
    Statnaturemouvement [] statnaturemouvements;
    Cyclemouvement [] cyclemouvements;
    double totalentree;
    double totalsortie;
    totalarticlemouvement[] totalarticlemouvements;
    totalarticleentree[]  totalarticleentrees;
    totalarticlesortie[] totalarticlesorties;
    totalarticleentree  getcurrentsommearticleentree;
    totalarticlesortie getcurrentsommearticlesortie;

    public totalarticleentree[] getTotalarticleentrees() {
        return totalarticleentrees;
    }

    public void setTotalarticleentrees(totalarticleentree[] totalarticleentrees) {
        this.totalarticleentrees = totalarticleentrees;
    }

    public totalarticlesortie[] getTotalarticlesorties() {
        return totalarticlesorties;
    }

    public void setTotalarticlesorties(totalarticlesortie[] totalarticlesorties) {
        this.totalarticlesorties = totalarticlesorties;
    }

    public NaturemouvementPageList() {
    }

    public Listenature[] getNaturemouvements() {
        return naturemouvements;
    }

    public void setNaturemouvements(Listenature[] naturemouvements) {
        this.naturemouvements = naturemouvements;
    }

    public Statnaturemouvement[] getStatnaturemouvements() {
        return statnaturemouvements;
    }

    public void setStatnaturemouvements(Statnaturemouvement[] statnaturemouvements) {
        this.statnaturemouvements = statnaturemouvements;
    }

    public Cyclemouvement[] getCyclemouvements() {
        return cyclemouvements;
    }

    public void setCyclemouvements(Cyclemouvement[] cyclemouvements) {
        this.cyclemouvements = cyclemouvements;
    }

    public double getTotalentree() {
        return totalentree;
    }

    public void setTotalentree(double totalentree) {
        this.totalentree = totalentree;
    }

    public double getTotalsortie() {
        return totalsortie;
    }

    public void setTotalsortie(double totalsortie) {
        this.totalsortie = totalsortie;
    }

    public totalarticlemouvement[] getTotalarticlemouvements() {
        return totalarticlemouvements;
    }

    public void setTotalarticlemouvements(totalarticlemouvement[] totalarticlemouvements) {
        this.totalarticlemouvements = totalarticlemouvements;
    }

    public totalarticleentree getGetcurrentsommearticleentree() {
        return getcurrentsommearticleentree;
    }

    public void setGetcurrentsommearticleentree(totalarticleentree getcurrentsommearticleentree) {
        this.getcurrentsommearticleentree = getcurrentsommearticleentree;
    }

    public totalarticlesortie getGetcurrentsommearticlesortie() {
        return getcurrentsommearticlesortie;
    }

    public void setGetcurrentsommearticlesortie(totalarticlesortie getcurrentsommearticlesortie) {
        this.getcurrentsommearticlesortie = getcurrentsommearticlesortie;
    }
}
