package itusolar.historique.wait;

import itusolar.eltricSensor.ElectricAccessorSignature;
import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.beeper.Beeper;
import itusolar.eltricSensor.call.ElectricCallMethod;
import itusolar.eltricSensor.log.ElectricHandler;
import itusolar.eltricSensor.log.MailHandler;
import itusolar.eltricSensor.log.mailing.MailAccessorSignature;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ElectricWaitMethod extends ElectricCallMethod implements ElectricWaitMethodSignature {
    Map<String, itusolar.historique.wait.ElectricWaitState> electricStates = new HashMap<>();

    public ElectricWaitMethod(ElectricAccessorSignature accessor) {
        super(null, accessor);
        this.setElectricStates(new HashMap<>());
    }

    @Override
    public ElectricState[] evaluate(Connection connection) throws Exception {
//        connection = this.getConnection(connection);
        this.initStates(null,connection);
        return this.getStates();
    }

    @Override
    public void load(WaitParams params) throws Exception {
//        System.out.println(params);
        this.initStates(null,null);
        itusolar.historique.wait.ElectricWaitState state = this.getElectricStates().get(this.key(params.getMacAddress()));
        state.reload();
    }

    @Override
    public void start(MailAccessorSignature mailAccessor, Connection connection) throws Exception {
        ElectricState[] beepers = this.getAccessor().getAll(new Beeper(), connection);
        this.initStates(null, connection);
        ElectricHandler handler = new ElectricHandler(this.getStates(),beepers, this.getConnection(null));
        Thread th = new Thread(handler);
        th.start();
        handler = new MailHandler(this.getStates(), mailAccessor, this.getConnection(null));
        th = new Thread(handler);
        th.start();
    }

    public String key(String key) {
        return key.toLowerCase();
    }

    @Override
    public void initStates(ElectricSensor sensor, Connection connection) throws Exception {
        boolean verif = this.getStates() == null;
        if (verif) {
            connection = this.getConnection(connection);
            super.initStates(new itusolar.historique.wait.ElectricWaitState(),connection);
            this.addToMaps();
            connection.close();
        }
    }

    public void addToMaps() {
        this.addToMaps(this.getStates());
    }

    public void addToMaps(ElectricState[] states) {
        for (ElectricState electricState : states) {
            itusolar.historique.wait.ElectricWaitState state = (itusolar.historique.wait.ElectricWaitState) electricState;
            this.getElectricStates().put(this.key(state.getMacAddress()), state);
            state.lost();
        }
    }

    public Map<String, itusolar.historique.wait.ElectricWaitState> getElectricStates() {
        return electricStates;
    }

    public void setElectricStates(Map<String, ElectricWaitState> electricStates) {
        this.electricStates = electricStates;
    }
}
