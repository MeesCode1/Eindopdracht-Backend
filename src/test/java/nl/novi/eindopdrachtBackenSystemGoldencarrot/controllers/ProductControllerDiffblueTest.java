package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoDecreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoIncreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductController#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory() throws Exception {
        // Arrange
        when(productService.getProductsByCategory(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/category/{category}",
                "Category");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory2() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.getProductByName(Mockito.<String>any())).thenReturn(productDto);
        when(productService.getProductsByCategory(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/category/{category}", "",
                "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"category\":\"Category\",\"priceInEur\":10.0,\"packedPerUnit\":\"Packed Per"
                                        + " Unit\",\"inStock\":1,\"shortDescription\":\"Short Description\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }

    /**
     * Method under test:
     * {@link ProductController#createProduct(ProductDto, BindingResult)}
     */
    @Test
    void testCreateProduct() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.createProduct(Mockito.<ProductDto>any())).thenReturn(productDto);

        ProductDto productDto2 = new ProductDto();
        productDto2.setCategory("Category");
        productDto2.setDescription("The characteristics of someone or something");
        productDto2.setId(1L);
        productDto2.setInStock(1);
        productDto2.setName("Name");
        productDto2.setPackedPerUnit("Packed Per Unit");
        productDto2.setPriceInEur(10.0d);
        productDto2.setShortDescription("Short Description");
        String content = (new ObjectMapper()).writeValueAsString(productDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"category\":\"Category\",\"priceInEur\":10.0,\"packedPerUnit\":\"Packed Per"
                                        + " Unit\",\"inStock\":1,\"shortDescription\":\"Short Description\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/products/1"));
    }

    /**
     * Method under test:
     * {@link ProductController#createProduct(ProductDto, BindingResult)}
     */
    @Test
    void testCreateProduct2() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.createProduct(Mockito.<ProductDto>any())).thenReturn(productDto);

        ProductDto productDto2 = new ProductDto();
        productDto2.setCategory(null);
        productDto2.setDescription("The characteristics of someone or something");
        productDto2.setId(1L);
        productDto2.setInStock(1);
        productDto2.setName("Name");
        productDto2.setPackedPerUnit("Packed Per Unit");
        productDto2.setPriceInEur(10.0d);
        productDto2.setShortDescription("Short Description");
        String content = (new ObjectMapper()).writeValueAsString(productDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("category: must not be blank\n"));
    }

    /**
     * Method under test:
     * {@link ProductController#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.updateProduct(Mockito.<String>any(), Mockito.<ProductDtoUpdate>any())).thenReturn(productDto);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/products/{productname}", "Productname")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ProductDtoUpdate()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"category\":\"Category\",\"priceInEur\":10.0,\"packedPerUnit\":\"Packed Per"
                                        + " Unit\",\"inStock\":1,\"shortDescription\":\"Short Description\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        when(productService.deleteProduct(Mockito.<Long>any())).thenReturn("Delete Product");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product: \"Delete Product\"deleted from database"));
    }

    /**
     * Method under test:
     * {@link ProductController#decreaseProductStock(String, ProductDtoDecreaseStock, BindingResult)}
     */
    @Test
    void testDecreaseProductStock() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/products/decreasestock/{productname}", "Productname")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ProductDtoDecreaseStock()));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("reduceFromStock: must not be null\n"));
    }

    /**
     * Method under test:
     * {@link ProductController#decreaseProductStock(String, ProductDtoDecreaseStock, BindingResult)}
     */
    @Test
    void testDecreaseProductStock2() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.updateProduct(Mockito.<String>any(), Mockito.<ProductDtoUpdate>any())).thenReturn(productDto);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/products/decreasestock/{productname}", "", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ProductDtoDecreaseStock()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"category\":\"Category\",\"priceInEur\":10.0,\"packedPerUnit\":\"Packed Per"
                                        + " Unit\",\"inStock\":1,\"shortDescription\":\"Short Description\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }

    /**
     * Method under test: {@link ProductController#getProduct(String)}
     */
    @Test
    void testGetProduct() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.getProductByName(Mockito.<String>any())).thenReturn(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{productname}", "Productname");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"category\":\"Category\",\"priceInEur\":10.0,\"packedPerUnit\":\"Packed Per"
                                        + " Unit\",\"inStock\":1,\"shortDescription\":\"Short Description\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }

    /**
     * Method under test:
     * {@link ProductController#increaseProductStock(String, ProductDtoIncreaseStock, BindingResult)}
     */
    @Test
    void testIncreaseProductStock() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/products/increasestock/{productname}", "Productname")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ProductDtoIncreaseStock()));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("addToStock: must not be null\n"));
    }

    /**
     * Method under test:
     * {@link ProductController#increaseProductStock(String, ProductDtoIncreaseStock, BindingResult)}
     */
    @Test
    void testIncreaseProductStock2() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setInStock(1);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        when(productService.updateProduct(Mockito.<String>any(), Mockito.<ProductDtoUpdate>any())).thenReturn(productDto);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/products/increasestock/{productname}", "", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ProductDtoIncreaseStock()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"category\":\"Category\",\"priceInEur\":10.0,\"packedPerUnit\":\"Packed Per"
                                        + " Unit\",\"inStock\":1,\"shortDescription\":\"Short Description\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }

    /**
     * Method under test: {@link ProductController#showProducts()}
     */
    @Test
    void testShowProducts() throws Exception {
        // Arrange
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
