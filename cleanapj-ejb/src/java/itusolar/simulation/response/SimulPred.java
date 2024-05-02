package itusolar.simulation.response;

import itusolar.predict.etatconsommation.ConsumptionState;
import itusolar.predict.etatconsommation.Excess;
import itusolar.predict.historique.HistMean;
import itusolar.predict.PredicterStat;
import itusolar.jirama.facture.Facture;
import itusolar.jirama.Tarif;
import itusolar.lieu.Section;
import itusolar.simulation.DateConcern;
import itusolar.simulation.SimulData;
import itusolar.simulation.material.Material;
import itusolar.simulation.consumption.ConsumptionEvaluatorSignature;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;

public class SimulPred {
    SimulData[][] solaires;
    SimulData[] jirama;
    DateConcern[] dates;
    DateConcern[] meteos;
    HistMean[] histMeans;

    Section[] section;

    Facture facture;

    ConsumptionState[][] etatConsommations;

    PredicterStat[] statistics;

    ConsumptionEvaluatorSignature evaluator;

    int[] heur;

    double total, notSupported;

    public SimulData[][] getConso() {
        return this.evaluator.evaluate(this);
    }

    public void loadExcess() {
        ConsumptionState[][] results = this.getEtatConsommations();
        for (int i = 0; i < results.length; i++) {
            int[] excessList = this.filterExcess(results[i]);
            int excessCount = this.sum(excessList);
            ConsumptionState[] excess = new ConsumptionState[excessCount];
            ConsumptionState[] deficits = new ConsumptionState[excessList.length-excessCount];
            int eIndex = 0, dIndex = 0;
            for (int j = 0; j < excessList.length; j++) {
                if (excessList[j] == 1) {
                    excess[eIndex] = results[i][j];
                    eIndex++;
                } else {
                    deficits[dIndex] = results[i][j];
                    dIndex++;
                }
            }
            excess = this.loadExcess(excess);
            ConsumptionState[] response = new ConsumptionState[excess.length+deficits.length];
            int index = 0;
            for (ConsumptionState state : excess) {
                response[index] = state;
                index++;
            }
            for (ConsumptionState state : deficits) {
                response[index] = state;
                index++;
            }
            results[i] = response;
        }
        this.setEtatConsommations(results);
    }

    public int sum(int[] results) {
        int sum = 0;
        for ( int result : results ) {
            sum += result;
        }
        return sum;
    }

    public int[] filterExcess(ConsumptionState[] datas) {
        int[] results = new int[datas.length];
        for (int i = 0; i < datas.length; i++) {
            results[i] = datas[i] instanceof Excess ? 1 : 0;
        }
        return results;
    }

    public ConsumptionState[] loadExcess(ConsumptionState[] states) {
        Vector<ConsumptionState> result = new Vector<>();
        if (states != null && states.length > 0) {
            ConsumptionState temp = states[0];
            result.add(temp);
            for (int i = 1; i < states.length; i++) {
                if (temp.isContinue(states[i])) {
                    temp.setFin(states[i].getFin());
                    temp.setConso(temp.getConso()+states[i].getConso());
                } else {
                    temp = states[i];
                    result.add(temp);
                }
            }
        }
        ConsumptionState[] responses = new ConsumptionState[result.size()];
        result.copyInto(responses);
        return responses;
    }

    public ScheduleRenderingSignature[] getSchedules() {
        ScheduleRenderingSignature[] responses = new ScheduleRenderingSignature[this.count(this.getEtatConsommations())];
        int index = 0;
        for (ConsumptionState[] states : this.getEtatConsommations()) {
            for (ConsumptionState state : states) {
                responses[index] = state;
                index++;
            }
        }
        return responses;
    }

    public int count(ConsumptionState[][] states) {
        int result = 0;
        for (ConsumptionState[] state : states) {
            result += state.length;
        }
        return result;
    }


    public SimulData[] stack(SimulData[] datas) {
        SimulData[][] classified = this.classify(datas);
        SimulData[] result = new SimulData[classified.length];
        for (int i = 0; i < classified.length; i++) {
            result[i] = this.sum(classified[i]);
        }
        return result;
    }

    public SimulData sum(SimulData[] datas) {
        SimulData result = new SimulData();
        if (datas.length > 0) {
            result = new SimulData(datas[0]);
        }
        result.setAttachments(datas);
        double sum = 0.,vitesse = 0.;
        for (SimulData data : datas) {
            sum += data.getConsommation();
            vitesse+= data.getVitesse();
        }
        result.setConsommation(sum);
        if (result.getAppareil() == null) {
            result.setNb(1);
            Material appareil = new Material();
            appareil.setConsommation(vitesse);
            result.setAppareil(appareil);
        }
        return result;
    }

    public SimulData[][] classify(SimulData[] datas) {
        Vector<Vector<SimulData>> result = new Vector<>();
        Vector<SimulData> temp = null;
        boolean verif = true;
        boolean firstStep = false;
        for (SimulData data : datas) {
            if (!firstStep) {
                this.create(data,result);
                firstStep = true;
                continue;
            }
            verif = true;
            for (Vector<SimulData> res : result) {
                if (res.get(0).getHeur() == data.getHeur()) {
                    res.add(data);
                    verif = false;
                    break;
                }
            }
            if (verif) {
                this.create(data,result);
            }
        }
        SimulData[][] answer = new SimulData[result.size()][];
        for (int  i = 0; i < result.size(); i++) {
            answer[i] = new SimulData[result.get(i).size()];
            result.get(i).copyInto(answer[i]);
        }
        return answer;
    }

    public Vector<SimulData> create(SimulData data,Vector<Vector<SimulData>> result) {
        Vector<SimulData> temp = new Vector<>();
        temp.add(data);
        result.add(temp);
        return temp;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSupported() {
        return this.getTotal()-this.getNotSupported();
    }

    public double getNotSupported() {
        return notSupported;
    }

    public void setNotSupported(double notSupported) {
        this.notSupported = notSupported;
    }

    public PredicterStat[] getStatistics() {
        return statistics;
    }

    public int[] getHeur() {
        return heur;
    }

    public void setHeur(int[] heur) {
        this.heur = heur;
    }

    public void setStatistics(PredicterStat[] statistics) {
        this.statistics = statistics;
        double total = 0., sup = 0.;
        for (PredicterStat stat : this.getStatistics()) {
            stat.config(this.getEtatConsommations());
            total += stat.getConsommation();
            sup += stat.getConsoSup();
        }
        this.setTotal(total);
        this.setNotSupported(this.getTotal()-sup);
    }

    public ConsumptionState[][] getEtatConsommations() {
        return etatConsommations;
    }

    public void setEtatConsommations(ConsumptionState[][] etatConsommations) {
        this.etatConsommations = etatConsommations;
    }

    public HistMean[] getHistMeans() {
        return histMeans;
    }

    public void setHistMeans(HistMean[] histMeans) {
        this.histMeans = histMeans;
    }

    public SimulPred() {
    }

    public SimulPred(ConsumptionEvaluatorSignature consuptionEvaluator, SimulData[][] solaires, SimulData[] jirama, DateConcern[] dates, Tarif tarif) {
        this.evaluator = consuptionEvaluator;
        this.setSolaires(solaires);
        this.setJirama(jirama);
        this.setDates(dates);
        this.evaluer(tarif);
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    /**
     *
     * */
    public void evaluer(Tarif tarif) {
        this.setFacture(tarif.toFacture(this.getJirama()));
    }

    public SimulData[][] getSolaires() {
        return solaires;
    }

    public void setSolaires(SimulData[][] solaires) {
        this.solaires = solaires;
    }

    public SimulData[] getJirama() {
        return jirama;
    }

    public void setJirama(SimulData[] jirama) {
        this.jirama = jirama;
    }

    public DateConcern[] getDates() {
        return dates;
    }

    public void setDates(DateConcern[] dates) {
        this.dates = dates;
    }

    public Section[] getSection() {
        return section;
    }

    public void setSection(Section[] section) {
        this.section = section;
    }

    public void loadStat() throws Exception {
        this.setStatistics(new PredicterStat().toStat(this));
    }

    public void loadDates() {
        Vector<DateConcern> results = new Vector<>();
        int count = 0;
        double sum = 0.;
        Date dTemp = null;
        DateConcern[] dates = this.getDates();
        for (int i = 0; i < dates.length; i++) {
            DateConcern date = dates[i];
            boolean verif = false;
            if (dTemp == null) {
                dTemp = date.getDate();
            } else if (!this.compare(dTemp, date.getDate())) {
                results.add(this.toDate(dTemp,sum,count));
                dTemp = date.getDate();
                count = 1;
                sum = date.getTemperature();
                verif = true;
            }
            if (i == dates.length -1)
                results.add(this.toDate(dTemp,sum,count));
            if (verif)
                continue;
            sum += date.getTemperature();
            count++;
        }
        DateConcern[] answers = new DateConcern[results.size()];
        results.copyInto(answers);
        this.setMeteos(answers);
    }

    public DateConcern toDate(Date date, double temperature, int count) {
        temperature /= ((double) count);
        DateConcern newConcern = new DateConcern();
        newConcern.setDate(date);
        newConcern.setTemperature(temperature);
        return newConcern;
    }

    public boolean compare(Date date1, Date date2) {
        LocalDate d1 = date1.toLocalDate();
        LocalDate d2 = date2.toLocalDate();
        long dLong1 = (long) d1.getYear() *d1.getMonth().getValue() + d1.getDayOfMonth();
        long dLong2 = (long) d2.getYear() *d2.getMonth().getValue() + d2.getDayOfMonth();
        return dLong1 == dLong2;
    }

    public DateConcern[] getMeteos() {
        return meteos;
    }

    public void setMeteos(DateConcern[] meteos) {
        this.meteos = meteos;
    }
}
