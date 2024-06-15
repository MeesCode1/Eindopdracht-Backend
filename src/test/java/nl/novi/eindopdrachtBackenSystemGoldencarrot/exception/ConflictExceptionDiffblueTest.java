package nl.novi.eindopdrachtBackenSystemGoldencarrot.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConflictExceptionDiffblueTest {
    /**
     * Method under test: {@link ConflictException#ConflictException(String)}
     */
    @Test
    void testNewConflictException() {
        // Arrange and Act
        ConflictException actualConflictException = new ConflictException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualConflictException.getLocalizedMessage());
        assertEquals("An error occurred", actualConflictException.getMessage());
        assertNull(actualConflictException.getCause());
        assertEquals(0, actualConflictException.getSuppressed().length);
    }
}
