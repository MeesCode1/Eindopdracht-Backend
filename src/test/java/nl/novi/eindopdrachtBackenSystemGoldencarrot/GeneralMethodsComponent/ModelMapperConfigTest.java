package nl.novi.eindopdrachtBackenSystemGoldencarrot.GeneralMethodsComponent;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent.ModelMapperConfig;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelMapperConfigTest {

    @Test
      void shouldReturnDtoOfProduct() {
          Product product = new Product();
          product.setId(1L);
          product.setName("TestProduct");
          product.setCategory("TestCategory");
          product.setPriceInEur(10.0);
          product.setPackedPerUnit("PerUnit");
          product.setInStock(100);
          product.setShortDescription("Short Description");
          product.setDescription("Product Description");

          ProductDto result = ModelMapperConfig.mappingToDtoProduct(product);

          assertEquals(product.getId(), result.getId());
          assertEquals(product.getName(), result.getName());
          assertEquals(product.getCategory(), result.getCategory());
          assertEquals(product.getPriceInEur(), result.getPriceInEur());
          assertEquals(product.getPackedPerUnit(), result.getPackedPerUnit());
          assertEquals(product.getInStock(), result.getInStock());
          assertEquals(product.getShortDescription(), result.getShortDescription());
          assertEquals(product.getDescription(), result.getDescription());
      }

      @Test
    void shouldReturnProductOfDto(){

          ProductDto pdto = new ProductDto();
          pdto.setId(1L);
          pdto.setName("TestProduct");
          pdto.setCategory("TestCategory");
          pdto.setPriceInEur(10.0);
          pdto.setPackedPerUnit("PerUnit");
          pdto.setInStock(100);
          pdto.setShortDescription("Short Description");
          pdto.setDescription("Product Description");

          Product result = ModelMapperConfig.mappingToEntityProduct(pdto);

          assertEquals(pdto.getId(), result.getId());
          assertEquals(pdto.getName(), result.getName());
          assertEquals(pdto.getCategory(), result.getCategory());
          assertEquals(pdto.getPriceInEur(), result.getPriceInEur());
          assertEquals(pdto.getPackedPerUnit(), result.getPackedPerUnit());
          assertEquals(pdto.getInStock(), result.getInStock());
          assertEquals(pdto.getShortDescription(), result.getShortDescription());
          assertEquals(pdto.getDescription(), result.getDescription());
      }

      }

