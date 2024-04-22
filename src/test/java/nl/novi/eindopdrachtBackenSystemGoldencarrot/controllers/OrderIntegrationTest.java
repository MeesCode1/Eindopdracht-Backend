package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent.ModelMapperConfig;
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
class OrderIntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void shouldCreateCorrectOrder() throws Exception {

        String requestJsonProduct1 = """
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

        String requestJsonProduct2 = """
                {
                   "name": "Vine tomatoes",
                   "category": "vegitables",
                   "priceInEur": "3.59",
                   "packedPerUnit": "1kg",
                   "inStock": 21,
                   "description": "vegi's test descr",
                   "shortDescription": "vegi's test short descr"
                }
                """;

        String requestJsonProduct3 = """
                {
                   "name": "Basil",
                   "category": "herbs & spices",
                   "priceInEur": "2.29",
                   "packedPerUnit": "20gr",
                   "inStock": 11,
                   "description": "herbs test descr",
                   "shortDescription": "herbs test short descr"
                }
                """;

        String requestJsonCustomer = """
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

        String requestJson = """
                    {
                        "customerCompany": "Maze",
                       "products": [{"productName": "Dorade side", "quantity": 3},
                                   {"productName":"Vine tomatoes", "quantity": 2},
                                   {"productName":"Basil", "quantity": 1}]
                     }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonProduct1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonProduct2))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonProduct3))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonCustomer))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}