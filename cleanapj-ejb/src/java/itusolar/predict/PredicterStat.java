package itusolar.predict;

import itusolar.predict.etatconsommation.Deficit;
import itusolar.predict.etatconsommation.ConsumptionState;
import itusolar.predict.historique.HistMean;
import itusolar.lieu.Section;
import itusolar.simulation.material.Material;
import itusolar.simulation.SimulData;
import itusolar.simulation.response.SimulPred;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;

public class PredicterStat {
    Section section;
    Date date;
    int heur;
    double production,consommation,reste,consoSup;

    SimulData concern;

    public PredicterStat() {
    }

    public PredicterStat(SimulData concern,Section section, Date date, int heur, double production, double reste) {
        this.setSection(section);
        this.setDate(date);
        this.setProduction(production);
        this.setReste(reste);
        this.setHeur(heur);
        this.setConcern(concern);
    }

    public void config(ConsumptionState[][] etatConsommations) {
        // TODO Auto
        double notSup = 0.;
        for (ConsumptionState[] etats : etatConsommations) {
            for (ConsumptionState etat : etats) {
                String fDate = String.format("%s-%s-%s-%s", etat.getDebut().getYear(),etat.getDebut().getMonth(),etat.getDebut().getDay(),etat.getDebut().getHours());
                String sDate = String.format("%s-%s-%s-%s", this.getDate().getYear(),this.getDate().getMonth(),this.getDate().getDay(),this.getHeur());
                if (etat instanceof Deficit && fDate.compareTo(sDate) == 0 && etat.getSection() == this.getSection().getId()) {
                    SimulData data = new SimulData();
                    data.setAppareil(new Material());
                    data.setNb(1);
                    data.getAppareil().setConsommation(etat.getValue());
                    data.setDebut(etat.getDebut());
                    data.setFin(etat.getFin());
                    data.evalConsommation(-1);
                    notSup += data.getConsommation();
                }
            }
        }
        this.setConsoSup(this.getConsommation()-notSup);
        // Alaina le etat deficit tafiditra am le date sy heure
        // calculena le valeur tsy tafa
        // consup = consommation - valeur tsy tafa
    }

    public PredicterStat[] toStat(SimulPred prediction) throws Exception {
        Vector<PredicterStat> result = new Vector<>();
        int index = 0;
        SimulData[][] conso = prediction.getConso();
        for ( itusolar.predict.historique.HistMean hist : prediction.getHistMeans()) {
            SimulData data = null;
            try {
                data = this.filter(conso,hist);
            }catch (Exception e) {
                continue;
            }
            Section section = this.getById(prediction.getSection(),hist.getSection());
            PredicterStat stat = new PredicterStat(data,section,hist.getDate(),hist.getHeure(),hist.getPuissanceentree(),hist.getReste());
            result.add(stat);
        }
        PredicterStat[] temp = new PredicterStat[result.size()];
        result.copyInto(temp);
        return temp;
    }

    public Section getById(Section[] sections,int sectionId) throws Exception {
        for(Section section : sections)
            if (section.getId() == sectionId)
                return section;
        throw new Exception("Not found section");
    }

    public SimulData filter(SimulData[][] consommations, HistMean hist) throws Exception {
        for (SimulData[] consommation : consommations) {
            for (SimulData conso : consommation) {
                if (this.compare(conso, hist)) {
                    return conso;
                }
            }
        }
        throw new Exception("Invalid Consommation");
    }

    public boolean compare(SimulData conso, HistMean hist) {
        boolean verif = conso.getSection() == hist.getSection();
        verif &= conso.getHeur() == hist.getHeure();
        LocalDate hDate = hist.getDate().toLocalDate();
        LocalDate cDate = conso.getDebut().toLocalDateTime().toLocalDate();
        verif &= cDate.getYear() == hDate.getYear();
        verif &= cDate.getMonth().getValue() == hDate.getMonth().getValue();
        verif &= cDate.getDayOfMonth() == hDate.getDayOfMonth();
        return verif;
    }

    public SimulData getConcern() {
        return concern;
    }

    public void setConcern(SimulData concern) {
        this.concern = concern;
        this.setConsommation(concern.getConsommation());
    }

    public int getHeur() {
        return heur;
    }

    public void setHeur(int heur) {
        this.heur = heur;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public double getProduction() {
        return production;
    }


    public void setProduction(double production) {
        this.production = production;
    }

    public double getConsoSup() {
        return consoSup;
    }

    public void setConsoSup(double consoSup) {
        this.consoSup = consoSup;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }
}
