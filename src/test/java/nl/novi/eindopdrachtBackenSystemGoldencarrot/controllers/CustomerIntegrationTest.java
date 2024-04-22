package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class CustomerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateCorrectCustomer() throws Exception {

        String requestJson = """
                {
                    "company": "Maze",
                    "address": "10-13 GrosvenorSquare London",
                    "firstName": "Gordon",
                    "lastName": "Ramsey",
                    "dob": "1986-11-08",
                    "phoneNumber": "0644448888",
                    "emailAddress": "Gordon@cooking.nl",
                    "bankAccount": "NLINGB0021222567"
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}