package itusolar.eltricSensor.log.mailing;

import itusolar.eltricSensor.ElectricSensor;

public class MailSensorConfiguration extends MailConfiguration {
    public MailSensorConfiguration(MailConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void init(MailSender sender) {
        StringBuilder body = new StringBuilder();
        body.append("<div>");
        int count = 0;
        for (ElectricSensor state : ((SensorMailSender) sender).getSensors()) {
            if ((count % 4) == 0) {
                body.append("<p>");
            } else
                body.append(", ");
            body.append(state.getLabel());
            count++;
            if ((count % 4) == 0)
                body.append("</p>");
        }
        if ((count % 4) != 0) {
            body.append("</p>");
        }
        body.append("</div>");
        this.setMsg(body.toString());
    }
}
