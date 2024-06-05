package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.security.JwtService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.ProductService;
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


@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
public class ProductControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    ProductService productService;

    @Test  //SERVICE-DSK
    @WithMockUser(username = "testUser", roles = "JOKERCEO")
    void shouldRetrieveCorrectProduct() throws Exception {

        ProductDto pdto = new ProductDto();
        pdto.name = "Dorade side";
        pdto.category = "fish";
        pdto.priceInEur = 36.12;
        pdto.packedPerUnit = "c.a 600gr";
        pdto.inStock = 7;
        pdto.description = "fish descr";
        pdto.shortDescription = "fish short descr";


        Mockito.when(productService.getProductByName("Dorade side")).thenReturn(pdto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/products/Dorade side"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Dorade side")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", is("fish")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceInEur", is(36.12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.packedPerUnit", is("c.a 600gr")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inStock", is(7)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("fish descr")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortDescription", is("fish short descr")));
    }
}
