package itusolar.historique.wait;

import itusolar.eltricSensor.log.mailing.MailAccessorSignature;

import java.sql.Connection;

public interface ElectricWaitMethodSignature {
    public void load(WaitParams params) throws Exception;

    public void start(MailAccessorSignature accessor, Connection connection) throws Exception;
}
