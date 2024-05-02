package itusolar.historique;

import java.sql.Date;

public class HistEnergyCalc extends HistEnergy{
    int sc, mn, hr, dy, mt, yr,lieuid,section;
    Double puissanceentree, puissancesortie;
    Double pConsomme, pProduite,predict;
    String titre,type;

    @Override
    public String toString() {
        return "HistEnergyCalc{" +
                "sc=" + sc +
                ", mn=" + mn +
                ", hr=" + hr +
                ", dy=" + dy +
                ", mt=" + mt +
                ", yr=" + yr +
                ", lieuid=" + lieuid +
                ", section=" + section +
                ", puissanceentree=" + puissanceentree +
                ", puissancesortie=" + puissancesortie +
                ", pConsomme=" + pConsomme +
                ", pProduite=" + pProduite +
                ", predict=" + predict +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public Integer getLieuid() {
        return lieuid;
    }

    public void setLieuid(Integer lieuid) {
        this.lieuid = lieuid;
    }

    public Date createDate() {
        String date = "%s-%s-%s";
        date = (String.format(date, this.getYr(),this.getMt(),this.getDy()));
        return Date.valueOf(date);
    }

    public void rapport() {
        this.setPredict(this.getPuissanceentree()/this.getTemperature());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Double getPredict() {
        return predict;
    }

    public void setPredict(Double predict) {
        this.predict = predict;
    }

    @Override
    public void setDuree(double duree) {
        super.setDuree(duree);
        double pProdPerMin = this.toWaltPerSecond(this.getPuissanceentree());
        this.setpProduite(pProdPerMin*this.getDuree());
        double pConsPerMin = this.toWaltPerSecond(this.getPuissancesortie());
        this.setpConsomme(pConsPerMin*this.getDuree());
    }

    public HistEnergyCalc() {
        this("Histpage");
    }
    public HistEnergyCalc(String tableName) {
        this.setNomTable(tableName);
    }
    public Integer getSc() {
        return sc;
    }

    public void setSc(Integer sc) {
        this.sc = sc;
    }

    public Integer getMn() {
        return mn;
    }

    public void setMn(Integer mn) {
        this.mn = mn;
    }

    public Integer getHr() {
        return hr;
    }

    public void setHr(Integer hr) {
        this.hr = hr;
    }

    public Integer getDy() {
        return dy;
    }

    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Integer getMt() {
        return mt;
    }

    public void setMt(Integer mt) {
        this.mt = mt;
    }

    public Integer getYr() {
        return yr;
    }

    public void setYr(Integer yr) {
        this.yr = yr;
    }

    public Double getPuissanceentree() {
        return puissanceentree;
    }

    public void setPuissanceentree(Double puissanceentree) {
        this.puissanceentree = puissanceentree;
    }

    public Double getPuissancesortie() {
        return puissancesortie;
    }

    public void setPuissancesortie(Double puissancesortie) {
        this.puissancesortie = puissancesortie;
    }

    public Double getpConsomme() {
        return pConsomme;
    }

    public void setpConsomme(Double pConsomme) {
        this.pConsomme = pConsomme;
    }

    public Double getpProduite() {
        return pProduite;
    }

    public void setpProduite(Double pProduite) {
        this.pProduite = pProduite;
    }
}
