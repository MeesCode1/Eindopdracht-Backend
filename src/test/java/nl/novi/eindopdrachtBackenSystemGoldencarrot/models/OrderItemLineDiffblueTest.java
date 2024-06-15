package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class OrderItemLineDiffblueTest {
    /**
     * Method under test: {@link OrderItemLine#calculateTotalPrice()}
     */
    @Test
    void testCalculateTotalPrice() {
        // Arrange
        Product product = mock(Product.class);
        when(product.getPriceInEur()).thenReturn(10.0d);
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

        OrderItemLine orderItemLine = new OrderItemLine();
        orderItemLine.setProduct(product);

        // Act
        Double actualCalculateTotalPriceResult = orderItemLine.calculateTotalPrice();

        // Assert
        verify(product).getPriceInEur();
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
        assertEquals(0.0d, actualCalculateTotalPriceResult.doubleValue());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link OrderItemLine#setId(Long)}
     *   <li>{@link OrderItemLine#setOrder(Order)}
     *   <li>{@link OrderItemLine#setQuantity(int)}
     *   <li>{@link OrderItemLine#setTotalPrice(Double)}
     *   <li>{@link OrderItemLine#getId()}
     *   <li>{@link OrderItemLine#getOrder()}
     *   <li>{@link OrderItemLine#getProduct()}
     *   <li>{@link OrderItemLine#getQuantity()}
     *   <li>{@link OrderItemLine#getTotalPrice()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws UnsupportedEncodingException {
        // Arrange
        OrderItemLine orderItemLine = new OrderItemLine();

        // Act
        orderItemLine.setId(1L);
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setBankAccount("3");
        customer3.setCompany("Company");
        customer3.setDob(LocalDate.of(1970, 1, 1));
        customer3.setEmailAddress("42 Main St");
        customer3.setFirstName("Jane");
        customer3.setId(1L);
        customer3.setLastName("Doe");
        customer3.setOrders(new ArrayList<>());
        customer3.setPhoneNumber("6625550144");
        Invoice invoice = new Invoice();
        invoice.setCustomer(new Customer());
        invoice.setCustomerCompany("Customer Name");
        invoice.setId(1L);
        invoice.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice.setOrder(new Order());
        invoice.setOrderNumber(10);
        Order order = new Order();
        order.setCustomer(customer3);
        order.setId(1L);
        order.setInvoice(invoice);
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderTime("Order Time");
        order.setProducts(new ArrayList<>());
        order.setTotalPriceInEur(10.0d);
        Invoice invoice2 = new Invoice();
        invoice2.setCustomer(customer2);
        invoice2.setCustomerCompany("Customer Name");
        invoice2.setId(1L);
        invoice2.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice2.setOrder(order);
        invoice2.setOrderNumber(10);
        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        orderItemLine.setOrder(order2);
        orderItemLine.setQuantity(1);
        orderItemLine.setTotalPrice(10.0d);
        Long actualId = orderItemLine.getId();
        Order actualOrder = orderItemLine.getOrder();
        orderItemLine.getProduct();
        int actualQuantity = orderItemLine.getQuantity();

        // Assert that nothing has changed
        assertEquals(1, actualQuantity);
        assertEquals(10.0d, orderItemLine.getTotalPrice().doubleValue());
        assertEquals(1L, actualId.longValue());
        assertSame(order2, actualOrder);
    }
}
