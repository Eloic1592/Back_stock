package itusolar.eltricSensor.log;

import itusolar.eltricSensor.ElectricState;
import itusolar.eltricSensor.beeper.BeeperCaller;
import itusolar.eltricSensor.call.ElectricTester;
import itusolar.eltricSensor.call.ElectricTesterSignature;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ElectricHandler implements Runnable {

    public static int timeSleep = 2000;
    ElectricState[] states;
    Map<String, ElectricLogger> logger = new HashMap<>();
    Connection connection;
    ElectricTesterSignature tester;
    ElectricState[] beeper;
    boolean stoped = false;
    public ElectricHandler(ElectricState[] states, ElectricState[] beeper, Connection connection) {
        this.setStates(states);
        this.setConnection(connection);
        this.setBeeper(beeper);
        this.tester = new BeeperCaller();
    }

    public ElectricState[] getBeeper() {
        return beeper;
    }

    public void setBeeper(ElectricState[] beeper) {
        this.beeper = beeper;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.listen();
                Thread.sleep(ElectricHandler.timeSleep);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listen() throws Exception {
        int[] status = {0};
        for(ElectricState state : this.getStates()) {
            this.listen(state, status);
        }
        if (status[0] > 0) {
            this.beep();
            this.stoped = false;
        } else if (!this.stoped) {
            this.stop();
            this.stoped = true;
        }
    }

    private void listen(ElectricState state, int[] status) throws IOException {
        if (!state.isOpen()) {
            status[0] = 50;
            try {
                this.log(state);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.changeState(state);
        }
    }

    public void beep() throws IOException {
        ((BeeperCaller) this.tester).setActive(true);
        this.tester.evaluate(this.getBeeper());
    }

    public void stop() throws IOException {
        ((BeeperCaller) this.tester).setActive(false);
        this.tester.evaluate(this.getBeeper());
    }

    public void changeState(ElectricState state) throws IOException {
        this.getLog(state).setChanged(false);
    }

    public void log(ElectricState state) throws IOException {
        ElectricLogger log = this.getLog(state);
        if (!log.isChanged()) {
            log.log();
            log.setChanged(true);
        }
    }

    public ElectricLogger getLog(ElectricState state) throws IOException {
        ElectricLogger log = this.getLogger().get(state.getKey());
        if (log == null) {
            log = new ElectricLogger(state, ElectricLogger.signature);
            this.getLogger().put(state.getKey(), log);
        }
        return log;
    }

    public ElectricState[] getStates() {
        return states;
    }

    public void setStates(ElectricState[] states) {
        this.states = states;
    }

    public Map<String, ElectricLogger> getLogger() {
        return logger;
    }

    public void setLogger(Map<String, ElectricLogger> logger) {
        this.logger = logger;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
