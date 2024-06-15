package nl.novi.eindopdrachtBackenSystemGoldencarrot.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IllegalArgumentExceptionDiffblueTest {
    /**
     * Method under test:
     * {@link IllegalArgumentException#IllegalArgumentException(String)}
     */
    @Test
    void testNewIllegalArgumentException() {
        // Arrange and Act
        IllegalArgumentException actualIllegalArgumentException = new IllegalArgumentException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualIllegalArgumentException.getLocalizedMessage());
        assertEquals("An error occurred", actualIllegalArgumentException.getMessage());
        assertNull(actualIllegalArgumentException.getCause());
        assertEquals(0, actualIllegalArgumentException.getSuppressed().length);
    }
}
