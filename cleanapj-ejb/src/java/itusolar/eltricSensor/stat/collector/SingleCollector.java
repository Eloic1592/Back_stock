package itusolar.eltricSensor.stat.collector;

import com.fasterxml.jackson.databind.ObjectMapper;
import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.log.ElectricLogMessage;
import itusolar.prepare.HServiceManager;
import itusolar.prepare.NoSuchFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class SingleCollector extends HServiceManager implements SingleCollectorSignature {
    @Override
    public CollectedDatas collect(ElectricSensor sensor, LocalDate date) throws IOException {
        Vector<ElectricLogMessage> datas = new Vector<>();
        try {
            this.readFile(sensor.getFileHandler(date), (bf) -> {
                try {
                    this.read(bf, datas);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (NoSuchFileException e) {
            return new CollectedDatas(new ElectricLogMessage[0]);
        }
        ElectricLogMessage[] responses = new ElectricLogMessage[datas.size()];
        datas.copyInto(responses);
        return new CollectedDatas(responses);
    }

    @Override
    public CollectedDatas collect(ElectricSensor sensor, LocalDate start, LocalDate end) throws DateNotNormalException, IOException {
        LocalDate[] dates = this.expand(start, end);
        Vector<ElectricLogMessage> datas = new Vector<>();
        for (LocalDate date : dates) {
            CollectedDatas data = this.collect(sensor, date);
            datas.addAll(Arrays.asList(data.getMessages()));
        }
        ElectricLogMessage[] responses = new ElectricLogMessage[datas.size()];
        datas.copyInto(responses);
        return new CollectedDatas(responses);
    }

    public LocalDate[] expand(LocalDate start, LocalDate end) throws DateNotNormalException {
        if (start.isAfter(end)) {
            throw new DateNotNormalException(start, end);
        }
        Vector<LocalDate> result = new Vector<>();
        while (!start.isAfter(end)) {
            result.add(start);
            start = start.plusDays(1);
        }
        LocalDate[] response = new LocalDate[result.size()];
        result.copyInto(response);
        return response;
    }

    public void read(BufferedReader bf, Vector<ElectricLogMessage> datas) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String line = null;
        while ((line = bf.readLine()) != null) {
            line = bf.readLine();
            line = line.split("INFOS:")[1];
            ElectricLogMessage data = this.toMessage(mapper, line);
            datas.add(data);
        }
    }

    public ElectricLogMessage toMessage(ObjectMapper mapper,String data) throws IOException {
        return mapper.readValue(data, ElectricLogMessage.class);
    }

}
