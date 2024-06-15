package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrderDtoDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link OrderDto#setCustomerCompany(String)}
     *   <li>{@link OrderDto#setCustomerFirstName(String)}
     *   <li>{@link OrderDto#setCustomerLastName(String)}
     *   <li>{@link OrderDto#setId(Long)}
     *   <li>{@link OrderDto#setOrderDate(LocalDate)}
     *   <li>{@link OrderDto#setOrderTime(String)}
     *   <li>{@link OrderDto#setProducts(List)}
     *   <li>{@link OrderDto#setTotalPriceInEur(double)}
     *   <li>{@link OrderDto#getCustomerCompany()}
     *   <li>{@link OrderDto#getCustomerFirstName()}
     *   <li>{@link OrderDto#getCustomerLastName()}
     *   <li>{@link OrderDto#getId()}
     *   <li>{@link OrderDto#getOrderDate()}
     *   <li>{@link OrderDto#getOrderTime()}
     *   <li>{@link OrderDto#getProducts()}
     *   <li>{@link OrderDto#getTotalPriceInEur()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        OrderDto orderDto = new OrderDto();

        // Act
        orderDto.setCustomerCompany("Customer Company");
        orderDto.setCustomerFirstName("Jane");
        orderDto.setCustomerLastName("Doe");
        orderDto.setId(1L);
        LocalDate orderDate = LocalDate.of(1970, 1, 1);
        orderDto.setOrderDate(orderDate);
        orderDto.setOrderTime("Order Time");
        ArrayList<OrderItemLineDto> products = new ArrayList<>();
        orderDto.setProducts(products);
        orderDto.setTotalPriceInEur(10.0d);
        String actualCustomerCompany = orderDto.getCustomerCompany();
        String actualCustomerFirstName = orderDto.getCustomerFirstName();
        String actualCustomerLastName = orderDto.getCustomerLastName();
        Long actualId = orderDto.getId();
        LocalDate actualOrderDate = orderDto.getOrderDate();
        String actualOrderTime = orderDto.getOrderTime();
        List<OrderItemLineDto> actualProducts = orderDto.getProducts();

        // Assert that nothing has changed
        assertEquals("Customer Company", actualCustomerCompany);
        assertEquals("Doe", actualCustomerLastName);
        assertEquals("Jane", actualCustomerFirstName);
        assertEquals("Order Time", actualOrderTime);
        assertEquals(10.0d, orderDto.getTotalPriceInEur());
        assertEquals(1L, actualId.longValue());
        assertSame(products, actualProducts);
        assertSame(orderDate, actualOrderDate);
    }
}
