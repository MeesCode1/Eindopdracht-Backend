package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class BindingValidatorDiffblueTest {
    /**
     * Method under test: {@link BindingValidator#validateInput(BindingResult)}
     */
    @Test
    void testValidateInput() {
        // Arrange, Act and Assert
        assertNull(BindingValidator.validateInput(new BindException("Target", "Object Name")));
    }

    /**
     * Method under test: {@link BindingValidator#validateInput(BindingResult)}
     */
    @Test
    void testValidateInput2() {
        // Arrange
        BeanPropertyBindingResult br = mock(BeanPropertyBindingResult.class);
        when(br.getFieldErrors()).thenReturn(new ArrayList<>());
        when(br.hasFieldErrors()).thenReturn(true);

        // Act
        String actualValidateInputResult = BindingValidator.validateInput(br);

        // Assert
        verify(br).getFieldErrors();
        verify(br).hasFieldErrors();
        assertEquals("", actualValidateInputResult);
    }

    /**
     * Method under test: {@link BindingValidator#validateInput(BindingResult)}
     */
    @Test
    void testValidateInput3() {
        // Arrange
        ArrayList<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError("Object Name", "Field", "Default Message"));
        BeanPropertyBindingResult br = mock(BeanPropertyBindingResult.class);
        when(br.getFieldErrors()).thenReturn(fieldErrorList);
        when(br.hasFieldErrors()).thenReturn(true);

        // Act
        String actualValidateInputResult = BindingValidator.validateInput(br);

        // Assert
        verify(br).getFieldErrors();
        verify(br).hasFieldErrors();
        assertEquals("Field: Default Message\n", actualValidateInputResult);
    }
}
