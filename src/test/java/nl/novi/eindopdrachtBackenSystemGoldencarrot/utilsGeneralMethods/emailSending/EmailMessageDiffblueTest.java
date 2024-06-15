package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailMessageDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link EmailMessage#EmailMessage(String, String, String)}
     *   <li>{@link EmailMessage#setMessage(String)}
     *   <li>{@link EmailMessage#setSubject(String)}
     *   <li>{@link EmailMessage#setToAddress(String)}
     *   <li>{@link EmailMessage#getMessage()}
     *   <li>{@link EmailMessage#getSubject()}
     *   <li>{@link EmailMessage#getToAddress()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        EmailMessage actualEmailMessage = new EmailMessage("42 Main St", "Hello from the Dreaming Spires",
                "Not all who wander are lost");
        actualEmailMessage.setMessage("Not all who wander are lost");
        actualEmailMessage.setSubject("Hello from the Dreaming Spires");
        actualEmailMessage.setToAddress("42 Main St");
        String actualMessage = actualEmailMessage.getMessage();
        String actualSubject = actualEmailMessage.getSubject();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualEmailMessage.getToAddress());
        assertEquals("Hello from the Dreaming Spires", actualSubject);
        assertEquals("Not all who wander are lost", actualMessage);
    }
}
