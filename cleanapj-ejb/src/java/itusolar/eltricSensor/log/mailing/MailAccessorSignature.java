package itusolar.eltricSensor.log.mailing;

import itusolar.eltricSensor.MailListContent;

import java.sql.Connection;
import java.sql.SQLException;

public interface MailAccessorSignature {
    public MailListContent listContent(Connection connection) throws Exception;
    public MailAddress[] getAll(Connection connection) throws Exception;
    public void create(MailAddress address, Connection connection) throws Exception;
    public MailAddress[] search(MailAddress address,String where, Connection connection) throws Exception;
    public MailConfiguration prepare(Connection connection) throws Exception;
    public MailConfiguration prepare(MailAddress[] addresses) throws NoFromMailException, NoToAddressException;
}
