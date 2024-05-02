package itusolar.eltricSensor.call;

import itusolar.eltricSensor.*;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class ElectricCallMethod extends HServiceManager implements ElectricManagerSignature {

    ElectricTesterSignature tester;
    ElectricAccessorSignature accessor;
    ElectricState[] states;

    public ElectricCallMethod(ElectricTesterSignature tester, ElectricAccessorSignature accessor) {
        this.tester = tester;
        this.accessor = accessor;
    }

    @Override
    public ElectricPage pageContent(Connection connection) throws Exception {
        ElectricPage page = new ElectricPage();
        page.setStates(this.evaluate(connection));
        return page;
    }

    @Override
    public ElectricState[] evaluate(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        this.initStates(new ElectricState(),connection);
        this.tester.evaluate(this.getStates());
        return this.getStates();
    }

    public void initStates(ElectricSensor sensor,Connection connection) throws Exception {
        if (this.getStates() == null) {
            this.setStates(this.accessor.getAll(sensor,connection));
        }
    }

    public ElectricAccessorSignature getAccessor() {
        return accessor;
    }

    public void setAccessor(ElectricAccessorSignature accessor) {
        this.accessor = accessor;
    }

    public ElectricState[] getStates() {
        return states;
    }

    public void setStates(ElectricState[] states) {
        this.states = states;
    }
}
