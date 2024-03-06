package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUpOrder() {
        order = new Order();
    }

    @Test
    void shouldSaveAndReturnCorrectId() {

    }

    @Test
    void shouldSaveAndReturnCorrectOrderDate() {
        LocalDate orderDate = LocalDate.of(2023, 11, 6);
        order.setOrderDate(orderDate);
        assertEquals(orderDate, order.getOrderDate());
    }

    @Test
    void shouldSaveAndReturnCorrectOrderTime() {
        order.setOrderTime("12:30:00");
        assertEquals("12:30:00", order.getOrderTime());
    }

    @Test
    void shouldSaveAndReturnCorrectTotalPriceInEur() {
        order.setTotalPriceInEur(50.0);
        assertEquals(50.0, order.getTotalPriceInEur(), 0.001);
    }

    @Test
    void shouldSaveAndReturnCorrectCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void shouldSaveAndReturnCorrectProducts() {
        List<OrderItemLine> products = new ArrayList<>();
        OrderItemLine oil1 = new OrderItemLine();
        OrderItemLine oil2 = new OrderItemLine();
        products.add(oil1);
        products.add(oil2);

        order.setProducts(products);

        List<OrderItemLine> resultProducts = order.getProducts();

        assertEquals(products, resultProducts);
    }
}