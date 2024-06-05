package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Product;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository repos;
    @InjectMocks
    ProductService service;


    @Test
    void shouldReturnCorrectProducts() {

        Product p = new Product();
        p.setName("Dorade side");
        p.setCategory("fish");
        p.setPriceInEur(36.12);
        p.setPackedPerUnit("c.a 600gr");
        p.setInStock(7);
        p.setDescription("fish descr");
        p.setShortDescription("fish short descr");

        Product p2 = new Product();
        p2.setName("Dorade side2");
        p2.setCategory("fish");
        p2.setPriceInEur(36.12);
        p2.setPackedPerUnit("c.a 600gr");
        p2.setInStock(5);
        p2.setDescription("fish descr");
        p2.setShortDescription("fish short descr");


        Mockito.when(repos.findByCategory(anyString())).thenReturn(List.of(p, p2));

        List<ProductDto> result = service.getProductsByCategory("fish");

        assertEquals("Dorade side", result.get(0).name);
        assertEquals("Dorade side2", result.get(1).name);
        assertEquals(36.12, result.get(0).priceInEur);
        assertEquals(36.12, result.get(1).priceInEur);
        assertEquals(7, result.get(0).inStock);
        assertEquals(5, result.get(1).inStock);
        assertEquals("fish descr", result.get(0).description);
        assertEquals("fish descr", result.get(1).description);
    }
}
