package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDiffblueTest {
    /**
     * Method under test: {@link Product#getInStock()}
     */
    @Test
    void testGetInStock() {
        // Arrange
        Product product = new Product();
        product.setInStock(1);

        // Act and Assert
        assertEquals(1, product.getInStock());
    }

    /**
     * Method under test: {@link Product#lowerInStockForIncomingOrder(int)}
     */
    @Test
    void testLowerInStockForIncomingOrder() {
        // Arrange
        Product product = new Product();
        product.setInStock(1);

        // Act
        product.lowerInStockForIncomingOrder(1);

        // Assert
        assertEquals(0, product.getInStock());
    }

    /**
     * Method under test: {@link Product#restoreInStockForChangedOrder(int)}
     */
    @Test
    void testRestoreInStockForChangedOrder() {
        // Arrange
        Product product = new Product();
        product.setInStock(1);

        // Act
        product.restoreInStockForChangedOrder(1);

        // Assert
        assertEquals(2, product.getInStock());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Product#setCategory(String)}
     *   <li>{@link Product#setDescription(String)}
     *   <li>{@link Product#setId(Long)}
     *   <li>{@link Product#setName(String)}
     *   <li>{@link Product#setPackedPerUnit(String)}
     *   <li>{@link Product#setPriceInEur(Double)}
     *   <li>{@link Product#setShortDescription(String)}
     *   <li>{@link Product#getCategory()}
     *   <li>{@link Product#getDescription()}
     *   <li>{@link Product#getId()}
     *   <li>{@link Product#getName()}
     *   <li>{@link Product#getPackedPerUnit()}
     *   <li>{@link Product#getPriceInEur()}
     *   <li>{@link Product#getShortDescription()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Product product = new Product();

        // Act
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPackedPerUnit("Packed Per Unit");
        product.setPriceInEur(10.0d);
        product.setShortDescription("Short Description");
        String actualCategory = product.getCategory();
        String actualDescription = product.getDescription();
        Long actualId = product.getId();
        String actualName = product.getName();
        String actualPackedPerUnit = product.getPackedPerUnit();
        Double actualPriceInEur = product.getPriceInEur();

        // Assert that nothing has changed
        assertEquals("Category", actualCategory);
        assertEquals("Name", actualName);
        assertEquals("Packed Per Unit", actualPackedPerUnit);
        assertEquals("Short Description", product.getShortDescription());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(10.0d, actualPriceInEur.doubleValue());
        assertEquals(1L, actualId.longValue());
    }
}
