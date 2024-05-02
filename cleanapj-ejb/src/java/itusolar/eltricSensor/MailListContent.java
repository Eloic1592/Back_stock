package itusolar.eltricSensor;

import itusolar.eltricSensor.log.mailing.MailAddress;

public class MailListContent {
    MailAddress[] addresses;

    public MailAddress[] getAddresses() {
        return addresses;
    }

    public void setAddresses(MailAddress[] addresses) {
        this.addresses = addresses;
    }
}
