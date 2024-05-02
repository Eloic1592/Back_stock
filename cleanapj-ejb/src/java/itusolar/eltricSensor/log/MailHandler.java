package itusolar.eltricSensor.log;

import itusolar.eltricSensor.ElectricAccessor;
import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.log.mailing.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MailHandler extends ElectricHandler {

    public static int timeAlert = 10;
    MailAccessorSignature accessor;
    Map<String, MailSender> mailer = new HashMap<>();
    Map<String, ElectricState> problems = new HashMap<>();
    MailAddress[] addresses;

    public MailHandler(ElectricState[] states, MailAccessorSignature accessor, Connection connection) {
        super(states, null, connection);
        this.setAccessor(accessor);
    }

    @Override
    public void listen() throws Exception {
        Vector<ElectricState> results = new Vector<>();
        for(ElectricState state : this.getStates()) {
            if (!state.isOpen(MailHandler.timeAlert)) {
                results.add(state);
            } else
                this.getProblems().remove(state.getKey());
        }
        this.sendMessage(results);
    }

    public Map<String, ElectricState> getProblems() {
        return problems;
    }

    public void setProblems(Map<String, ElectricState> problems) {
        this.problems = problems;
    }

    public void sendMessage(Vector<ElectricState> results) throws Exception {
        if (results.size() > 0) {
            boolean verif = false;
            for (ElectricState state : results) {
                System.out.println(state.getMacAddress());
                if (this.getProblems().get(state.getKey()) == null) {
                    this.getProblems().put(state.getKey(), state);
                    verif = true;
                }
            }
            
            if (verif) {
                ElectricState[] states = new ElectricState[results.size()];
                results.copyInto(states);
                this.sendMessage(states);
            }
        }
    }

    public void sendMessage(ElectricState[] state) throws Exception {
        MailSender sender = this.getSender(state);
        sender.send();
    }

    public MailSender getSender(ElectricState[] states) throws Exception {
        if (this.getAddresses() == null) {
            MailAddress[] addresses = this.getAccessor().search(new MailAddress(), "", connection);
            this.setAddresses(addresses);
        }
        return this.toMailSender(states);
    }

    public MailSender toMailSender(ElectricState[] states) throws NoToAddressException, NoFromMailException {
        MailSender sender = this.getMailer().get(this.getKey());
        if (sender == null) {
            MailConfiguration configuration = this.getAccessor().prepare(this.getAddresses());
            MailSensorConfiguration sensorConfiguration = new MailSensorConfiguration(configuration);
            sender = new SensorMailSender(sensorConfiguration, states);
            this.getMailer().put(this.getKey(), sender);
        } else {
            ((SensorMailSender) sender).setSensors(states);
        }
        return sender;
    }

    public String getKey() {
        return "key";
    }

    public MailAddress[] getAddresses() {
        return addresses;
    }

    public void setAddresses(MailAddress[] addresses) {
        this.addresses = addresses;
    }

    public Map<String, MailSender> getMailer() {
        return mailer;
    }

    public void setMailer(Map<String, MailSender> mailer) {
        this.mailer = mailer;
    }



    public MailAccessorSignature getAccessor() {
        return accessor;
    }

    public void setAccessor(MailAccessorSignature accessor) {
        this.accessor = accessor;
    }
}
