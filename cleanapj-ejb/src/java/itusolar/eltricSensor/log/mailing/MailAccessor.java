package itusolar.eltricSensor.log.mailing;

import bean.CGenUtil;
import itusolar.eltricSensor.MailListContent;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class MailAccessor extends HServiceManager implements MailAccessorSignature {

    @Override
    public MailListContent listContent(Connection connection) throws Exception {
        MailAddress[] addresses = this.search(new MailAddress(), "and accreditation < 500",connection);
        MailListContent content = new MailListContent();
        content.setAddresses(addresses);
        return content;
    }

    @Override
    public MailAddress[] getAll(Connection connection) throws Exception {
        return this.search(new MailAddress(), "", connection);
    }

    @Override
    public void create(MailAddress address, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (address.getId() <= 0) {
            address.construirePK(connection);
            CGenUtil.save(address, connection);
        } else {
            address.updateToTable(connection);
        }
        connection.close();
    }

    @Override
    public MailConfiguration prepare(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        MailAddress[] addresses = this.search(new MailAddress(), "", connection);
        return this.prepare(addresses);
    }

    @Override
    public MailConfiguration prepare(MailAddress[] addresses) throws NoFromMailException, NoToAddressException {
        MailAddress from = this.getFromAddress(addresses);
        MailAddress to = this.getToAddress(addresses);
        MailAddress[] cc = this.getCC(addresses);
        String subject = this.getSubject();
        String content = this.getContent();
        return new MailConfiguration(from, to, cc, content, subject);
    }

    public MailAddress[] getCC(MailAddress[] addresses) {
        Vector<MailAddress> cc = new Vector<MailAddress>();
        for (MailAddress address : addresses) {
            if (address.getAccreditation() < MailAddress.TO) {
                cc.add(address);
            }
        }
        MailAddress[] result = new MailAddress[cc.size()];
        cc.copyInto(result);
        return result;
    }

    public String getContent() {
        return "";
    }

    public String getSubject() {
        return "Coupure de courant ITU";
    }

    public MailAddress getFromAddress(MailAddress[] addresses) throws NoFromMailException {
        for (MailAddress address : addresses) {
            if (address.getAccreditation() >= MailAddress.FROM) {
                return address;
            }
        }
        throw new NoFromMailException();
    }

    public MailAddress getToAddress(MailAddress[] addresses) throws NoToAddressException {
        for (MailAddress address : addresses) {
            if ( MailAddress.TO <= address.getAccreditation() && address.getAccreditation() < MailAddress.FROM) {
                return address;
            }
        }
        throw new NoToAddressException();
    }


    public MailAddress[] search(MailAddress address, String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        String[] intervals = {};
        Object[] values = CGenUtil.rechercher(address, intervals, intervals, connection, where);
        return this.cast(values);
    }

    public MailAddress[] cast(Object[] values) {
        MailAddress[] addresses = new MailAddress[values.length];
        for (int i = 0; i < values.length; i++) {
            addresses[i] = (MailAddress) values[i];
        }
        return addresses;
    }
}
