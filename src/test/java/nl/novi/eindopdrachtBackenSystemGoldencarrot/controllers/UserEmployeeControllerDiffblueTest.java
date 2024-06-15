package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoOutput;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.UserEmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserEmployeeController.class})
@ExtendWith(SpringExtension.class)
class UserEmployeeControllerDiffblueTest {
    @Autowired
    private UserEmployeeController userEmployeeController;

    @MockBean
    private UserEmployeeService userEmployeeService;

    /**
     * Method under test:
     * {@link UserEmployeeController#createUserEmployee(UserEmployeeDto, BindingResult)}
     */
    @Test
    void testCreateUserEmployee() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserEmployeeController userEmployeeController = new UserEmployeeController(mock(UserEmployeeService.class));

        UserEmployeeDto userdto = new UserEmployeeDto();
        userdto.setAddress("42 Main St");
        userdto.setBankAccount("3");
        userdto.setDob(LocalDate.of(1970, 1, 1));
        userdto.setEmailAddress("42 Main St");
        userdto.setEmployeeNumber(1L);
        userdto.setFirstName("Jane");
        userdto.setFunction("Function");
        userdto.setLastName("Doe");
        userdto.setPassword("iloveyou");
        userdto.setPhoneNumb("6625550144");
        userdto.setRoles(new ArrayList<>());
        userdto.setUsername("JaneDoe");
        BeanPropertyBindingResult br = mock(BeanPropertyBindingResult.class);
        when(br.getFieldErrors()).thenReturn(new ArrayList<>());
        when(br.hasFieldErrors()).thenReturn(true);

        // Act
        ResponseEntity<Object> actualCreateUserEmployeeResult = userEmployeeController.createUserEmployee(userdto, br);

        // Assert
        verify(br).getFieldErrors();
        verify(br).hasFieldErrors();
        assertEquals("", actualCreateUserEmployeeResult.getBody());
        assertEquals(400, actualCreateUserEmployeeResult.getStatusCodeValue());
        assertTrue(actualCreateUserEmployeeResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link UserEmployeeController#createUserEmployee(UserEmployeeDto, BindingResult)}
     */
    @Test
    void testCreateUserEmployee2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserEmployeeController userEmployeeController = new UserEmployeeController(mock(UserEmployeeService.class));

        UserEmployeeDto userdto = new UserEmployeeDto();
        userdto.setAddress("42 Main St");
        userdto.setBankAccount("3");
        userdto.setDob(LocalDate.of(1970, 1, 1));
        userdto.setEmailAddress("42 Main St");
        userdto.setEmployeeNumber(1L);
        userdto.setFirstName("Jane");
        userdto.setFunction("Function");
        userdto.setLastName("Doe");
        userdto.setPassword("iloveyou");
        userdto.setPhoneNumb("6625550144");
        userdto.setRoles(new ArrayList<>());
        userdto.setUsername("JaneDoe");

        ArrayList<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError("Object Name", "Field", "Default Message"));
        BeanPropertyBindingResult br = mock(BeanPropertyBindingResult.class);
        when(br.getFieldErrors()).thenReturn(fieldErrorList);
        when(br.hasFieldErrors()).thenReturn(true);

        // Act
        ResponseEntity<Object> actualCreateUserEmployeeResult = userEmployeeController.createUserEmployee(userdto, br);

        // Assert
        verify(br).getFieldErrors();
        verify(br).hasFieldErrors();
        assertEquals("Field: Default Message\n", actualCreateUserEmployeeResult.getBody());
        assertEquals(400, actualCreateUserEmployeeResult.getStatusCodeValue());
        assertTrue(actualCreateUserEmployeeResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link UserEmployeeController#updateUserEmployee(Long, UserEmployeeDtoUpdate, BindingResult)}
     */
    @Test
    void testUpdateUserEmployee() throws Exception {
        // Arrange
        UserEmployeeDtoOutput userEmployeeDtoOutput = new UserEmployeeDtoOutput();
        userEmployeeDtoOutput.setAddress("42 Main St");
        userEmployeeDtoOutput.setBankAccount("3");
        userEmployeeDtoOutput.setDob(LocalDate.of(1970, 1, 1));
        userEmployeeDtoOutput.setEmailAddress("42 Main St");
        userEmployeeDtoOutput.setEmployeeNumber(1L);
        userEmployeeDtoOutput.setFirstName("Jane");
        userEmployeeDtoOutput.setFunction("Function");
        userEmployeeDtoOutput.setLastName("Doe");
        userEmployeeDtoOutput.setPhoneNumb("6625550144");
        userEmployeeDtoOutput.setRoles(new ArrayList<>());
        userEmployeeDtoOutput.setUsername("JaneDoe");
        when(userEmployeeService.updateUserEmployee(Mockito.<Long>any(), Mockito.<UserEmployeeDtoUpdate>any()))
                .thenReturn(userEmployeeDtoOutput);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/users_employees/{employeeNumber}", 1L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UserEmployeeDtoUpdate()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userEmployeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"employeeNumber\":1,\"username\":\"JaneDoe\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"dob\":[1970,1,1],\"address\":\"42"
                                        + " Main St\",\"phoneNumb\":\"6625550144\",\"emailAddress\":\"42 Main St\",\"bankAccount\":\"3\",\"function\":\"Function"
                                        + "\",\"roles\":[]}"));
    }

    /**
     * Method under test:
     * {@link UserEmployeeController#createUserEmployee(UserEmployeeDto, BindingResult)}
     */

    @Test
    void testDeleteCustomer() throws Exception {
        // Arrange
        when(userEmployeeService.deleteUserEmployee(Mockito.<Long>any())).thenReturn("Delete User Employee");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users_employees/{employeeNumber}",
                1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userEmployeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("UserEmployee: \"Delete User Employee\" deleted from database"));
    }

    /**
     * Method under test: {@link UserEmployeeController#getCustomerByUsername(Long)}
     */
    @Test
    void testGetCustomerByUsername() throws Exception {
        // Arrange
        UserEmployeeDtoOutput userEmployeeDtoOutput = new UserEmployeeDtoOutput();
        userEmployeeDtoOutput.setAddress("42 Main St");
        userEmployeeDtoOutput.setBankAccount("3");
        userEmployeeDtoOutput.setDob(LocalDate.of(1970, 1, 1));
        userEmployeeDtoOutput.setEmailAddress("42 Main St");
        userEmployeeDtoOutput.setEmployeeNumber(1L);
        userEmployeeDtoOutput.setFirstName("Jane");
        userEmployeeDtoOutput.setFunction("Function");
        userEmployeeDtoOutput.setLastName("Doe");
        userEmployeeDtoOutput.setPhoneNumb("6625550144");
        userEmployeeDtoOutput.setRoles(new ArrayList<>());
        userEmployeeDtoOutput.setUsername("JaneDoe");
        when(userEmployeeService.getUserEmployee(Mockito.<Long>any())).thenReturn(userEmployeeDtoOutput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users_employees/{employeeNumber}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userEmployeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"employeeNumber\":1,\"username\":\"JaneDoe\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"dob\":[1970,1,1],\"address\":\"42"
                                        + " Main St\",\"phoneNumb\":\"6625550144\",\"emailAddress\":\"42 Main St\",\"bankAccount\":\"3\",\"function\":\"Function"
                                        + "\",\"roles\":[]}"));
    }

    /**
     * Method under test: {@link UserEmployeeController#getUsers()}
     */
    @Test
    void testGetUsers() throws Exception {
        // Arrange
        when(userEmployeeService.getAllUserEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users_employees");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userEmployeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
