package nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent.emailSending;

public class EmailMessage {

    private String toAddress;
    private String subject;
    private String message;

    public EmailMessage() {
    }

    public EmailMessage(String toAddress, String subject, String message) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
