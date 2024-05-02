package itusolar.simulation.preparation;

import itusolar.simulation.material.Material;
import itusolar.simulation.SimulData;
import itusolar.simulation.classification.PlaceClassifierSignature;
import itusolar.simulation.classification.SimulDataClassifierSignature;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

public class DataPreparator implements DataPreparatorSignature, Comparator<SimulData> {
    SimulDataClassifierSignature simulDataClassifier;
    PlaceClassifierSignature placeClassifier;

    public DataPreparator(SimulDataClassifierSignature simulDataClassifier, PlaceClassifierSignature placeClassifier) {
        this.simulDataClassifier = simulDataClassifier;
        this.placeClassifier = placeClassifier;
    }

    @Override
    public DataPrepared prepare(SimulData[] datas) {
        SimulData[] jirama = this.simulDataClassifier.classifyJirama(datas);
        SimulData[] solar = this.simulDataClassifier.classifySolar(datas);
        SimulData[][] dataSpliteds = this.placeClassifier.split(solar);
        int index = 0;
        for (SimulData[] dataSplited : dataSpliteds) {
            dataSpliteds[index] = this.sort(dataSplited);
            dataSpliteds[index] = this.splitConso(dataSpliteds[index]);
            dataSpliteds[index] = this.splitHours(dataSpliteds[index]);
            index++;
        }
        return new DataPrepared(jirama, dataSpliteds);
    }

    public SimulData[] splitHours(SimulData[] datas) {
        datas = this.completeVoid(datas);
        Vector<SimulData> resultTemp = new Vector<>();
        SimulData temp = null;
        for (SimulData data : datas) {
            Timestamp[] intervals = this.splitHours(data.getDebut(),data.getFin());
            for (int i = 0; i < intervals.length-1; i++) {
                temp = new SimulData(data);
                temp.setDebut(intervals[i]);
                temp.setFin(intervals[i+1]);
                resultTemp.add(temp);
            }
        }
        return this.extract(resultTemp);
    }

    public SimulData[] extract(Vector<SimulData> datas) {
        SimulData[] answer = new SimulData[datas.size()];
        datas.copyInto(answer);
        return answer;
    }

    @Override
    public Timestamp[] splitHours(Timestamp start, Timestamp end) {
        Timestamp startTemp = new Timestamp(start.getTime());
        this.reset(startTemp);
        Timestamp endTemp = new Timestamp(end.getTime());
        if (endTemp.getMinutes() > 0)
            endTemp.setHours(endTemp.getHours()+1);
        this.reset(endTemp);
        int oneHour = 3600000;
        long diff = endTemp.getTime() - startTemp.getTime();
        int count = (int) (diff / oneHour) +1;
        Timestamp[] result = new Timestamp[count];
        long startValue = startTemp.getTime();
        for (int i = 0; i < count; i++) {
            result[i] = new Timestamp(startValue);
            startValue += oneHour;
        }
        result[0] = start;
        result[result.length-1] = end;
        return result;
    }

    public void reset(Timestamp time) {
        time.setMinutes(0);
        time.setSeconds(0);
        time.setNanos(0);
    }

    public SimulData[] completeVoid(SimulData[] datas) {

        if (!(datas != null && datas.length > 0))
            return datas;
        Vector<SimulData> stockTemp = new Vector<>();
        stockTemp.add(datas[0]);
        for (int i = 1; i < datas.length; i++) {
            Timestamp finPrecedent = datas[i-1].getFin(),debutActual = datas[i].getDebut();
            if (finPrecedent.getTime() < debutActual.getTime()) {
                SimulData data = new SimulData();
                Material appareil = new Material();
                data.setAppareil(appareil);
                data.setDebut(finPrecedent);
                data.setFin(debutActual);
                data.setAttachments(new SimulData[0]);
                stockTemp.add(data);
            }
            stockTemp.add(datas[i]);
        }
        return this.extract(stockTemp);
    }

    public SimulData[] sort(SimulData[] datas) {
        List<SimulData> result = Arrays.asList(datas);
        result.sort(this);
        return result.toArray(new SimulData[0]);
    }


    public SimulData[] splitConso(SimulData[] data) {
        if (data.length == 0)
            return new SimulData[0];
        Vector<SimulData> result = new Vector<>();
        long[] points = this.filterPoints(data);
        this.splitConso(result,data,0,points);
        return this.extract(result);
    }

    public void splitConso(Vector<SimulData> results,SimulData[] data,int index,long [] points) {
        long start = points[index];
        SimulData[] attachments = this.hasInterval(data,start);
        SimulData result = new SimulData();
        result.setDebut(new Timestamp(start));
        index++;
        result.setFin(new Timestamp(points[index]));
        result.setAttachments(attachments);
        if (attachments.length > 0) {
            result.setLieux(attachments[0].getLieux());
            result.setSection(attachments[0].getSection());
        } else {
            Material appareil = new Material();
            appareil.setConsommation(0);
            result.setNb(1);
            result.setAppareil(appareil);
            if (data.length > 0) {
                result.setLieux(data[0].getLieux());
                result.setSection(data[0].getSection());
            }
        }
        results.add(result);
        if (index < points.length-1)
            this.splitConso(results,data,index,points);
    }

    public long[] filterPoints(SimulData[] datas) {
        long[] points = new long[datas.length*2];
        for (int i = 0; i < datas.length; i++) {
            points[(i*2)] = datas[i].getDebut().getTime();
            points[(i*2)+1] = datas[i].getFin().getTime();
        }
        Arrays.sort(points);
        return points;
    }

    public SimulData[] hasInterval(SimulData[] datas,long start) {
        Vector<SimulData> result = new Vector<>();
        for (SimulData data : datas)
            if (data.hasInterval(start))
                result.add(data);
        return this.extract(result);
    }

    @Override
    public int compare(SimulData o1, SimulData o2) {
        Logger logger = Logger.getLogger("DataComparator");
        logger.info(o1.toString());
        logger.info(o2.toString());
        return Long.compare( o1.getDebut().getTime(),o2.getDebut().getTime());
    }

}
