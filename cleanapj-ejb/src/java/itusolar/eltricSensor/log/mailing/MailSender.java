package itusolar.eltricSensor.log.mailing;

import itusolar.eltricSensor.ElectricSensor;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender extends Authenticator implements MailSenderSignature , Runnable{
    Properties properties;
    Session session;
    MailConfiguration configuration;
    Message message;
    boolean active = false;

    public MailSender(MailConfiguration configuration) {
        this.initProperties();
        this.setConfiguration(configuration);
    }

    public void init(MailConfiguration configuration) throws MessagingException {
        this.initSession();
        Message message = new MimeMessage(this.getSession());
        this.setMessage(message);
        configuration.config(this);
    }

    public void initProperties() {
        this.setProperties(new Properties());
        this.getProperties().put("mail.smtp.auth", true);
        this.getProperties().put("mail.smtp.starttls.enable", "true");
        this.getProperties().put("mail.smtp.host", "smtp.gmail.com");
        this.getProperties().put("mail.smtp.port", "587");
//        this.getProperties().put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
    }

    public void initSession() {
        MailAuthenticator auth = new MailAuthenticator(this.getConfiguration());
        this.setSession(Session.getInstance(this.getProperties(), auth));
    }


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MailConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MailConfiguration configuration) {
        this.configuration = configuration;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void sendFirst() throws MessagingException {
        this.init(this.getConfiguration());
        Transport.send(this.getMessage());
        this.setActive(true);
    }

    public void sendOnChange() throws MessagingException {
        this.getConfiguration().init(this);
        Transport.send(this.getMessage());
        this.setActive(true);
    }

    @Override
    public void send() throws MessagingException {
        if (this.getMessage() == null) {
            this.sendFirst();
        } else {
            this.sendOnChange();
        }
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void run() {
        try {
            this.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
