package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.CustomerService;
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
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerDiffblueTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerController#getCustomerByCompany(String)}
     */
    @Test
    void testGetCustomerByCompany() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAddress("42 Main St");
        customerDto.setBankAccount("3");
        customerDto.setCompany("Company");
        customerDto.setDob(LocalDate.of(1970, 1, 1));
        customerDto.setEmailAddress("42 Main St");
        customerDto.setFirstName("Jane");
        customerDto.setId(1L);
        customerDto.setLastName("Doe");
        customerDto.setPhoneNumber("6625550144");
        when(customerService.getCustomerByCompany(Mockito.<String>any())).thenReturn(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{company}", "Company");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"company\":\"Company\",\"address\":\"42 Main St\",\"dob\":[1970,1"
                                        + ",1],\"emailAddress\":\"42 Main St\",\"phoneNumber\":\"6625550144\",\"bankAccount\":\"3\"}"));
    }

    /**
     * Method under test:
     * {@link CustomerController#createCustomer(CustomerDto, BindingResult)}
     */
    @Test
    void testCreateCustomer() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController(mock(CustomerService.class));

        CustomerDto cdto = new CustomerDto();
        cdto.setAddress("42 Main St");
        cdto.setBankAccount("3");
        cdto.setCompany("Company");
        cdto.setDob(LocalDate.of(1970, 1, 1));
        cdto.setEmailAddress("42 Main St");
        cdto.setFirstName("Jane");
        cdto.setId(1L);
        cdto.setLastName("Doe");
        cdto.setPhoneNumber("6625550144");
        BeanPropertyBindingResult br = mock(BeanPropertyBindingResult.class);
        when(br.getFieldErrors()).thenReturn(new ArrayList<>());
        when(br.hasFieldErrors()).thenReturn(true);

        // Act
        ResponseEntity<Object> actualCreateCustomerResult = customerController.createCustomer(cdto, br);

        // Assert
        verify(br).getFieldErrors();
        verify(br).hasFieldErrors();
        assertEquals("", actualCreateCustomerResult.getBody());
        assertEquals(400, actualCreateCustomerResult.getStatusCodeValue());
        assertTrue(actualCreateCustomerResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#createCustomer(CustomerDto, BindingResult)}
     */
    @Test
    void testCreateCustomer2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController(mock(CustomerService.class));

        CustomerDto cdto = new CustomerDto();
        cdto.setAddress("42 Main St");
        cdto.setBankAccount("3");
        cdto.setCompany("Company");
        cdto.setDob(LocalDate.of(1970, 1, 1));
        cdto.setEmailAddress("42 Main St");
        cdto.setFirstName("Jane");
        cdto.setId(1L);
        cdto.setLastName("Doe");
        cdto.setPhoneNumber("6625550144");

        ArrayList<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new FieldError("Object Name", "Field", "Default Message"));
        BeanPropertyBindingResult br = mock(BeanPropertyBindingResult.class);
        when(br.getFieldErrors()).thenReturn(fieldErrorList);
        when(br.hasFieldErrors()).thenReturn(true);

        // Act
        ResponseEntity<Object> actualCreateCustomerResult = customerController.createCustomer(cdto, br);

        // Assert
        verify(br).getFieldErrors();
        verify(br).hasFieldErrors();
        assertEquals("Field: Default Message\n", actualCreateCustomerResult.getBody());
        assertEquals(400, actualCreateCustomerResult.getStatusCodeValue());
        assertTrue(actualCreateCustomerResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#updateCustomer(String, CustomerDtoUpdate, BindingResult)}
     */
    @Test
    void testUpdateCustomer() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAddress("42 Main St");
        customerDto.setBankAccount("33340488");
        customerDto.setCompany("Company");
        customerDto.setDob(LocalDate.of(1970, 1, 1));
        customerDto.setEmailAddress("janedoe-testmail@outlook.com");
        customerDto.setFirstName("Jane");
        customerDto.setId(1L);
        customerDto.setLastName("Doe");
        customerDto.setPhoneNumber("6625550144");
        when(customerService.updateCustomer(Mockito.<String>any(), Mockito.<CustomerDtoUpdate>any()))
                .thenReturn(customerDto);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/customers/{company}", "Company")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CustomerDtoUpdate()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"company\":\"Company\",\"address\":\"42 Main St\",\"dob\":[1970,1"
                                        + ",1],\"emailAddress\":\"janedoe-testmail@outlook.com\",\"phoneNumber\":\"6625550144\",\"bankAccount\":\"33340488\"}"));
    }

    /**
     * Method under test: {@link CustomerController#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer() throws Exception {
        // Arrange
        when(customerService.deleteCustomer(Mockito.<Long>any())).thenReturn("Delete Customer");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer: \"Delete Customer\" deleted from database"));
    }

    /**
     * Method under test: {@link CustomerController#showCustomers()}
     */
    @Test
    void testShowCustomers() throws Exception {
        // Arrange
        when(customerService.getAllCustomers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
