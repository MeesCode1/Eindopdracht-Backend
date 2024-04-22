package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.security.JwtService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.CustomerService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.UserEmployeeService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@WebMvcTest(UserEmployeeController.class)
@ActiveProfiles("test")
class UserEmployeeControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    UserEmployeeService service;

    @Test
    @WithMockUser(username = "testUser", roles = "JOKERCEO")
    void shouldRetrieveCorrectCustomer() throws Exception {

        String role1 = "CEO";
        List<String> roles = new ArrayList<>();
        roles.add(role1);

        UserEmployeeDto udto = new UserEmployeeDto();
        udto.setEmployeeNumber(1L);
        udto.setUsername("GuusMeeuwis");
        udto.setPassword("topsecret");
        udto.setFirstName("Guus");
        udto.setLastName("Meeuwis");
        udto.setDob(LocalDate.of(1972, 02, 23));
        udto.setAddress("Muziekweg 38 Eindhoven");
        udto.setPhoneNumb("+31622772277");
        udto.setEmailAddress("test mail@mail.com");
        udto.setBankAccount("NL25INGB0003123456");
        udto.setFunction("CEO");
        udto.setRoles(roles);

        Mockito.when(service.getUserEmployee(1L)).thenReturn(udto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users_employees/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is("GuusMeeuwis")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumb", is("+31622772277")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bankAccount", is("NL25INGB0003123456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", is("Muziekweg 38 Eindhoven")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0]", is("CEO")));
    }
}
