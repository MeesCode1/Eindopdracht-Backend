package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Invoice;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class SetTimeAndDateDiffblueTest {
    /**
     * Method under test: {@link SetTimeAndDate#SetOrderDateAndTime(Order)}
     */
    @Test
    void testSetOrderDateAndTime() throws UnsupportedEncodingException {
        // Arrange
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
        Order order2 = mock(Order.class);
        doNothing().when(order2).setCustomer(Mockito.<Customer>any());
        doNothing().when(order2).setId(Mockito.<Long>any());
        doNothing().when(order2).setInvoice(Mockito.<Invoice>any());
        doNothing().when(order2).setOrderDate(Mockito.<LocalDate>any());
        doNothing().when(order2).setOrderTime(Mockito.<String>any());
        doNothing().when(order2).setProducts(Mockito.<List<OrderItemLine>>any());
        doNothing().when(order2).setTotalPriceInEur(anyDouble());
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);

        // Act
        SetTimeAndDate.SetOrderDateAndTime(order2);

        // Assert
        verify(order2).setCustomer(isA(Customer.class));
        verify(order2).setId(eq(1L));
        verify(order2).setInvoice(isA(Invoice.class));
        verify(order2, atLeast(1)).setOrderDate(isA(LocalDate.class));
        verify(order2, atLeast(1)).setOrderTime(Mockito.<String>any());
        verify(order2).setProducts(isA(List.class));
        verify(order2).setTotalPriceInEur(eq(10.0d));
    }
}
