package itusolar.eltricSensor.stat.collector;

import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.log.ElectricLogMessage;

public class CollectedDatas {
    ElectricLogMessage[] messages;
    ElectricSensor[] sensors;

    public CollectedDatas(ElectricLogMessage[] messages) {
        this.messages = messages;
    }

    public ElectricSensor[] getSensors() {
        return sensors;
    }

    public void setSensors(ElectricSensor[] sensors) {
        this.sensors = sensors;
    }

    public ElectricLogMessage[] getMessages() {
        return messages;
    }

    public void setMessages(ElectricLogMessage[] messages) {
        this.messages = messages;
    }
}
