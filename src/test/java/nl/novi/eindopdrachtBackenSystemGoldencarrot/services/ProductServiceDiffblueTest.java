package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoDecreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoIncreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Product;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceDiffblueTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    /**
     * Method under test: {@link ProductService#createProduct(ProductDto)}
     */
    @Test
    void testCreateProduct() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        when(product.getPriceInEur()).thenReturn(10.0d);
        when(product.getId()).thenReturn(1L);
        when(product.getCategory()).thenReturn("Category");
        when(product.getDescription()).thenReturn("The characteristics of someone or something");
        when(product.getName()).thenReturn("Name");
        when(product.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        ProductDto pdto = new ProductDto();
        pdto.setCategory("Category");
        pdto.setDescription("The characteristics of someone or something");
        pdto.setId(1L);
        pdto.setInStock(1);
        pdto.setName("Name");
        pdto.setPackedPerUnit("Packed Per Unit");
        pdto.setPriceInEur(10.0d);
        pdto.setShortDescription("Short Description");

        // Act
        ProductDto actualCreateProductResult = productService.createProduct(pdto);

        // Assert
        verify(product).getCategory();
        verify(product).getDescription();
        verify(product).getId();
        verify(product).getInStock();
        verify(product).getName();
        verify(product).getPackedPerUnit();
        verify(product).getPriceInEur();
        verify(product).getShortDescription();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualCreateProductResult.getCategory());
        assertEquals("Name", actualCreateProductResult.getName());
        assertEquals("Packed Per Unit", actualCreateProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualCreateProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualCreateProductResult.getDescription());
        assertEquals(1, actualCreateProductResult.inStock.intValue());
        assertEquals(10.0d, actualCreateProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualCreateProductResult.getId().longValue());
    }

    /**
     * Method under test: {@link ProductService#getProductByName(String)}
     */
    @Test
    void testGetProductByName() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        when(product.getPriceInEur()).thenReturn(10.0d);
        when(product.getId()).thenReturn(1L);
        when(product.getCategory()).thenReturn("Category");
        when(product.getDescription()).thenReturn("The characteristics of someone or something");
        when(product.getName()).thenReturn("Name");
        when(product.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        ProductDto actualProductByName = productService.getProductByName("Productname");

        // Assert
        verify(product).getCategory();
        verify(product).getDescription();
        verify(product).getId();
        verify(product).getInStock();
        verify(product).getName();
        verify(product).getPackedPerUnit();
        verify(product).getPriceInEur();
        verify(product).getShortDescription();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        assertEquals("Category", actualProductByName.getCategory());
        assertEquals("Name", actualProductByName.getName());
        assertEquals("Packed Per Unit", actualProductByName.getPackedPerUnit());
        assertEquals("Short Description", actualProductByName.getShortDescription());
        assertEquals("The characteristics of someone or something", actualProductByName.getDescription());
        assertEquals(1, actualProductByName.inStock.intValue());
        assertEquals(10.0d, actualProductByName.getPriceInEur().doubleValue());
        assertEquals(1L, actualProductByName.getId().longValue());
    }

    /**
     * Method under test: {@link ProductService#getProductByName(String)}
     */
    @Test
    void testGetProductByName2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductByName("Productname"));
        verify(productRepository).findByName(eq("Productname"));
    }

    /**
     * Method under test: {@link ProductService#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory() {
        // Arrange
        when(productRepository.findByCategory(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ProductDto> actualProductsByCategory = productService.getProductsByCategory("Category");

        // Assert
        verify(productRepository).findByCategory(eq("Category"));
        assertTrue(actualProductsByCategory.isEmpty());
    }

    /**
     * Method under test: {@link ProductService#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory2() {
        // Arrange
        when(productRepository.findByCategory(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productService.getProductsByCategory("Category"));
        verify(productRepository).findByCategory(eq("Category"));
    }

    /**
     * Method under test: {@link ProductService#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory3() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        when(product.getPriceInEur()).thenReturn(10.0d);
        when(product.getId()).thenReturn(1L);
        when(product.getCategory()).thenReturn("Category");
        when(product.getDescription()).thenReturn("The characteristics of someone or something");
        when(product.getName()).thenReturn("Name");
        when(product.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByCategory(Mockito.<String>any())).thenReturn(productList);

        // Act
        List<ProductDto> actualProductsByCategory = productService.getProductsByCategory("Category");

        // Assert
        verify(product).getCategory();
        verify(product).getDescription();
        verify(product).getId();
        verify(product).getInStock();
        verify(product).getName();
        verify(product).getPackedPerUnit();
        verify(product).getPriceInEur();
        verify(product).getShortDescription();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByCategory(eq("Category"));
        assertEquals(1, actualProductsByCategory.size());
    }

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ProductDto> actualAllProducts = productService.getAllProducts();

        // Assert
        verify(productRepository).findAll();
        assertTrue(actualAllProducts.isEmpty());
    }

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
        // Arrange
        when(productRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productService.getAllProducts());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts3() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        when(product.getPriceInEur()).thenReturn(10.0d);
        when(product.getId()).thenReturn(1L);
        when(product.getCategory()).thenReturn("Category");
        when(product.getDescription()).thenReturn("The characteristics of someone or something");
        when(product.getName()).thenReturn("Name");
        when(product.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<ProductDto> actualAllProducts = productService.getAllProducts();

        // Assert
        verify(product).getCategory();
        verify(product).getDescription();
        verify(product).getId();
        verify(product).getInStock();
        verify(product).getName();
        verify(product).getPackedPerUnit();
        verify(product).getPriceInEur();
        verify(product).getShortDescription();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findAll();
        assertEquals(1, actualAllProducts.size());
    }

    /**
     * Method under test:
     * {@link ProductService#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoUpdate pdto = new ProductDtoUpdate();
        pdto.category = "Category";
        pdto.description = "The characteristics of someone or something";
        pdto.inStock = 1;
        pdto.name = "Name";
        pdto.packedPerUnit = "Packed Per Unit";
        pdto.priceInEur = 10.0d;
        pdto.shortDescription = "Short Description";

        // Act
        ProductDto actualUpdateProductResult = productService.updateProduct("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product, atLeast(1)).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product, atLeast(1)).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product, atLeast(1)).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product, atLeast(1)).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product, atLeast(1)).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product, atLeast(1)).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualUpdateProductResult.getCategory());
        assertEquals("Name", actualUpdateProductResult.getName());
        assertEquals("Packed Per Unit", actualUpdateProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualUpdateProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualUpdateProductResult.getDescription());
        assertEquals(1, actualUpdateProductResult.inStock.intValue());
        assertEquals(10.0d, actualUpdateProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualUpdateProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        ProductDtoUpdate pdto = new ProductDtoUpdate();
        pdto.category = "Category";
        pdto.description = "The characteristics of someone or something";
        pdto.inStock = 1;
        pdto.name = "Name";
        pdto.packedPerUnit = "Packed Per Unit";
        pdto.priceInEur = 10.0d;
        pdto.shortDescription = "Short Description";

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct("Productname", pdto));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
    }

    /**
     * Method under test:
     * {@link ProductService#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct3() {
        // Arrange
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoUpdate pdto = new ProductDtoUpdate();
        pdto.category = "Category";
        pdto.description = null;
        pdto.inStock = 1;
        pdto.name = "Name";
        pdto.packedPerUnit = "Packed Per Unit";
        pdto.priceInEur = 10.0d;
        pdto.shortDescription = "Short Description";

        // Act
        ProductDto actualUpdateProductResult = productService.updateProduct("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product, atLeast(1)).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product, atLeast(1)).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product, atLeast(1)).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product, atLeast(1)).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product, atLeast(1)).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualUpdateProductResult.getCategory());
        assertEquals("Name", actualUpdateProductResult.getName());
        assertEquals("Packed Per Unit", actualUpdateProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualUpdateProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualUpdateProductResult.getDescription());
        assertEquals(1, actualUpdateProductResult.inStock.intValue());
        assertEquals(10.0d, actualUpdateProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualUpdateProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct4() {
        // Arrange
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoUpdate pdto = new ProductDtoUpdate();
        pdto.category = "Category";
        pdto.description = "The characteristics of someone or something";
        pdto.inStock = 1;
        pdto.name = null;
        pdto.packedPerUnit = "Packed Per Unit";
        pdto.priceInEur = 10.0d;
        pdto.shortDescription = "Short Description";

        // Act
        ProductDto actualUpdateProductResult = productService.updateProduct("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product, atLeast(1)).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product, atLeast(1)).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product, atLeast(1)).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product, atLeast(1)).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product, atLeast(1)).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualUpdateProductResult.getCategory());
        assertEquals("Name", actualUpdateProductResult.getName());
        assertEquals("Packed Per Unit", actualUpdateProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualUpdateProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualUpdateProductResult.getDescription());
        assertEquals(1, actualUpdateProductResult.inStock.intValue());
        assertEquals(10.0d, actualUpdateProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualUpdateProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct5() {
        // Arrange
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoUpdate pdto = new ProductDtoUpdate();
        pdto.category = "Category";
        pdto.description = "The characteristics of someone or something";
        pdto.inStock = 1;
        pdto.name = "Name";
        pdto.packedPerUnit = null;
        pdto.priceInEur = 10.0d;
        pdto.shortDescription = "Short Description";

        // Act
        ProductDto actualUpdateProductResult = productService.updateProduct("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product, atLeast(1)).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product, atLeast(1)).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product, atLeast(1)).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product, atLeast(1)).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product, atLeast(1)).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualUpdateProductResult.getCategory());
        assertEquals("Name", actualUpdateProductResult.getName());
        assertEquals("Packed Per Unit", actualUpdateProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualUpdateProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualUpdateProductResult.getDescription());
        assertEquals(1, actualUpdateProductResult.inStock.intValue());
        assertEquals(10.0d, actualUpdateProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualUpdateProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#updateProduct(String, ProductDtoUpdate)}
     */
    @Test
    void testUpdateProduct6() {
        // Arrange
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoUpdate pdto = new ProductDtoUpdate();
        pdto.category = "Category";
        pdto.description = "The characteristics of someone or something";
        pdto.inStock = 1;
        pdto.name = "Name";
        pdto.packedPerUnit = "Packed Per Unit";
        pdto.priceInEur = 10.0d;
        pdto.shortDescription = null;

        // Act
        ProductDto actualUpdateProductResult = productService.updateProduct("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product, atLeast(1)).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product, atLeast(1)).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product, atLeast(1)).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product, atLeast(1)).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product, atLeast(1)).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualUpdateProductResult.getCategory());
        assertEquals("Name", actualUpdateProductResult.getName());
        assertEquals("Packed Per Unit", actualUpdateProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualUpdateProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualUpdateProductResult.getDescription());
        assertEquals(1, actualUpdateProductResult.inStock.intValue());
        assertEquals(10.0d, actualUpdateProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualUpdateProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#increaseProductStock(String, ProductDtoIncreaseStock)}
     */
    @Test
    void testIncreaseProductStock() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoIncreaseStock pdto = new ProductDtoIncreaseStock();
        pdto.addToStock = 1;

        // Act
        ProductDto actualIncreaseProductStockResult = productService.increaseProductStock("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product, atLeast(1)).setInStock(anyInt());
        verify(product2).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualIncreaseProductStockResult.getCategory());
        assertEquals("Name", actualIncreaseProductStockResult.getName());
        assertEquals("Packed Per Unit", actualIncreaseProductStockResult.getPackedPerUnit());
        assertEquals("Short Description", actualIncreaseProductStockResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualIncreaseProductStockResult.getDescription());
        assertEquals(1, actualIncreaseProductStockResult.inStock.intValue());
        assertEquals(10.0d, actualIncreaseProductStockResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualIncreaseProductStockResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#increaseProductStock(String, ProductDtoIncreaseStock)}
     */
    @Test
    void testIncreaseProductStock2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        ProductDtoIncreaseStock pdto = new ProductDtoIncreaseStock();
        pdto.addToStock = 1;

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.increaseProductStock("Productname", pdto));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
    }

    /**
     * Method under test:
     * {@link ProductService#decreaseProductStock(String, ProductDtoDecreaseStock)}
     */
    @Test
    void testDecreaseProductStock() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getPriceInEur()).thenReturn(10.0d);
        when(product2.getId()).thenReturn(1L);
        when(product2.getCategory()).thenReturn("Category");
        when(product2.getDescription()).thenReturn("The characteristics of someone or something");
        when(product2.getName()).thenReturn("Name");
        when(product2.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(product2.getShortDescription()).thenReturn("Short Description");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        ProductDtoDecreaseStock pdto = new ProductDtoDecreaseStock();
        pdto.reduceFromStock = 1;

        // Act
        ProductDto actualDecreaseProductStockResult = productService.decreaseProductStock("Productname", pdto);

        // Assert
        verify(product2).getCategory();
        verify(product2).getDescription();
        verify(product2).getId();
        verify(product2).getInStock();
        verify(product).getInStock();
        verify(product2).getName();
        verify(product2).getPackedPerUnit();
        verify(product2).getPriceInEur();
        verify(product2).getShortDescription();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product, atLeast(1)).setInStock(anyInt());
        verify(product2).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
        verify(productRepository).save(isA(Product.class));
        assertEquals("Category", actualDecreaseProductStockResult.getCategory());
        assertEquals("Name", actualDecreaseProductStockResult.getName());
        assertEquals("Packed Per Unit", actualDecreaseProductStockResult.getPackedPerUnit());
        assertEquals("Short Description", actualDecreaseProductStockResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualDecreaseProductStockResult.getDescription());
        assertEquals(1, actualDecreaseProductStockResult.inStock.intValue());
        assertEquals(10.0d, actualDecreaseProductStockResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualDecreaseProductStockResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ProductService#decreaseProductStock(String, ProductDtoDecreaseStock)}
     */
    @Test
    void testDecreaseProductStock2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        ProductDtoDecreaseStock pdto = new ProductDtoDecreaseStock();
        pdto.reduceFromStock = 1;

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.decreaseProductStock("Productname", pdto));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Productname"));
    }

    /**
     * Method under test: {@link ProductService#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getName()).thenReturn("Name");
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepository).delete(Mockito.<Product>any());
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualDeleteProductResult = productService.deleteProduct(1L);

        // Assert
        verify(product).getName();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).delete(isA(Product.class));
        verify(productRepository).findById(eq(1L));
        assertEquals("Name", actualDeleteProductResult);
    }

    /**
     * Method under test: {@link ProductService#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProductService#lowerInStockNewOrder(String, int)}
     */
    @Test
    void testLowerInStockNewOrder() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save(Mockito.<Product>any())).thenThrow(new IllegalArgumentException("foo"));
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productService.lowerInStockNewOrder("Product Name", 1));
        verify(product).getInStock();
        verify(product, atLeast(1)).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Product Name"));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Method under test: {@link ProductService#lowerInStockNewOrder(String, int)}
     */
    @Test
    void testLowerInStockNewOrder2() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(1);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        productService.lowerInStockNewOrder("Product Name", 1);

        // Assert that nothing has changed
        verify(product).getInStock();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product, atLeast(1)).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Product Name"));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Method under test: {@link ProductService#lowerInStockNewOrder(String, int)}
     */
    @Test
    void testLowerInStockNewOrder3() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getInStock()).thenReturn(0);
        when(product.getName()).thenReturn("Name");
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        Product product2 = mock(Product.class);
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productService.lowerInStockNewOrder("Product Name", 1));
        verify(product, atLeast(1)).getInStock();
        verify(product).getName();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Product Name"));
    }

    /**
     * Method under test: {@link ProductService#lowerInStockNewOrder(String, int)}
     */
    @Test
    void testLowerInStockNewOrder4() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.lowerInStockNewOrder("Product Name", 1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#restoreInStockForChangedOrder(String, int)}
     */
    @Test
    void testRestoreInStockForChangedOrder() {
        // Arrange
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        Optional<Product> ofResult = Optional.of(product);
        Product product2 = mock(Product.class);
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        productService.restoreInStockForChangedOrder("Product Name", 1);

        // Assert that nothing has changed
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product, atLeast(1)).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Product Name"));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Method under test:
     * {@link ProductService#restoreInStockForChangedOrder(String, int)}
     */
    @Test
    void testRestoreInStockForChangedOrder2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        Product product = mock(Product.class);
        doNothing().when(product).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product).setCategory(Mockito.<String>any());
        doNothing().when(product).setDescription(Mockito.<String>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setInStock(anyInt());
        doNothing().when(product).setName(Mockito.<String>any());
        doNothing().when(product).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product).setShortDescription(Mockito.<String>any());
        product.lowerInStockForIncomingOrder(1);
        product.restoreInStockForChangedOrder(1);
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setInStock(1);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> productService.restoreInStockForChangedOrder("Product Name", 1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(productRepository).findByName(eq("Product Name"));
    }
}
