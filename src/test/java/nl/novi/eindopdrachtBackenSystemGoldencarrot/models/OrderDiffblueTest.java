package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrderDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Order#setCustomer(Customer)}
     *   <li>{@link Order#setId(Long)}
     *   <li>{@link Order#setInvoice(Invoice)}
     *   <li>{@link Order#setOrderDate(LocalDate)}
     *   <li>{@link Order#setOrderTime(String)}
     *   <li>{@link Order#setProducts(List)}
     *   <li>{@link Order#setTotalPriceInEur(double)}
     *   <li>{@link Order#getCustomer()}
     *   <li>{@link Order#getId()}
     *   <li>{@link Order#getInvoice()}
     *   <li>{@link Order#getOrderDate()}
     *   <li>{@link Order#getOrderTime()}
     *   <li>{@link Order#getProducts()}
     *   <li>{@link Order#getTotalPriceInEur()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws UnsupportedEncodingException {
        // Arrange
        Order order = new Order();

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

        // Act
        order.setCustomer(customer);
        order.setId(1L);
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
        Customer customer4 = new Customer();
        customer4.setAddress("42 Main St");
        customer4.setBankAccount("3");
        customer4.setCompany("Company");
        customer4.setDob(LocalDate.of(1970, 1, 1));
        customer4.setEmailAddress("42 Main St");
        customer4.setFirstName("Jane");
        customer4.setId(1L);
        customer4.setLastName("Doe");
        customer4.setOrders(new ArrayList<>());
        customer4.setPhoneNumber("6625550144");
        Order order2 = new Order();
        order2.setCustomer(new Customer());
        order2.setId(1L);
        order2.setInvoice(new Invoice());
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer4);
        invoice.setCustomerCompany("Customer Name");
        invoice.setId(1L);
        invoice.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice.setOrder(order2);
        invoice.setOrderNumber(10);
        Order order3 = new Order();
        order3.setCustomer(customer3);
        order3.setId(1L);
        order3.setInvoice(invoice);
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderTime("Order Time");
        order3.setProducts(new ArrayList<>());
        order3.setTotalPriceInEur(10.0d);
        Invoice invoice2 = new Invoice();
        invoice2.setCustomer(customer2);
        invoice2.setCustomerCompany("Customer Name");
        invoice2.setId(1L);
        invoice2.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice2.setOrder(order3);
        invoice2.setOrderNumber(10);
        order.setInvoice(invoice2);
        LocalDate orderDate = LocalDate.of(1970, 1, 1);
        order.setOrderDate(orderDate);
        order.setOrderTime("Order Time");
        ArrayList<OrderItemLine> products = new ArrayList<>();
        order.setProducts(products);
        order.setTotalPriceInEur(10.0d);
        Customer actualCustomer = order.getCustomer();
        Long actualId = order.getId();
        Invoice actualInvoice = order.getInvoice();
        LocalDate actualOrderDate = order.getOrderDate();
        String actualOrderTime = order.getOrderTime();
        List<OrderItemLine> actualProducts = order.getProducts();

        // Assert that nothing has changed
        assertEquals("Order Time", actualOrderTime);
        assertEquals(10.0d, order.getTotalPriceInEur());
        assertEquals(1L, actualId.longValue());
        assertSame(products, actualProducts);
        assertSame(customer, actualCustomer);
        assertSame(invoice2, actualInvoice);
        assertSame(orderDate, actualOrderDate);
    }
}
