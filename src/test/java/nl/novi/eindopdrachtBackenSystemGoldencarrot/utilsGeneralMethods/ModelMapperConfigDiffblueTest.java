package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoOutput;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ModelMapperConfigDiffblueTest {
    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityCustomer(CustomerDto)}
     */
    @Test
    void testMappingToEntityCustomer() {
        // Arrange
        CustomerDto cDto = new CustomerDto();
        cDto.setAddress("42 Main St");
        cDto.setBankAccount("3");
        cDto.setCompany("Company");
        cDto.setDob(LocalDate.of(1970, 1, 1));
        cDto.setEmailAddress("42 Main St");
        cDto.setFirstName("Jane");
        cDto.setId(1L);
        cDto.setLastName("Doe");
        cDto.setPhoneNumber("6625550144");

        // Act
        Customer actualMappingToEntityCustomerResult = ModelMapperConfig.mappingToEntityCustomer(cDto);

        // Assert
        assertEquals("1970-01-01", actualMappingToEntityCustomerResult.getDob().toString());
        assertEquals("3", actualMappingToEntityCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityCustomerResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityCustomerResult.getPhoneNumber());
        assertEquals("Company", actualMappingToEntityCustomerResult.getCompany());
        assertEquals("Doe", actualMappingToEntityCustomerResult.getLastName());
        assertEquals("Jane", actualMappingToEntityCustomerResult.getFirstName());
        assertEquals(1L, actualMappingToEntityCustomerResult.getId().longValue());
        assertTrue(actualMappingToEntityCustomerResult.getOrders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityCustomer(CustomerDto)}
     */
    @Test
    void testMappingToEntityCustomer2() {
        // Arrange
        CustomerDto cDto = mock(CustomerDto.class);
        when(cDto.getId()).thenReturn(1L);
        when(cDto.getAddress()).thenReturn("42 Main St");
        when(cDto.getBankAccount()).thenReturn("3");
        when(cDto.getCompany()).thenReturn("Company");
        when(cDto.getEmailAddress()).thenReturn("42 Main St");
        when(cDto.getFirstName()).thenReturn("Jane");
        when(cDto.getLastName()).thenReturn("Doe");
        when(cDto.getPhoneNumber()).thenReturn("6625550144");
        when(cDto.getDob()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(cDto).setAddress(Mockito.<String>any());
        doNothing().when(cDto).setBankAccount(Mockito.<String>any());
        doNothing().when(cDto).setCompany(Mockito.<String>any());
        doNothing().when(cDto).setDob(Mockito.<LocalDate>any());
        doNothing().when(cDto).setEmailAddress(Mockito.<String>any());
        doNothing().when(cDto).setFirstName(Mockito.<String>any());
        doNothing().when(cDto).setId(Mockito.<Long>any());
        doNothing().when(cDto).setLastName(Mockito.<String>any());
        doNothing().when(cDto).setPhoneNumber(Mockito.<String>any());
        cDto.setAddress("42 Main St");
        cDto.setBankAccount("3");
        cDto.setCompany("Company");
        cDto.setDob(LocalDate.of(1970, 1, 1));
        cDto.setEmailAddress("42 Main St");
        cDto.setFirstName("Jane");
        cDto.setId(1L);
        cDto.setLastName("Doe");
        cDto.setPhoneNumber("6625550144");

        // Act
        Customer actualMappingToEntityCustomerResult = ModelMapperConfig.mappingToEntityCustomer(cDto);

        // Assert
        verify(cDto).getAddress();
        verify(cDto).getBankAccount();
        verify(cDto).getCompany();
        verify(cDto).getDob();
        verify(cDto).getEmailAddress();
        verify(cDto).getFirstName();
        verify(cDto).getId();
        verify(cDto).getLastName();
        verify(cDto).getPhoneNumber();
        verify(cDto).setAddress(eq("42 Main St"));
        verify(cDto).setBankAccount(eq("3"));
        verify(cDto).setCompany(eq("Company"));
        verify(cDto).setDob(isA(LocalDate.class));
        verify(cDto).setEmailAddress(eq("42 Main St"));
        verify(cDto).setFirstName(eq("Jane"));
        verify(cDto).setId(eq(1L));
        verify(cDto).setLastName(eq("Doe"));
        verify(cDto).setPhoneNumber(eq("6625550144"));
        assertEquals("1970-01-01", actualMappingToEntityCustomerResult.getDob().toString());
        assertEquals("3", actualMappingToEntityCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityCustomerResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityCustomerResult.getPhoneNumber());
        assertEquals("Company", actualMappingToEntityCustomerResult.getCompany());
        assertEquals("Doe", actualMappingToEntityCustomerResult.getLastName());
        assertEquals("Jane", actualMappingToEntityCustomerResult.getFirstName());
        assertEquals(1L, actualMappingToEntityCustomerResult.getId().longValue());
        assertTrue(actualMappingToEntityCustomerResult.getOrders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityCustomer(CustomerDto)}
     */
    @Test
    void testMappingToEntityCustomer3() {
        // Arrange
        CustomerDto cDto = mock(CustomerDto.class);
        when(cDto.getId()).thenReturn(1L);
        when(cDto.getAddress()).thenReturn("42 Main St");
        when(cDto.getBankAccount()).thenReturn("3");
        when(cDto.getCompany()).thenReturn("Company");
        when(cDto.getEmailAddress()).thenReturn("42 Main St");
        when(cDto.getFirstName()).thenReturn("Jane");
        when(cDto.getLastName()).thenReturn("Doe");
        when(cDto.getPhoneNumber()).thenReturn("6625550144");
        when(cDto.getDob()).thenReturn(null);
        doNothing().when(cDto).setAddress(Mockito.<String>any());
        doNothing().when(cDto).setBankAccount(Mockito.<String>any());
        doNothing().when(cDto).setCompany(Mockito.<String>any());
        doNothing().when(cDto).setDob(Mockito.<LocalDate>any());
        doNothing().when(cDto).setEmailAddress(Mockito.<String>any());
        doNothing().when(cDto).setFirstName(Mockito.<String>any());
        doNothing().when(cDto).setId(Mockito.<Long>any());
        doNothing().when(cDto).setLastName(Mockito.<String>any());
        doNothing().when(cDto).setPhoneNumber(Mockito.<String>any());
        cDto.setAddress("42 Main St");
        cDto.setBankAccount("3");
        cDto.setCompany("Company");
        cDto.setDob(LocalDate.of(1970, 1, 1));
        cDto.setEmailAddress("42 Main St");
        cDto.setFirstName("Jane");
        cDto.setId(1L);
        cDto.setLastName("Doe");
        cDto.setPhoneNumber("6625550144");

        // Act
        Customer actualMappingToEntityCustomerResult = ModelMapperConfig.mappingToEntityCustomer(cDto);

        // Assert
        verify(cDto).getAddress();
        verify(cDto).getBankAccount();
        verify(cDto).getCompany();
        verify(cDto).getDob();
        verify(cDto).getEmailAddress();
        verify(cDto).getFirstName();
        verify(cDto).getId();
        verify(cDto).getLastName();
        verify(cDto).getPhoneNumber();
        verify(cDto).setAddress(eq("42 Main St"));
        verify(cDto).setBankAccount(eq("3"));
        verify(cDto).setCompany(eq("Company"));
        verify(cDto).setDob(isA(LocalDate.class));
        verify(cDto).setEmailAddress(eq("42 Main St"));
        verify(cDto).setFirstName(eq("Jane"));
        verify(cDto).setId(eq(1L));
        verify(cDto).setLastName(eq("Doe"));
        verify(cDto).setPhoneNumber(eq("6625550144"));
        assertEquals("3", actualMappingToEntityCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityCustomerResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityCustomerResult.getPhoneNumber());
        assertEquals("Company", actualMappingToEntityCustomerResult.getCompany());
        assertEquals("Doe", actualMappingToEntityCustomerResult.getLastName());
        assertEquals("Jane", actualMappingToEntityCustomerResult.getFirstName());
        assertNull(actualMappingToEntityCustomerResult.getDob());
        assertEquals(1L, actualMappingToEntityCustomerResult.getId().longValue());
        assertTrue(actualMappingToEntityCustomerResult.getOrders().isEmpty());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoCustomer(Customer)}
     */
    @Test
    void testMappingToDtoCustomer() {
        // Arrange
        Customer c = new Customer();
        c.setAddress("42 Main St");
        c.setBankAccount("3");
        c.setCompany("Company");
        c.setDob(LocalDate.of(1970, 1, 1));
        c.setEmailAddress("42 Main St");
        c.setFirstName("Jane");
        c.setId(1L);
        c.setLastName("Doe");
        c.setOrders(new ArrayList<>());
        c.setPhoneNumber("6625550144");

        // Act
        CustomerDto actualMappingToDtoCustomerResult = ModelMapperConfig.mappingToDtoCustomer(c);

        // Assert
        assertEquals("1970-01-01", actualMappingToDtoCustomerResult.getDob().toString());
        assertEquals("3", actualMappingToDtoCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoCustomerResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoCustomerResult.getPhoneNumber());
        assertEquals("Company", actualMappingToDtoCustomerResult.getCompany());
        assertEquals("Doe", actualMappingToDtoCustomerResult.getLastName());
        assertEquals("Jane", actualMappingToDtoCustomerResult.getFirstName());
        assertEquals(1L, actualMappingToDtoCustomerResult.getId().longValue());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoCustomer(Customer)}
     */
    @Test
    void testMappingToDtoCustomer2() {
        // Arrange
        Customer c = mock(Customer.class);
        when(c.getId()).thenReturn(1L);
        when(c.getAddress()).thenReturn("42 Main St");
        when(c.getBankAccount()).thenReturn("3");
        when(c.getCompany()).thenReturn("Company");
        when(c.getEmailAddress()).thenReturn("42 Main St");
        when(c.getFirstName()).thenReturn("Jane");
        when(c.getLastName()).thenReturn("Doe");
        when(c.getPhoneNumber()).thenReturn("6625550144");
        when(c.getDob()).thenReturn(LocalDate.of(1970, 1, 1));
        doNothing().when(c).setAddress(Mockito.<String>any());
        doNothing().when(c).setBankAccount(Mockito.<String>any());
        doNothing().when(c).setCompany(Mockito.<String>any());
        doNothing().when(c).setDob(Mockito.<LocalDate>any());
        doNothing().when(c).setEmailAddress(Mockito.<String>any());
        doNothing().when(c).setFirstName(Mockito.<String>any());
        doNothing().when(c).setId(Mockito.<Long>any());
        doNothing().when(c).setLastName(Mockito.<String>any());
        doNothing().when(c).setOrders(Mockito.<List<Order>>any());
        doNothing().when(c).setPhoneNumber(Mockito.<String>any());
        c.setAddress("42 Main St");
        c.setBankAccount("3");
        c.setCompany("Company");
        c.setDob(LocalDate.of(1970, 1, 1));
        c.setEmailAddress("42 Main St");
        c.setFirstName("Jane");
        c.setId(1L);
        c.setLastName("Doe");
        c.setOrders(new ArrayList<>());
        c.setPhoneNumber("6625550144");

        // Act
        CustomerDto actualMappingToDtoCustomerResult = ModelMapperConfig.mappingToDtoCustomer(c);

        // Assert
        verify(c).getAddress();
        verify(c).getBankAccount();
        verify(c).getCompany();
        verify(c).getDob();
        verify(c).getEmailAddress();
        verify(c).getFirstName();
        verify(c).getId();
        verify(c).getLastName();
        verify(c).getPhoneNumber();
        verify(c).setAddress(eq("42 Main St"));
        verify(c).setBankAccount(eq("3"));
        verify(c).setCompany(eq("Company"));
        verify(c).setDob(isA(LocalDate.class));
        verify(c).setEmailAddress(eq("42 Main St"));
        verify(c).setFirstName(eq("Jane"));
        verify(c).setId(eq(1L));
        verify(c).setLastName(eq("Doe"));
        verify(c).setOrders(isA(List.class));
        verify(c).setPhoneNumber(eq("6625550144"));
        assertEquals("1970-01-01", actualMappingToDtoCustomerResult.getDob().toString());
        assertEquals("3", actualMappingToDtoCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoCustomerResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoCustomerResult.getPhoneNumber());
        assertEquals("Company", actualMappingToDtoCustomerResult.getCompany());
        assertEquals("Doe", actualMappingToDtoCustomerResult.getLastName());
        assertEquals("Jane", actualMappingToDtoCustomerResult.getFirstName());
        assertEquals(1L, actualMappingToDtoCustomerResult.getId().longValue());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoCustomer(Customer)}
     */
    @Test
    void testMappingToDtoCustomer3() {
        // Arrange
        Customer c = mock(Customer.class);
        when(c.getId()).thenReturn(1L);
        when(c.getAddress()).thenReturn("42 Main St");
        when(c.getBankAccount()).thenReturn("3");
        when(c.getCompany()).thenReturn("Company");
        when(c.getEmailAddress()).thenReturn("42 Main St");
        when(c.getFirstName()).thenReturn("Jane");
        when(c.getLastName()).thenReturn("Doe");
        when(c.getPhoneNumber()).thenReturn("6625550144");
        when(c.getDob()).thenReturn(null);
        doNothing().when(c).setAddress(Mockito.<String>any());
        doNothing().when(c).setBankAccount(Mockito.<String>any());
        doNothing().when(c).setCompany(Mockito.<String>any());
        doNothing().when(c).setDob(Mockito.<LocalDate>any());
        doNothing().when(c).setEmailAddress(Mockito.<String>any());
        doNothing().when(c).setFirstName(Mockito.<String>any());
        doNothing().when(c).setId(Mockito.<Long>any());
        doNothing().when(c).setLastName(Mockito.<String>any());
        doNothing().when(c).setOrders(Mockito.<List<Order>>any());
        doNothing().when(c).setPhoneNumber(Mockito.<String>any());
        c.setAddress("42 Main St");
        c.setBankAccount("3");
        c.setCompany("Company");
        c.setDob(LocalDate.of(1970, 1, 1));
        c.setEmailAddress("42 Main St");
        c.setFirstName("Jane");
        c.setId(1L);
        c.setLastName("Doe");
        c.setOrders(new ArrayList<>());
        c.setPhoneNumber("6625550144");

        // Act
        CustomerDto actualMappingToDtoCustomerResult = ModelMapperConfig.mappingToDtoCustomer(c);

        // Assert
        verify(c).getAddress();
        verify(c).getBankAccount();
        verify(c).getCompany();
        verify(c).getDob();
        verify(c).getEmailAddress();
        verify(c).getFirstName();
        verify(c).getId();
        verify(c).getLastName();
        verify(c).getPhoneNumber();
        verify(c).setAddress(eq("42 Main St"));
        verify(c).setBankAccount(eq("3"));
        verify(c).setCompany(eq("Company"));
        verify(c).setDob(isA(LocalDate.class));
        verify(c).setEmailAddress(eq("42 Main St"));
        verify(c).setFirstName(eq("Jane"));
        verify(c).setId(eq(1L));
        verify(c).setLastName(eq("Doe"));
        verify(c).setOrders(isA(List.class));
        verify(c).setPhoneNumber(eq("6625550144"));
        assertEquals("3", actualMappingToDtoCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoCustomerResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoCustomerResult.getPhoneNumber());
        assertEquals("Company", actualMappingToDtoCustomerResult.getCompany());
        assertEquals("Doe", actualMappingToDtoCustomerResult.getLastName());
        assertEquals("Jane", actualMappingToDtoCustomerResult.getFirstName());
        assertNull(actualMappingToDtoCustomerResult.getDob());
        assertEquals(1L, actualMappingToDtoCustomerResult.getId().longValue());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoOrder(Order)}
     */
    @Test
    void testMappingToDtoOrder() throws UnsupportedEncodingException {
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
        ArrayList<Order> orders = new ArrayList<>();
        customer.setOrders(orders);
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

        // Act
        OrderDto actualMappingToDtoOrderResult = ModelMapperConfig.mappingToDtoOrder(order2);

        // Assert
        assertEquals("1970-01-01", actualMappingToDtoOrderResult.getOrderDate().toString());
        assertEquals("Company", actualMappingToDtoOrderResult.getCustomerCompany());
        assertEquals("Doe", actualMappingToDtoOrderResult.getCustomerLastName());
        assertEquals("Jane", actualMappingToDtoOrderResult.getCustomerFirstName());
        assertEquals("Order Time", actualMappingToDtoOrderResult.getOrderTime());
        assertEquals(10.0d, actualMappingToDtoOrderResult.getTotalPriceInEur());
        assertEquals(1L, actualMappingToDtoOrderResult.getId().longValue());
        assertEquals(orders, actualMappingToDtoOrderResult.getProducts());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoOrder(Order)}
     */
    @Test
    void testMappingToDtoOrder2() throws UnsupportedEncodingException {
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
        Order order2 = mock(Order.class);
        when(order2.getTotalPriceInEur()).thenReturn(10.0d);
        when(order2.getId()).thenReturn(1L);
        when(order2.getOrderTime()).thenReturn("Order Time");
        when(order2.getOrderDate()).thenReturn(LocalDate.of(1970, 1, 1));
        ArrayList<OrderItemLine> orderItemLineList = new ArrayList<>();
        when(order2.getProducts()).thenReturn(orderItemLineList);
        when(order2.getCustomer()).thenReturn(customer4);
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
        OrderDto actualMappingToDtoOrderResult = ModelMapperConfig.mappingToDtoOrder(order2);

        // Assert
        verify(order2, atLeast(1)).getCustomer();
        verify(order2).getId();
        verify(order2).getOrderDate();
        verify(order2).getOrderTime();
        verify(order2, atLeast(1)).getProducts();
        verify(order2).getTotalPriceInEur();
        verify(order2).setCustomer(isA(Customer.class));
        verify(order2).setId(eq(1L));
        verify(order2).setInvoice(isA(Invoice.class));
        verify(order2).setOrderDate(isA(LocalDate.class));
        verify(order2).setOrderTime(eq("Order Time"));
        verify(order2).setProducts(isA(List.class));
        verify(order2).setTotalPriceInEur(eq(10.0d));
        assertEquals("1970-01-01", actualMappingToDtoOrderResult.getOrderDate().toString());
        assertEquals("Company", actualMappingToDtoOrderResult.getCustomerCompany());
        assertEquals("Doe", actualMappingToDtoOrderResult.getCustomerLastName());
        assertEquals("Jane", actualMappingToDtoOrderResult.getCustomerFirstName());
        assertEquals("Order Time", actualMappingToDtoOrderResult.getOrderTime());
        assertEquals(10.0d, actualMappingToDtoOrderResult.getTotalPriceInEur());
        assertEquals(1L, actualMappingToDtoOrderResult.getId().longValue());
        assertEquals(orderItemLineList, actualMappingToDtoOrderResult.getProducts());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoOrder(Order)}
     */
    @Test
    void testMappingToDtoOrder3() throws UnsupportedEncodingException {
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
        Order order2 = mock(Order.class);
        when(order2.getTotalPriceInEur()).thenReturn(10.0d);
        when(order2.getId()).thenReturn(1L);
        when(order2.getOrderTime()).thenReturn("Order Time");
        when(order2.getOrderDate()).thenReturn(null);
        ArrayList<OrderItemLine> orderItemLineList = new ArrayList<>();
        when(order2.getProducts()).thenReturn(orderItemLineList);
        when(order2.getCustomer()).thenReturn(customer4);
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
        OrderDto actualMappingToDtoOrderResult = ModelMapperConfig.mappingToDtoOrder(order2);

        // Assert
        verify(order2, atLeast(1)).getCustomer();
        verify(order2).getId();
        verify(order2).getOrderDate();
        verify(order2).getOrderTime();
        verify(order2, atLeast(1)).getProducts();
        verify(order2).getTotalPriceInEur();
        verify(order2).setCustomer(isA(Customer.class));
        verify(order2).setId(eq(1L));
        verify(order2).setInvoice(isA(Invoice.class));
        verify(order2).setOrderDate(isA(LocalDate.class));
        verify(order2).setOrderTime(eq("Order Time"));
        verify(order2).setProducts(isA(List.class));
        verify(order2).setTotalPriceInEur(eq(10.0d));
        assertEquals("Company", actualMappingToDtoOrderResult.getCustomerCompany());
        assertEquals("Doe", actualMappingToDtoOrderResult.getCustomerLastName());
        assertEquals("Jane", actualMappingToDtoOrderResult.getCustomerFirstName());
        assertEquals("Order Time", actualMappingToDtoOrderResult.getOrderTime());
        assertNull(actualMappingToDtoOrderResult.getOrderDate());
        assertEquals(10.0d, actualMappingToDtoOrderResult.getTotalPriceInEur());
        assertEquals(1L, actualMappingToDtoOrderResult.getId().longValue());
        assertEquals(orderItemLineList, actualMappingToDtoOrderResult.getProducts());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityProduct(ProductDto)}
     */
    @Test
    void testMappingToEntityProduct() {
        // Arrange
        ProductDto pDto = new ProductDto();
        pDto.setCategory("Category");
        pDto.setDescription("The characteristics of someone or something");
        pDto.setId(1L);
        pDto.setInStock(1);
        pDto.setName("Name");
        pDto.setPackedPerUnit("Packed Per Unit");
        pDto.setPriceInEur(10.0d);
        pDto.setShortDescription("Short Description");

        // Act
        Product actualMappingToEntityProductResult = ModelMapperConfig.mappingToEntityProduct(pDto);

        // Assert
        assertEquals("Category", actualMappingToEntityProductResult.getCategory());
        assertEquals("Name", actualMappingToEntityProductResult.getName());
        assertEquals("Packed Per Unit", actualMappingToEntityProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualMappingToEntityProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualMappingToEntityProductResult.getDescription());
        assertEquals(1, actualMappingToEntityProductResult.getInStock());
        assertEquals(10.0d, actualMappingToEntityProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualMappingToEntityProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityProduct(ProductDto)}
     */
    @Test
    void testMappingToEntityProduct2() {
        // Arrange
        ProductDto pDto = mock(ProductDto.class);
        when(pDto.getInStock()).thenReturn(1);
        when(pDto.getPriceInEur()).thenReturn(10.0d);
        when(pDto.getId()).thenReturn(1L);
        when(pDto.getCategory()).thenReturn("Category");
        when(pDto.getDescription()).thenReturn("The characteristics of someone or something");
        when(pDto.getName()).thenReturn("Name");
        when(pDto.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(pDto.getShortDescription()).thenReturn("Short Description");
        doNothing().when(pDto).setCategory(Mockito.<String>any());
        doNothing().when(pDto).setDescription(Mockito.<String>any());
        doNothing().when(pDto).setId(Mockito.<Long>any());
        doNothing().when(pDto).setInStock(anyInt());
        doNothing().when(pDto).setName(Mockito.<String>any());
        doNothing().when(pDto).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(pDto).setPriceInEur(Mockito.<Double>any());
        doNothing().when(pDto).setShortDescription(Mockito.<String>any());
        pDto.setCategory("Category");
        pDto.setDescription("The characteristics of someone or something");
        pDto.setId(1L);
        pDto.setInStock(1);
        pDto.setName("Name");
        pDto.setPackedPerUnit("Packed Per Unit");
        pDto.setPriceInEur(10.0d);
        pDto.setShortDescription("Short Description");

        // Act
        Product actualMappingToEntityProductResult = ModelMapperConfig.mappingToEntityProduct(pDto);

        // Assert
        verify(pDto).getCategory();
        verify(pDto).getDescription();
        verify(pDto).getId();
        verify(pDto).getInStock();
        verify(pDto).getName();
        verify(pDto).getPackedPerUnit();
        verify(pDto).getPriceInEur();
        verify(pDto).getShortDescription();
        verify(pDto).setCategory(eq("Category"));
        verify(pDto).setDescription(eq("The characteristics of someone or something"));
        verify(pDto).setId(eq(1L));
        verify(pDto).setInStock(eq(1));
        verify(pDto).setName(eq("Name"));
        verify(pDto).setPackedPerUnit(eq("Packed Per Unit"));
        verify(pDto).setPriceInEur(eq(10.0d));
        verify(pDto).setShortDescription(eq("Short Description"));
        assertEquals("Category", actualMappingToEntityProductResult.getCategory());
        assertEquals("Name", actualMappingToEntityProductResult.getName());
        assertEquals("Packed Per Unit", actualMappingToEntityProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualMappingToEntityProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualMappingToEntityProductResult.getDescription());
        assertEquals(1, actualMappingToEntityProductResult.getInStock());
        assertEquals(10.0d, actualMappingToEntityProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualMappingToEntityProductResult.getId().longValue());
    }

    /**
     * Method under test: {@link ModelMapperConfig#mappingToDtoProduct(Product)}
     */
    @Test
    void testMappingToDtoProduct() {
        // Arrange
        Product p = mock(Product.class);
        when(p.getInStock()).thenReturn(1);
        when(p.getPriceInEur()).thenReturn(10.0d);
        when(p.getId()).thenReturn(1L);
        when(p.getCategory()).thenReturn("Category");
        when(p.getDescription()).thenReturn("The characteristics of someone or something");
        when(p.getName()).thenReturn("Name");
        when(p.getPackedPerUnit()).thenReturn("Packed Per Unit");
        when(p.getShortDescription()).thenReturn("Short Description");
        doNothing().when(p).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(p).restoreInStockForChangedOrder(anyInt());
        doNothing().when(p).setCategory(Mockito.<String>any());
        doNothing().when(p).setDescription(Mockito.<String>any());
        doNothing().when(p).setId(Mockito.<Long>any());
        doNothing().when(p).setInStock(anyInt());
        doNothing().when(p).setName(Mockito.<String>any());
        doNothing().when(p).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(p).setPriceInEur(Mockito.<Double>any());
        doNothing().when(p).setShortDescription(Mockito.<String>any());
        p.lowerInStockForIncomingOrder(1);
        p.restoreInStockForChangedOrder(1);
        p.setCategory("Category");
        p.setDescription("The characteristics of someone or something");
        p.setId(1L);
        p.setInStock(1);
        p.setName("Name");
        p.setPackedPerUnit("Packed Per Unit");
        p.setPriceInEur(10.0d);
        p.setShortDescription("Short Description");

        // Act
        ProductDto actualMappingToDtoProductResult = ModelMapperConfig.mappingToDtoProduct(p);

        // Assert
        verify(p).getCategory();
        verify(p).getDescription();
        verify(p).getId();
        verify(p).getInStock();
        verify(p).getName();
        verify(p).getPackedPerUnit();
        verify(p).getPriceInEur();
        verify(p).getShortDescription();
        verify(p).lowerInStockForIncomingOrder(eq(1));
        verify(p).restoreInStockForChangedOrder(eq(1));
        verify(p).setCategory(eq("Category"));
        verify(p).setDescription(eq("The characteristics of someone or something"));
        verify(p).setId(eq(1L));
        verify(p).setInStock(eq(1));
        verify(p).setName(eq("Name"));
        verify(p).setPackedPerUnit(eq("Packed Per Unit"));
        verify(p).setPriceInEur(eq(10.0d));
        verify(p).setShortDescription(eq("Short Description"));
        assertEquals("Category", actualMappingToDtoProductResult.getCategory());
        assertEquals("Name", actualMappingToDtoProductResult.getName());
        assertEquals("Packed Per Unit", actualMappingToDtoProductResult.getPackedPerUnit());
        assertEquals("Short Description", actualMappingToDtoProductResult.getShortDescription());
        assertEquals("The characteristics of someone or something", actualMappingToDtoProductResult.getDescription());
        assertEquals(1, actualMappingToDtoProductResult.inStock.intValue());
        assertEquals(10.0d, actualMappingToDtoProductResult.getPriceInEur().doubleValue());
        assertEquals(1L, actualMappingToDtoProductResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToDtoUserEmployee(UserEmployee)}
     */
    @Test
    void testMappingToDtoUserEmployee() {
        // Arrange
        UserEmployee user = new UserEmployee();
        user.setAddress("42 Main St");
        user.setBankAccount("3");
        user.setDob(LocalDate.of(1970, 1, 1));
        user.setEmailAddress("42 Main St");
        user.setEmployeeNumber(1L);
        user.setFirstName("Jane");
        user.setFunction("Function");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumb("6625550144");
        ArrayList<Role> roles = new ArrayList<>();
        user.setRoles(roles);
        user.setUsername("janedoe");

        // Act
        UserEmployeeDtoOutput actualMappingToDtoUserEmployeeResult = ModelMapperConfig.mappingToDtoUserEmployee(user);

        // Assert
        assertEquals("1970-01-01", actualMappingToDtoUserEmployeeResult.getDob().toString());
        assertEquals("3", actualMappingToDtoUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToDtoUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToDtoUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToDtoUserEmployeeResult.getFirstName());
        assertEquals("janedoe", actualMappingToDtoUserEmployeeResult.getUsername());
        assertEquals(1L, actualMappingToDtoUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(roles, actualMappingToDtoUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToDtoUserEmployee(UserEmployee)}
     */
    @Test
    void testMappingToDtoUserEmployee2() {
        // Arrange
        UserEmployee user = mock(UserEmployee.class);
        when(user.getEmployeeNumber()).thenReturn(1L);
        when(user.getAddress()).thenReturn("42 Main St");
        when(user.getBankAccount()).thenReturn("3");
        when(user.getEmailAddress()).thenReturn("42 Main St");
        when(user.getFirstName()).thenReturn("Jane");
        when(user.getFunction()).thenReturn("Function");
        when(user.getLastName()).thenReturn("Doe");
        when(user.getPhoneNumb()).thenReturn("6625550144");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getDob()).thenReturn(LocalDate.of(1970, 1, 1));
        ArrayList<Role> roleList = new ArrayList<>();
        when(user.getRoles()).thenReturn(roleList);
        doNothing().when(user).setAddress(Mockito.<String>any());
        doNothing().when(user).setBankAccount(Mockito.<String>any());
        doNothing().when(user).setDob(Mockito.<LocalDate>any());
        doNothing().when(user).setEmailAddress(Mockito.<String>any());
        doNothing().when(user).setEmployeeNumber(Mockito.<Long>any());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setFunction(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPhoneNumb(Mockito.<String>any());
        doNothing().when(user).setRoles(Mockito.<Collection<Role>>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAddress("42 Main St");
        user.setBankAccount("3");
        user.setDob(LocalDate.of(1970, 1, 1));
        user.setEmailAddress("42 Main St");
        user.setEmployeeNumber(1L);
        user.setFirstName("Jane");
        user.setFunction("Function");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumb("6625550144");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        // Act
        UserEmployeeDtoOutput actualMappingToDtoUserEmployeeResult = ModelMapperConfig.mappingToDtoUserEmployee(user);

        // Assert
        verify(user).getAddress();
        verify(user).getBankAccount();
        verify(user).getDob();
        verify(user).getEmailAddress();
        verify(user).getEmployeeNumber();
        verify(user).getFirstName();
        verify(user).getFunction();
        verify(user).getLastName();
        verify(user).getPhoneNumb();
        verify(user, atLeast(1)).getRoles();
        verify(user).getUsername();
        verify(user).setAddress(eq("42 Main St"));
        verify(user).setBankAccount(eq("3"));
        verify(user).setDob(isA(LocalDate.class));
        verify(user).setEmailAddress(eq("42 Main St"));
        verify(user).setEmployeeNumber(eq(1L));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setFunction(eq("Function"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setPhoneNumb(eq("6625550144"));
        verify(user).setRoles(isA(Collection.class));
        verify(user).setUsername(eq("janedoe"));
        assertEquals("1970-01-01", actualMappingToDtoUserEmployeeResult.getDob().toString());
        assertEquals("3", actualMappingToDtoUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToDtoUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToDtoUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToDtoUserEmployeeResult.getFirstName());
        assertEquals("janedoe", actualMappingToDtoUserEmployeeResult.getUsername());
        assertEquals(1L, actualMappingToDtoUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(roleList, actualMappingToDtoUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToDtoUserEmployee(UserEmployee)}
     */
    @Test
    void testMappingToDtoUserEmployee3() {
        // Arrange
        UserEmployee user = mock(UserEmployee.class);
        when(user.getEmployeeNumber()).thenReturn(1L);
        when(user.getAddress()).thenReturn("42 Main St");
        when(user.getBankAccount()).thenReturn("3");
        when(user.getEmailAddress()).thenReturn("42 Main St");
        when(user.getFirstName()).thenReturn("Jane");
        when(user.getFunction()).thenReturn("Function");
        when(user.getLastName()).thenReturn("Doe");
        when(user.getPhoneNumb()).thenReturn("6625550144");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getDob()).thenReturn(null);
        ArrayList<Role> roleList = new ArrayList<>();
        when(user.getRoles()).thenReturn(roleList);
        doNothing().when(user).setAddress(Mockito.<String>any());
        doNothing().when(user).setBankAccount(Mockito.<String>any());
        doNothing().when(user).setDob(Mockito.<LocalDate>any());
        doNothing().when(user).setEmailAddress(Mockito.<String>any());
        doNothing().when(user).setEmployeeNumber(Mockito.<Long>any());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setFunction(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPhoneNumb(Mockito.<String>any());
        doNothing().when(user).setRoles(Mockito.<Collection<Role>>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAddress("42 Main St");
        user.setBankAccount("3");
        user.setDob(LocalDate.of(1970, 1, 1));
        user.setEmailAddress("42 Main St");
        user.setEmployeeNumber(1L);
        user.setFirstName("Jane");
        user.setFunction("Function");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumb("6625550144");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        // Act
        UserEmployeeDtoOutput actualMappingToDtoUserEmployeeResult = ModelMapperConfig.mappingToDtoUserEmployee(user);

        // Assert
        verify(user).getAddress();
        verify(user).getBankAccount();
        verify(user).getDob();
        verify(user).getEmailAddress();
        verify(user).getEmployeeNumber();
        verify(user).getFirstName();
        verify(user).getFunction();
        verify(user).getLastName();
        verify(user).getPhoneNumb();
        verify(user, atLeast(1)).getRoles();
        verify(user).getUsername();
        verify(user).setAddress(eq("42 Main St"));
        verify(user).setBankAccount(eq("3"));
        verify(user).setDob(isA(LocalDate.class));
        verify(user).setEmailAddress(eq("42 Main St"));
        verify(user).setEmployeeNumber(eq(1L));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setFunction(eq("Function"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setPhoneNumb(eq("6625550144"));
        verify(user).setRoles(isA(Collection.class));
        verify(user).setUsername(eq("janedoe"));
        assertEquals("3", actualMappingToDtoUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToDtoUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToDtoUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToDtoUserEmployeeResult.getFirstName());
        assertEquals("janedoe", actualMappingToDtoUserEmployeeResult.getUsername());
        assertNull(actualMappingToDtoUserEmployeeResult.getDob());
        assertEquals(1L, actualMappingToDtoUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(roleList, actualMappingToDtoUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToDtoUserEmployee(UserEmployee)}
     */
    @Test
    void testMappingToDtoUserEmployee4() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Role Name");
        role.setUsersEmployees(new ArrayList<>());

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        UserEmployee user = mock(UserEmployee.class);
        when(user.getEmployeeNumber()).thenReturn(1L);
        when(user.getAddress()).thenReturn("42 Main St");
        when(user.getBankAccount()).thenReturn("3");
        when(user.getEmailAddress()).thenReturn("42 Main St");
        when(user.getFirstName()).thenReturn("Jane");
        when(user.getFunction()).thenReturn("Function");
        when(user.getLastName()).thenReturn("Doe");
        when(user.getPhoneNumb()).thenReturn("6625550144");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getDob()).thenReturn(LocalDate.of(1970, 1, 1));
        when(user.getRoles()).thenReturn(roleList);
        doNothing().when(user).setAddress(Mockito.<String>any());
        doNothing().when(user).setBankAccount(Mockito.<String>any());
        doNothing().when(user).setDob(Mockito.<LocalDate>any());
        doNothing().when(user).setEmailAddress(Mockito.<String>any());
        doNothing().when(user).setEmployeeNumber(Mockito.<Long>any());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setFunction(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPhoneNumb(Mockito.<String>any());
        doNothing().when(user).setRoles(Mockito.<Collection<Role>>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAddress("42 Main St");
        user.setBankAccount("3");
        user.setDob(LocalDate.of(1970, 1, 1));
        user.setEmailAddress("42 Main St");
        user.setEmployeeNumber(1L);
        user.setFirstName("Jane");
        user.setFunction("Function");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumb("6625550144");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        // Act
        UserEmployeeDtoOutput actualMappingToDtoUserEmployeeResult = ModelMapperConfig.mappingToDtoUserEmployee(user);

        // Assert
        verify(user).getAddress();
        verify(user).getBankAccount();
        verify(user).getDob();
        verify(user).getEmailAddress();
        verify(user).getEmployeeNumber();
        verify(user).getFirstName();
        verify(user).getFunction();
        verify(user).getLastName();
        verify(user).getPhoneNumb();
        verify(user, atLeast(1)).getRoles();
        verify(user).getUsername();
        verify(user).setAddress(eq("42 Main St"));
        verify(user).setBankAccount(eq("3"));
        verify(user).setDob(isA(LocalDate.class));
        verify(user).setEmailAddress(eq("42 Main St"));
        verify(user).setEmployeeNumber(eq(1L));
        verify(user).setFirstName(eq("Jane"));
        verify(user).setFunction(eq("Function"));
        verify(user).setLastName(eq("Doe"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setPhoneNumb(eq("6625550144"));
        verify(user).setRoles(isA(Collection.class));
        verify(user).setUsername(eq("janedoe"));
        assertEquals("1970-01-01", actualMappingToDtoUserEmployeeResult.getDob().toString());
        assertEquals("3", actualMappingToDtoUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToDtoUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToDtoUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToDtoUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToDtoUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToDtoUserEmployeeResult.getFirstName());
        List<String> roles = actualMappingToDtoUserEmployeeResult.getRoles();
        assertEquals(1, roles.size());
        assertEquals("Role Name", roles.get(0));
        assertEquals("janedoe", actualMappingToDtoUserEmployeeResult.getUsername());
        assertEquals(1L, actualMappingToDtoUserEmployeeResult.getEmployeeNumber().longValue());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityUserEmployee(UserEmployeeDto)}
     */
    @Test
    void testMappingToEntityUserEmployee() {
        // Arrange
        UserEmployeeDto uDto = new UserEmployeeDto();
        uDto.setAddress("42 Main St");
        uDto.setBankAccount("3");
        uDto.setDob(LocalDate.of(1970, 1, 1));
        uDto.setEmailAddress("42 Main St");
        uDto.setEmployeeNumber(1L);
        uDto.setFirstName("Jane");
        uDto.setFunction("Function");
        uDto.setLastName("Doe");
        uDto.setPassword("iloveyou");
        uDto.setPhoneNumb("6625550144");
        ArrayList<String> roles = new ArrayList<>();
        uDto.setRoles(roles);
        uDto.setUsername("janedoe");

        // Act
        UserEmployee actualMappingToEntityUserEmployeeResult = ModelMapperConfig.mappingToEntityUserEmployee(uDto);

        // Assert
        assertEquals("1970-01-01", actualMappingToEntityUserEmployeeResult.getDob().toString());
        assertEquals("3", actualMappingToEntityUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToEntityUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToEntityUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToEntityUserEmployeeResult.getFirstName());
        assertEquals("iloveyou", actualMappingToEntityUserEmployeeResult.getPassword());
        assertEquals("janedoe", actualMappingToEntityUserEmployeeResult.getUsername());
        assertEquals(1L, actualMappingToEntityUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(roles, actualMappingToEntityUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityUserEmployee(UserEmployeeDto)}
     */
    @Test
    void testMappingToEntityUserEmployee2() {
        // Arrange
        UserEmployeeDto uDto = mock(UserEmployeeDto.class);
        when(uDto.getEmployeeNumber()).thenReturn(1L);
        when(uDto.getAddress()).thenReturn("42 Main St");
        when(uDto.getBankAccount()).thenReturn("3");
        when(uDto.getEmailAddress()).thenReturn("42 Main St");
        when(uDto.getFirstName()).thenReturn("Jane");
        when(uDto.getFunction()).thenReturn("Function");
        when(uDto.getLastName()).thenReturn("Doe");
        when(uDto.getPassword()).thenReturn("iloveyou");
        when(uDto.getPhoneNumb()).thenReturn("6625550144");
        when(uDto.getUsername()).thenReturn("janedoe");
        when(uDto.getDob()).thenReturn(LocalDate.of(1970, 1, 1));
        ArrayList<String> stringList = new ArrayList<>();
        when(uDto.getRoles()).thenReturn(stringList);
        doNothing().when(uDto).setAddress(Mockito.<String>any());
        doNothing().when(uDto).setBankAccount(Mockito.<String>any());
        doNothing().when(uDto).setDob(Mockito.<LocalDate>any());
        doNothing().when(uDto).setEmailAddress(Mockito.<String>any());
        doNothing().when(uDto).setEmployeeNumber(Mockito.<Long>any());
        doNothing().when(uDto).setFirstName(Mockito.<String>any());
        doNothing().when(uDto).setFunction(Mockito.<String>any());
        doNothing().when(uDto).setLastName(Mockito.<String>any());
        doNothing().when(uDto).setPassword(Mockito.<String>any());
        doNothing().when(uDto).setPhoneNumb(Mockito.<String>any());
        doNothing().when(uDto).setRoles(Mockito.<List<String>>any());
        doNothing().when(uDto).setUsername(Mockito.<String>any());
        uDto.setAddress("42 Main St");
        uDto.setBankAccount("3");
        uDto.setDob(LocalDate.of(1970, 1, 1));
        uDto.setEmailAddress("42 Main St");
        uDto.setEmployeeNumber(1L);
        uDto.setFirstName("Jane");
        uDto.setFunction("Function");
        uDto.setLastName("Doe");
        uDto.setPassword("iloveyou");
        uDto.setPhoneNumb("6625550144");
        uDto.setRoles(new ArrayList<>());
        uDto.setUsername("janedoe");

        // Act
        UserEmployee actualMappingToEntityUserEmployeeResult = ModelMapperConfig.mappingToEntityUserEmployee(uDto);

        // Assert
        verify(uDto).getAddress();
        verify(uDto).getBankAccount();
        verify(uDto).getDob();
        verify(uDto).getEmailAddress();
        verify(uDto).getEmployeeNumber();
        verify(uDto).getFirstName();
        verify(uDto).getFunction();
        verify(uDto).getLastName();
        verify(uDto).getPassword();
        verify(uDto).getPhoneNumb();
        verify(uDto).getRoles();
        verify(uDto).getUsername();
        verify(uDto).setAddress(eq("42 Main St"));
        verify(uDto).setBankAccount(eq("3"));
        verify(uDto).setDob(isA(LocalDate.class));
        verify(uDto).setEmailAddress(eq("42 Main St"));
        verify(uDto).setEmployeeNumber(eq(1L));
        verify(uDto).setFirstName(eq("Jane"));
        verify(uDto).setFunction(eq("Function"));
        verify(uDto).setLastName(eq("Doe"));
        verify(uDto).setPassword(eq("iloveyou"));
        verify(uDto).setPhoneNumb(eq("6625550144"));
        verify(uDto).setRoles(isA(List.class));
        verify(uDto).setUsername(eq("janedoe"));
        assertEquals("1970-01-01", actualMappingToEntityUserEmployeeResult.getDob().toString());
        assertEquals("3", actualMappingToEntityUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToEntityUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToEntityUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToEntityUserEmployeeResult.getFirstName());
        assertEquals("iloveyou", actualMappingToEntityUserEmployeeResult.getPassword());
        assertEquals("janedoe", actualMappingToEntityUserEmployeeResult.getUsername());
        assertEquals(1L, actualMappingToEntityUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(stringList, actualMappingToEntityUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityUserEmployee(UserEmployeeDto)}
     */
    @Test
    void testMappingToEntityUserEmployee3() {
        // Arrange
        UserEmployeeDto uDto = mock(UserEmployeeDto.class);
        when(uDto.getEmployeeNumber()).thenReturn(1L);
        when(uDto.getAddress()).thenReturn("42 Main St");
        when(uDto.getBankAccount()).thenReturn("3");
        when(uDto.getEmailAddress()).thenReturn("42 Main St");
        when(uDto.getFirstName()).thenReturn("Jane");
        when(uDto.getFunction()).thenReturn("Function");
        when(uDto.getLastName()).thenReturn("Doe");
        when(uDto.getPassword()).thenReturn("iloveyou");
        when(uDto.getPhoneNumb()).thenReturn("6625550144");
        when(uDto.getUsername()).thenReturn("janedoe");
        when(uDto.getDob()).thenReturn(null);
        ArrayList<String> stringList = new ArrayList<>();
        when(uDto.getRoles()).thenReturn(stringList);
        doNothing().when(uDto).setAddress(Mockito.<String>any());
        doNothing().when(uDto).setBankAccount(Mockito.<String>any());
        doNothing().when(uDto).setDob(Mockito.<LocalDate>any());
        doNothing().when(uDto).setEmailAddress(Mockito.<String>any());
        doNothing().when(uDto).setEmployeeNumber(Mockito.<Long>any());
        doNothing().when(uDto).setFirstName(Mockito.<String>any());
        doNothing().when(uDto).setFunction(Mockito.<String>any());
        doNothing().when(uDto).setLastName(Mockito.<String>any());
        doNothing().when(uDto).setPassword(Mockito.<String>any());
        doNothing().when(uDto).setPhoneNumb(Mockito.<String>any());
        doNothing().when(uDto).setRoles(Mockito.<List<String>>any());
        doNothing().when(uDto).setUsername(Mockito.<String>any());
        uDto.setAddress("42 Main St");
        uDto.setBankAccount("3");
        uDto.setDob(LocalDate.of(1970, 1, 1));
        uDto.setEmailAddress("42 Main St");
        uDto.setEmployeeNumber(1L);
        uDto.setFirstName("Jane");
        uDto.setFunction("Function");
        uDto.setLastName("Doe");
        uDto.setPassword("iloveyou");
        uDto.setPhoneNumb("6625550144");
        uDto.setRoles(new ArrayList<>());
        uDto.setUsername("janedoe");

        // Act
        UserEmployee actualMappingToEntityUserEmployeeResult = ModelMapperConfig.mappingToEntityUserEmployee(uDto);

        // Assert
        verify(uDto).getAddress();
        verify(uDto).getBankAccount();
        verify(uDto).getDob();
        verify(uDto).getEmailAddress();
        verify(uDto).getEmployeeNumber();
        verify(uDto).getFirstName();
        verify(uDto).getFunction();
        verify(uDto).getLastName();
        verify(uDto).getPassword();
        verify(uDto).getPhoneNumb();
        verify(uDto).getRoles();
        verify(uDto).getUsername();
        verify(uDto).setAddress(eq("42 Main St"));
        verify(uDto).setBankAccount(eq("3"));
        verify(uDto).setDob(isA(LocalDate.class));
        verify(uDto).setEmailAddress(eq("42 Main St"));
        verify(uDto).setEmployeeNumber(eq(1L));
        verify(uDto).setFirstName(eq("Jane"));
        verify(uDto).setFunction(eq("Function"));
        verify(uDto).setLastName(eq("Doe"));
        verify(uDto).setPassword(eq("iloveyou"));
        verify(uDto).setPhoneNumb(eq("6625550144"));
        verify(uDto).setRoles(isA(List.class));
        verify(uDto).setUsername(eq("janedoe"));
        assertEquals("3", actualMappingToEntityUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToEntityUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToEntityUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToEntityUserEmployeeResult.getFirstName());
        assertEquals("iloveyou", actualMappingToEntityUserEmployeeResult.getPassword());
        assertEquals("janedoe", actualMappingToEntityUserEmployeeResult.getUsername());
        assertNull(actualMappingToEntityUserEmployeeResult.getDob());
        assertEquals(1L, actualMappingToEntityUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(stringList, actualMappingToEntityUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link ModelMapperConfig#mappingToEntityUserEmployee(UserEmployeeDto)}
     */
    @Test
    void testMappingToEntityUserEmployee4() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        UserEmployeeDto uDto = mock(UserEmployeeDto.class);
        when(uDto.getEmployeeNumber()).thenReturn(1L);
        when(uDto.getAddress()).thenReturn("42 Main St");
        when(uDto.getBankAccount()).thenReturn("3");
        when(uDto.getEmailAddress()).thenReturn("42 Main St");
        when(uDto.getFirstName()).thenReturn("Jane");
        when(uDto.getFunction()).thenReturn("Function");
        when(uDto.getLastName()).thenReturn("Doe");
        when(uDto.getPassword()).thenReturn("iloveyou");
        when(uDto.getPhoneNumb()).thenReturn("6625550144");
        when(uDto.getUsername()).thenReturn("janedoe");
        when(uDto.getDob()).thenReturn(LocalDate.of(1970, 1, 1));
        when(uDto.getRoles()).thenReturn(stringList);
        doNothing().when(uDto).setAddress(Mockito.<String>any());
        doNothing().when(uDto).setBankAccount(Mockito.<String>any());
        doNothing().when(uDto).setDob(Mockito.<LocalDate>any());
        doNothing().when(uDto).setEmailAddress(Mockito.<String>any());
        doNothing().when(uDto).setEmployeeNumber(Mockito.<Long>any());
        doNothing().when(uDto).setFirstName(Mockito.<String>any());
        doNothing().when(uDto).setFunction(Mockito.<String>any());
        doNothing().when(uDto).setLastName(Mockito.<String>any());
        doNothing().when(uDto).setPassword(Mockito.<String>any());
        doNothing().when(uDto).setPhoneNumb(Mockito.<String>any());
        doNothing().when(uDto).setRoles(Mockito.<List<String>>any());
        doNothing().when(uDto).setUsername(Mockito.<String>any());
        uDto.setAddress("42 Main St");
        uDto.setBankAccount("3");
        uDto.setDob(LocalDate.of(1970, 1, 1));
        uDto.setEmailAddress("42 Main St");
        uDto.setEmployeeNumber(1L);
        uDto.setFirstName("Jane");
        uDto.setFunction("Function");
        uDto.setLastName("Doe");
        uDto.setPassword("iloveyou");
        uDto.setPhoneNumb("6625550144");
        uDto.setRoles(new ArrayList<>());
        uDto.setUsername("janedoe");

        // Act
        UserEmployee actualMappingToEntityUserEmployeeResult = ModelMapperConfig.mappingToEntityUserEmployee(uDto);

        // Assert
        verify(uDto).getAddress();
        verify(uDto).getBankAccount();
        verify(uDto).getDob();
        verify(uDto).getEmailAddress();
        verify(uDto).getEmployeeNumber();
        verify(uDto).getFirstName();
        verify(uDto).getFunction();
        verify(uDto).getLastName();
        verify(uDto).getPassword();
        verify(uDto).getPhoneNumb();
        verify(uDto).getRoles();
        verify(uDto).getUsername();
        verify(uDto).setAddress(eq("42 Main St"));
        verify(uDto).setBankAccount(eq("3"));
        verify(uDto).setDob(isA(LocalDate.class));
        verify(uDto).setEmailAddress(eq("42 Main St"));
        verify(uDto).setEmployeeNumber(eq(1L));
        verify(uDto).setFirstName(eq("Jane"));
        verify(uDto).setFunction(eq("Function"));
        verify(uDto).setLastName(eq("Doe"));
        verify(uDto).setPassword(eq("iloveyou"));
        verify(uDto).setPhoneNumb(eq("6625550144"));
        verify(uDto).setRoles(isA(List.class));
        verify(uDto).setUsername(eq("janedoe"));
        assertEquals("1970-01-01", actualMappingToEntityUserEmployeeResult.getDob().toString());
        assertEquals("3", actualMappingToEntityUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualMappingToEntityUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualMappingToEntityUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualMappingToEntityUserEmployeeResult.getLastName());
        assertEquals("Function", actualMappingToEntityUserEmployeeResult.getFunction());
        assertEquals("Jane", actualMappingToEntityUserEmployeeResult.getFirstName());
        assertEquals("iloveyou", actualMappingToEntityUserEmployeeResult.getPassword());
        assertEquals("janedoe", actualMappingToEntityUserEmployeeResult.getUsername());
        assertEquals(1, actualMappingToEntityUserEmployeeResult.getRoles().size());
        assertEquals(1L, actualMappingToEntityUserEmployeeResult.getEmployeeNumber().longValue());
    }
}
