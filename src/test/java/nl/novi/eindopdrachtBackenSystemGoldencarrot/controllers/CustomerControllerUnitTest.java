package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;


import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.security.JwtService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

@WebMvcTest(CustomerController.class)
@ActiveProfiles("test")
class CustomerControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    CustomerService customerService;

    @Test  //SERVICE-DSK
    @WithMockUser(username = "testUser", roles = "JOKERCEO")
    void shouldRetrieveCorrectCustomer() throws Exception {

        CustomerDto cdto = new CustomerDto();
        cdto.firstName = "Arjen";
        cdto.lastName = "Robben";
        cdto.company = "PSV";
        cdto.phoneNumber = "0699998765";

        Mockito.when(customerService.getCustomerByCompany("PSV")).thenReturn(cdto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/PSV"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is("Robben")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company", is("PSV")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", is("0699998765")));
    }
}
