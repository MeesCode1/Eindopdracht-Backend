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
public class ProductIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateCorrectProduct() throws Exception {

        String requestJson = """
                {
                   "name": "Dorade side",
                   "category": "fish",
                   "priceInEur": "36.12",
                   "packedPerUnit": "c.a 600gr",
                   "inStock": 7,
                   "description": "fish descr",
                   "shortDescription": "fish short descr"
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
