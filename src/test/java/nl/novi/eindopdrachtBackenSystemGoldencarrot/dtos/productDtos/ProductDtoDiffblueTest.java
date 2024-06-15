package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDtoDiffblueTest {
    /**
     * Method under test: {@link ProductDto#getInStock()}
     */
    @Test
    void testGetInStock() {
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

        // Act and Assert
        assertEquals(1, productDto.getInStock());
    }

    /**
     * Method under test: {@link ProductDto#setInStock(int)}
     */
    @Test
    void testSetInStock() {
        // Arrange
        ProductDto productDto = new ProductDto();

        // Act
        productDto.setInStock(1);

        // Assert
        assertEquals(1, productDto.inStock.intValue());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link ProductDto#setCategory(String)}
     *   <li>{@link ProductDto#setDescription(String)}
     *   <li>{@link ProductDto#setId(Long)}
     *   <li>{@link ProductDto#setName(String)}
     *   <li>{@link ProductDto#setPackedPerUnit(String)}
     *   <li>{@link ProductDto#setPriceInEur(Double)}
     *   <li>{@link ProductDto#setShortDescription(String)}
     *   <li>{@link ProductDto#getCategory()}
     *   <li>{@link ProductDto#getDescription()}
     *   <li>{@link ProductDto#getId()}
     *   <li>{@link ProductDto#getName()}
     *   <li>{@link ProductDto#getPackedPerUnit()}
     *   <li>{@link ProductDto#getPriceInEur()}
     *   <li>{@link ProductDto#getShortDescription()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ProductDto productDto = new ProductDto();

        // Act
        productDto.setCategory("Category");
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPackedPerUnit("Packed Per Unit");
        productDto.setPriceInEur(10.0d);
        productDto.setShortDescription("Short Description");
        String actualCategory = productDto.getCategory();
        String actualDescription = productDto.getDescription();
        Long actualId = productDto.getId();
        String actualName = productDto.getName();
        String actualPackedPerUnit = productDto.getPackedPerUnit();
        Double actualPriceInEur = productDto.getPriceInEur();

        // Assert that nothing has changed
        assertEquals("Category", actualCategory);
        assertEquals("Name", actualName);
        assertEquals("Packed Per Unit", actualPackedPerUnit);
        assertEquals("Short Description", productDto.getShortDescription());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(10.0d, actualPriceInEur.doubleValue());
        assertEquals(1L, actualId.longValue());
    }
}
