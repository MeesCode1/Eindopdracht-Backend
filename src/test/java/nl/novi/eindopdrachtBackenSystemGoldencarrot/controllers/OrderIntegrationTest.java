package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Transactional
class OrderIntegrationTest {

    @Value("${invoice.image.toppage.absolutpath}")
    String imageTop;

    @Value("${invoice.image.watermark.absolutpath}")
    String imageWatermark;

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
                                   {"productName": "Vine tomatoes", "quantity": 2},
                                   {"productName": "Basil", "quantity": 1}]
                     }
                """;


        Path imageTopPath = Paths.get("C:\\Users\\mees\\Downloads\\eindopdrachtBackenSystemGoldencarrot\\" +
                "eindopdrachtBackenSystemGoldencarrot\\src\\images\\goldencarrotImageTopPageInv.jpg");
        byte[] fileContent = Files.readAllBytes(imageTopPath);
        MockMultipartFile file = new MockMultipartFile("file",
                "goldencarrotImagekleinste.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                fileContent);

        Path imageWatermarkPath = Paths.get("C:\\Users\\mees\\Downloads\\eindopdrachtBackenSystemGoldencarrot\\" +
                "eindopdrachtBackenSystemGoldencarrot\\src\\images\\goldencarrotImageWmInv.jpg");
        byte[] fileContent2 = Files.readAllBytes(imageWatermarkPath);
        MockMultipartFile file2 = new MockMultipartFile("file",
                "Watermerk goldencarot factuur eindopdracht klein.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                fileContent2);

        this.mockMvc
                .perform(multipart("/image")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        this.mockMvc
                .perform(multipart("/image")
                        .file(file2)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonProduct1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonProduct2))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonProduct3))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJsonCustomer))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerCompany").value("Maze"))
                .andExpect(jsonPath("$.products[0].productName").value("Dorade side"))
                .andExpect(jsonPath("$.products[0].quantity").value(3))
                .andExpect(jsonPath("$.products[1].productName").value("Vine tomatoes"))
                .andExpect(jsonPath("$.products[1].quantity").value(2))
                .andExpect(jsonPath("$.products[2].productName").value("Basil"))
                .andExpect(jsonPath("$.products[2].quantity").value(1));
    }

}