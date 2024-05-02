package itusolar.eltricSensor.log.mailing;

import itusolar.prepare.MappedInteger;

import java.sql.Connection;

public class MailAddress extends MappedInteger {
    public static int FROM = 500;
    public static int TO = 100;
    private String address, keyMail;
    int accreditation;

    @Override
    public String toString() {
        return "MailAddress{" +
                "address='" + address + '\'' +
                ", keyMail='" + keyMail + '\'' +
                ", accreditation=" + accreditation +
                '}';
    }

    public MailAddress() {
        this.setNomTable("mailAddress");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqMailAddress");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public String getKeyMail() {
        return keyMail;
    }

    public void setKeyMail(String keyMail) {
        this.keyMail = keyMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(int accreditation) {
        this.accreditation = accreditation;
    }
}
