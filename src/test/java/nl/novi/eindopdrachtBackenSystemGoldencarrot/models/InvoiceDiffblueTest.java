package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class InvoiceDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Invoice#setCustomer(Customer)}
     *   <li>{@link Invoice#setCustomerCompany(String)}
     *   <li>{@link Invoice#setId(Long)}
     *   <li>{@link Invoice#setInvoiceData(byte[])}
     *   <li>{@link Invoice#setOrder(Order)}
     *   <li>{@link Invoice#setOrderNumber(Integer)}
     *   <li>{@link Invoice#getCustomer()}
     *   <li>{@link Invoice#getCustomerCompany()}
     *   <li>{@link Invoice#getId()}
     *   <li>{@link Invoice#getInvoiceData()}
     *   <li>{@link Invoice#getOrder()}
     *   <li>{@link Invoice#getOrderNumber()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws UnsupportedEncodingException {
        // Arrange
        Invoice invoice = new Invoice();

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
        invoice.setCustomer(customer);
        invoice.setCustomerCompany("Customer Name");
        invoice.setId(1L);
        byte[] invoiceData = "AXAXAXAX".getBytes("UTF-8");
        invoice.setInvoiceData(invoiceData);
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
        Invoice invoice2 = new Invoice();
        invoice2.setCustomer(new Customer());
        invoice2.setCustomerCompany("Customer Name");
        invoice2.setId(1L);
        invoice2.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice2.setOrder(new Order());
        invoice2.setOrderNumber(10);
        Order order = new Order();
        order.setCustomer(customer4);
        order.setId(1L);
        order.setInvoice(invoice2);
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderTime("Order Time");
        order.setProducts(new ArrayList<>());
        order.setTotalPriceInEur(10.0d);
        Invoice invoice3 = new Invoice();
        invoice3.setCustomer(customer3);
        invoice3.setCustomerCompany("Customer Name");
        invoice3.setId(1L);
        invoice3.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice3.setOrder(order);
        invoice3.setOrderNumber(10);
        Order order2 = new Order();
        order2.setCustomer(customer2);
        order2.setId(1L);
        order2.setInvoice(invoice3);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        invoice.setOrder(order2);
        invoice.setOrderNumber(10);
        Customer actualCustomer = invoice.getCustomer();
        String actualCustomerCompany = invoice.getCustomerCompany();
        Long actualId = invoice.getId();
        byte[] actualInvoiceData = invoice.getInvoiceData();
        Order actualOrder = invoice.getOrder();

        // Assert that nothing has changed
        assertEquals("Customer Name", actualCustomerCompany);
        assertEquals(10, invoice.getOrderNumber().intValue());
        assertEquals(1L, actualId.longValue());
        assertSame(customer, actualCustomer);
        assertSame(order2, actualOrder);
        assertSame(invoiceData, actualInvoiceData);
    }
}
