package nl.novi.eindopdrachtBackenSystemGoldencarrot.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResourceNotFoundExceptionDiffblueTest {
    /**
     * Method under test:
     * {@link ResourceNotFoundException#ResourceNotFoundException(String)}
     */
    @Test
    void testNewResourceNotFoundException() {
        // Arrange and Act
        ResourceNotFoundException actualResourceNotFoundException = new ResourceNotFoundException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualResourceNotFoundException.getLocalizedMessage());
        assertEquals("An error occurred", actualResourceNotFoundException.getMessage());
        assertNull(actualResourceNotFoundException.getCause());
        assertEquals(0, actualResourceNotFoundException.getSuppressed().length);
    }
}
