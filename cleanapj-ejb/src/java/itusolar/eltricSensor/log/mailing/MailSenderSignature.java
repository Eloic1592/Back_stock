package itusolar.eltricSensor.log.mailing;

import javax.mail.MessagingException;

public interface MailSenderSignature {
    public void send() throws MessagingException;
}
