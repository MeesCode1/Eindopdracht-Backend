package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemLineTest {

    private OrderItemLine orderItemLine;

    @BeforeEach
    void setUpOrderItemLine() {
        orderItemLine = new OrderItemLine();
    }

//    @Test
//    void getId() {
//    }
//    @Test
//    void shouldSaveAndReturnCorrectId() {
//        Long id = 1L;
//        orderItemLine.setId(id);
//        assertEquals(id, orderItemLine.getId());
//    }

    @Test
    void shouldSaveAndReturnCorrectProduct() {
        Product product = new Product();
        orderItemLine.setProduct(product);
        assertEquals(product, orderItemLine.getProduct());
    }

    @Test
    void shouldSaveAndReturnCorrectQuantity() {
        int quantity = 2;
        orderItemLine.setQuantity(quantity);
        assertEquals(quantity, orderItemLine.getQuantity());
    }

    @Test
    void shouldSaveAndReturnCorrectTotalPrice() {
        Double totalPrice = 100.0;
        orderItemLine.setTotalPrice(totalPrice);
        assertEquals(totalPrice, orderItemLine.getTotalPrice());
    }

    @Test
    void shouldCalculateTotalPriceCorrectly() {
        Product product = new Product();
        product.setPriceInEur(50.0);
        orderItemLine.setProduct(product);
        orderItemLine.setQuantity(2);

        assertEquals(100.0, orderItemLine.calculateTotalPrice(), 0.001);
    }

    @Test
    void shouldSaveAndReturnCorrectOrder() {
        Order order = new Order();
        orderItemLine.setOrder(order);
        assertEquals(order, orderItemLine.getOrder());
    }
}