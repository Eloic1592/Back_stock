package itusolar.eltricSensor.log.mailing;

import itusolar.eltricSensor.ElectricSensor;

public class SensorMailSender extends MailSender{

    ElectricSensor[] sensors;
    public SensorMailSender(MailConfiguration configuration, ElectricSensor[] sensors) {
        super(configuration);
        this.setSensors(sensors);

    }


    public ElectricSensor[] getSensors() {
        return sensors;
    }

    public void setSensors(ElectricSensor[] sensor) {
        this.sensors = sensor;
    }
}
