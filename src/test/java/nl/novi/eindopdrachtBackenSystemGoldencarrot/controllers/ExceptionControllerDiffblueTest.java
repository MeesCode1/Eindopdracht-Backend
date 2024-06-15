package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ConflictException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExceptionControllerDiffblueTest {
    /**
     * Method under test:
     * {@link ExceptionController#handleResourceNotFoundException(ResourceNotFoundException)}
     */
    @Test
    void testHandleResourceNotFoundException() {
        // Arrange
        ExceptionController exceptionController = new ExceptionController();

        // Act
        ResponseEntity<String> actualHandleResourceNotFoundExceptionResult = exceptionController
                .handleResourceNotFoundException(new ResourceNotFoundException("An error occurred"));

        // Assert
        assertEquals("An error occurred", actualHandleResourceNotFoundExceptionResult.getBody());
        assertEquals(404, actualHandleResourceNotFoundExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleResourceNotFoundExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExceptionController#handleIllegalArgumentException(IllegalArgumentException)}
     */
    @Test
    void testHandleIllegalArgumentException() {
        // Arrange
        ExceptionController exceptionController = new ExceptionController();

        // Act
        ResponseEntity<String> actualHandleIllegalArgumentExceptionResult = exceptionController
                .handleIllegalArgumentException(new IllegalArgumentException("foo"));

        // Assert
        assertEquals("foo", actualHandleIllegalArgumentExceptionResult.getBody());
        assertEquals(400, actualHandleIllegalArgumentExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleIllegalArgumentExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExceptionController#handleConflictException(ConflictException)}
     */
    @Test
    void testHandleConflictException() {
        // Arrange
        ExceptionController exceptionController = new ExceptionController();

        // Act
        ResponseEntity<String> actualHandleConflictExceptionResult = exceptionController
                .handleConflictException(new ConflictException("An error occurred"));

        // Assert
        assertEquals("An error occurred", actualHandleConflictExceptionResult.getBody());
        assertEquals(409, actualHandleConflictExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleConflictExceptionResult.getHeaders().isEmpty());
    }
}
