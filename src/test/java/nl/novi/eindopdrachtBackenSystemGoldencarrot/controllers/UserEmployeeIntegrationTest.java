package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

    @SpringBootTest
    @AutoConfigureMockMvc(addFilters = false)
    @ActiveProfiles("test")
    class UserEmployeeIntegrationTest {

        @Autowired
        MockMvc mockMvc;

        @Test
        void shouldCreateCorrectUserEmployee() throws Exception {

            String requestJsonRole1 = """
                        {
                                "id": 1,
                                "roleName": "FINANCE-ADM"
                            }
                    """;

            String requestJson = """
                        {
                                "username": "JohnWest",
                                "password": "Vanavond",
                                "firstName": "John",
                                "lastName": "West",
                                "dob": "1988-07-12",
                                "address": "Draaiweg 86 den Haag",
                                "function": "finance",
                                "phoneNumb": "0688994433",
                                "emailAddress": "john-west@amsterdam.nl",
                                "bankAccount": "INGB0003123123",
                                "roles": ["FINANCE-ADM"]
                            }
                    """;

            this.mockMvc
                    .perform(MockMvcRequestBuilders.post("/roles")
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(requestJsonRole1))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
            this.mockMvc
                    .perform(MockMvcRequestBuilders.post("/users_employees")
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(requestJson))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }
}
