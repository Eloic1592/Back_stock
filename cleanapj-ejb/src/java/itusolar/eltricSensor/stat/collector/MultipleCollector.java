package itusolar.eltricSensor.stat.collector;

import itusolar.eltricSensor.ElectricAccessor;
import itusolar.eltricSensor.ElectricAccessorSignature;
import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.log.ElectricLogMessage;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

public class MultipleCollector extends SingleCollector implements MultipleCollectorSignature{

    ElectricAccessorSignature accessor;

    public MultipleCollector(ElectricAccessorSignature accessor) {
        this.setAccessor(accessor);
    }

    @Override
    public CollectedDatas collect(LocalDate start, LocalDate end, Connection connection) throws Exception {
        return this.collect(this.getAccessor().getAll(new ElectricState(),connection), start, end);
    }

    @Override
    public CollectedDatas collect(Connection connection) throws Exception {
        return this.collect(this.getAccessor().getAll(new ElectricState(),connection));
    }

    @Override
    public CollectedDatas collect(ElectricSensor[] sensors) throws DateNotNormalException, IOException {
        return this.collect(sensors, LocalDate.now(), LocalDate.now());
    }

    @Override
    public CollectedDatas collect(ElectricSensor[] sensors, LocalDate start, LocalDate end) throws DateNotNormalException, IOException {
        Vector<ElectricLogMessage> datas = new Vector<>();
        for (ElectricSensor sensor : sensors) {
            CollectedDatas data = this.collect(sensor, start, end);
            datas.addAll(Arrays.asList(data.getMessages()));
        }
        ElectricLogMessage[] messages = new ElectricLogMessage[datas.size()];
        datas.copyInto(messages);
        CollectedDatas response = new CollectedDatas(messages);
        response.setSensors(sensors);
        return response;
    }

    public ElectricAccessorSignature getAccessor() {
        return accessor;
    }

    public void setAccessor(ElectricAccessorSignature accessor) {
        this.accessor = accessor;
    }
}
