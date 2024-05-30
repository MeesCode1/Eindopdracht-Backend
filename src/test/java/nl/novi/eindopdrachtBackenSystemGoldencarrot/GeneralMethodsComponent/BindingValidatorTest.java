package nl.novi.eindopdrachtBackenSystemGoldencarrot.GeneralMethodsComponent;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.BindingValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BindingValidatorTest {

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private BindingValidator bindingValidator;

    @Test
    void validateInput_withFieldErrors_shouldReturnErrorMessage() {

        MockitoAnnotations.openMocks(this);

        when(bindingResult.hasFieldErrors()).thenReturn(true);

        FieldError fieldError1 = new FieldError("objectName", "fieldName1", "Error message 1");
        FieldError fieldError2 = new FieldError("objectName", "fieldName2", "Error message 2");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        String result = BindingValidator.validateInput(bindingResult);

        String expected = "fieldName1: Error message 1\nfieldName2: Error message 2\n";

        assertEquals(expected, result);
    }

    @Test
    void validateInput_withNoFieldErrors_shouldReturnNull() {

        MockitoAnnotations.openMocks(this);

        when(bindingResult.hasFieldErrors()).thenReturn(false);

        String result = BindingValidator.validateInput(bindingResult);

        assertEquals(null, result);
    }
}
