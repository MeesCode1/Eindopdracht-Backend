package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailMessageTest {


    @Test
    public void testEmailMessageConstructorAndGetters() {
        String toAddress = "test@example.com";
        String subject = "Test Subject";
        String message = "This is a test message.";

        EmailMessage emailMessage = new EmailMessage(toAddress, subject, message);

        assertEquals(toAddress, emailMessage.getToAddress());
        assertEquals(subject, emailMessage.getSubject());
        assertEquals(message, emailMessage.getMessage());
    }

    @Test
    public void testSetToAddress() {
        EmailMessage emailMessage = new EmailMessage("old@example.com", "Subject", "Message");
        String newToAddress = "new@example.com";

        emailMessage.setToAddress(newToAddress);
        assertEquals(newToAddress, emailMessage.getToAddress());
    }

    @Test
    public void testSetSubject() {
        EmailMessage emailMessage = new EmailMessage("test@example.com", "Old Subject", "Message");
        String newSubject = "New Subject";

        emailMessage.setSubject(newSubject);
        assertEquals(newSubject, emailMessage.getSubject());
    }

    @Test
    public void testSetMessage() {
        EmailMessage emailMessage = new EmailMessage("test@example.com", "Subject", "Old Message");
        String newMessage = "New Message";

        emailMessage.setMessage(newMessage);
        assertEquals(newMessage, emailMessage.getMessage());
    }

}