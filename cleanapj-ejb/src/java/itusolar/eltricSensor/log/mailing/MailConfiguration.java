package itusolar.eltricSensor.log.mailing;

import itusolar.eltricSensor.ElectricSensor;
import itusolar.eltricSensor.ElectricState;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class MailConfiguration {
    MailAddress from;
    MailAddress to;
    MailAddress[] cc;
    String msg;
    String subject;

    public MailConfiguration() {
    }

    public MailConfiguration(MailConfiguration configuration) {
        this(configuration.getFrom(),
                configuration.getTo(),
                configuration.getCc(),
                configuration.getMsg(),
                configuration.getSubject());
    }

    public MailConfiguration(MailAddress from, MailAddress to,MailAddress[] cc, String msg, String subject) {
        this.setCc(cc);
        this.setFrom(from);
        this.setTo(to);
        this.setMsg(msg);
        this.setSubject(subject);
    }

    public void config(MailSender sender) throws MessagingException {
        this.init(sender);
        this.configFrom(sender.getMessage());
        this.configRecipients(sender.getMessage());
        this.configSubject(sender.getMessage());
        this.configBody(sender.getMessage());
    }

    public void init(MailSender sender) {

    }

    public void configBody(Message message) throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
    }

    public void configSubject(Message message) throws MessagingException {
        message.setSubject(this.getSubject());
    }

    public void configRecipients(Message message) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.getTo().getAddress()));
        if (this.getCc() != null) {
            for ( MailAddress address : this.getCc()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(address.getAddress()));
            }
        }
    }
    public void configFrom(Message message) throws MessagingException {
        message.setFrom(new InternetAddress(this.getFrom().getAddress()));
    }

    public MailAddress getFrom() {
        return from;
    }

    public void setFrom(MailAddress from) {
        this.from = from;
    }

    public MailAddress getTo() {
        return to;
    }

    public void setTo(MailAddress to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MailAddress[] getCc() {
        return cc;
    }

    public void setCc(MailAddress[] cc) {
        this.cc = cc;
    }
}
