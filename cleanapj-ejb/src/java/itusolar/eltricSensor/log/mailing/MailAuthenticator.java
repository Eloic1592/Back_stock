package itusolar.eltricSensor.log.mailing;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
    MailConfiguration configuration;
    public MailAuthenticator(MailConfiguration configuration) {
        this.setConfiguration(configuration);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        System.out.println(this.getConfiguration().getFrom());
        String address = this.getConfiguration().getFrom().getAddress();
        String keyMail =  this.getConfiguration().getFrom().getKeyMail();
        return new PasswordAuthentication(address,keyMail);
    }

    public MailConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MailConfiguration configuration) {
        this.configuration = configuration;
    }
}
