package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemLineDtoDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link OrderItemLineDto#setId(Long)}
     *   <li>{@link OrderItemLineDto#setProductName(String)}
     *   <li>{@link OrderItemLineDto#setProductPriceInEur(double)}
     *   <li>{@link OrderItemLineDto#setQuantity(int)}
     *   <li>{@link OrderItemLineDto#setShortDescriptionProduct(String)}
     *   <li>{@link OrderItemLineDto#setTotalPrice(double)}
     *   <li>{@link OrderItemLineDto#getId()}
     *   <li>{@link OrderItemLineDto#getProductName()}
     *   <li>{@link OrderItemLineDto#getProductPriceInEur()}
     *   <li>{@link OrderItemLineDto#getQuantity()}
     *   <li>{@link OrderItemLineDto#getShortDescriptionProduct()}
     *   <li>{@link OrderItemLineDto#getTotalPrice()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        OrderItemLineDto orderItemLineDto = new OrderItemLineDto();

        // Act
        orderItemLineDto.setId(1L);
        orderItemLineDto.setProductName("Product Name");
        orderItemLineDto.setProductPriceInEur(10.0d);
        orderItemLineDto.setQuantity(1);
        orderItemLineDto.setShortDescriptionProduct("Short Description Product");
        orderItemLineDto.setTotalPrice(10.0d);
        Long actualId = orderItemLineDto.getId();
        String actualProductName = orderItemLineDto.getProductName();
        double actualProductPriceInEur = orderItemLineDto.getProductPriceInEur();
        int actualQuantity = orderItemLineDto.getQuantity();
        String actualShortDescriptionProduct = orderItemLineDto.getShortDescriptionProduct();

        // Assert that nothing has changed
        assertEquals("Product Name", actualProductName);
        assertEquals("Short Description Product", actualShortDescriptionProduct);
        assertEquals(1, actualQuantity);
        assertEquals(10.0d, actualProductPriceInEur);
        assertEquals(10.0d, orderItemLineDto.getTotalPrice());
        assertEquals(1L, actualId.longValue());
    }
}
