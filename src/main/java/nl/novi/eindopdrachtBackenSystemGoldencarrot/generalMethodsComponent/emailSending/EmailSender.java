package nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent.emailSending;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

@Component
public class EmailSender {

    public void sendEmail(final String userName,
                          final String password,
                          final EmailMessage emailMessage) throws
            MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.user", userName);

        Session session = Session.getDefaultInstance(properties);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(emailMessage.getToAddress())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(emailMessage.getSubject());
        msg.setSentDate(new Date());

        msg.setText(emailMessage.getMessage());

        Transport t = session.getTransport("smtp");
        t.connect(userName, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();


    }

}
