package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import jakarta.mail.MessagingException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDtoAddProduct;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ConflictException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.*;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.*;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.InvoiceUtil;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceDiffblueTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private EmailSender emailSender;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private OrderItemLineRepository orderItemLineRepository;

    @MockBean
    private OrderItemLineService orderItemLineService;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    /**
     * Method under test: {@link OrderService#createOrder(OrderDto)}
     */
    @Test
    void testCreateOrder() throws MessagingException, UnsupportedEncodingException {
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

        Order order = new Order();
        order.setCustomer(new Customer());
        order.setId(1L);
        order.setInvoice(new Invoice());
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderTime("Order Time");
        order.setProducts(new ArrayList<>());
        order.setTotalPriceInEur(10.0d);

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer4);
        invoice.setCustomerCompany("Customer Name");
        invoice.setId(1L);
        invoice.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice.setOrder(order);
        invoice.setOrderNumber(10);

        Order order2 = new Order();
        order2.setCustomer(customer3);
        order2.setId(1L);
        order2.setInvoice(invoice);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomer(customer2);
        invoice2.setCustomerCompany("Customer Name");
        invoice2.setId(1L);
        invoice2.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice2.setOrder(order2);
        invoice2.setOrderNumber(10);

        Order order3 = new Order();
        order3.setCustomer(customer);
        order3.setId(1L);
        order3.setInvoice(invoice2);
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderTime("Order Time");
        order3.setProducts(new ArrayList<>());
        order3.setTotalPriceInEur(10.0d);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order3);

        Customer customer5 = new Customer();
        customer5.setAddress("42 Main St");
        customer5.setBankAccount("3");
        customer5.setCompany("Company");
        customer5.setDob(LocalDate.of(1970, 1, 1));
        customer5.setEmailAddress("42 Main St");
        customer5.setFirstName("Jane");
        customer5.setId(1L);
        customer5.setLastName("Doe");
        customer5.setOrders(new ArrayList<>());
        customer5.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer5);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        when(invoiceService.generateInvoicePdf(Mockito.<Long>any())).thenThrow(new ConflictException("An error occurred"));

        OrderDto odto = new OrderDto();
        odto.setCustomerCompany("Customer Company");
        odto.setCustomerFirstName("Jane");
        odto.setCustomerLastName("Doe");
        odto.setId(1L);
        odto.setOrderDate(LocalDate.of(1970, 1, 1));
        odto.setOrderTime("Order Time");
        odto.setProducts(new ArrayList<>());
        odto.setTotalPriceInEur(10.0d);

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.createOrder(odto));
        verify(customerRepository).findByCompany(eq("Customer Company"));
        verify(invoiceService).generateInvoicePdf(eq(1L));
        verify(orderRepository, atLeast(1)).save(Mockito.<Order>any());
    }

    /**
     * Method under test: {@link OrderService#getOrders()}
     */
    @Test
    void testGetOrders() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<OrderDto> actualOrders = orderService.getOrders();

        // Assert
        verify(orderRepository).findAll();
        assertTrue(actualOrders.isEmpty());
    }

    /**
     * Method under test: {@link OrderService#getOrders()}
     */
    @Test
    void testGetOrders2() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        when(orderRepository.findAll()).thenReturn(orderList);

        // Act
        List<OrderDto> actualOrders = orderService.getOrders();

        // Assert
        verify(orderRepository).findAll();
        assertEquals(1, actualOrders.size());
    }

    /**
     * Method under test: {@link OrderService#getOrders()}
     */
    @Test
    void testGetOrders3() {
        // Arrange
        when(orderRepository.findAll()).thenThrow(new ConflictException("An error occurred"));

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.getOrders());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderService#getOrderById(Long)}
     */
    @Test
    void testGetOrderById() throws UnsupportedEncodingException {
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
        Optional<Order> ofResult = Optional.of(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        OrderDto actualOrderById = orderService.getOrderById(1L);

        // Assert
        verify(orderRepository).findById(eq(1L));
        assertEquals("1970-01-01", actualOrderById.getOrderDate().toString());
        assertEquals("Company", actualOrderById.getCustomerCompany());
        assertEquals("Doe", actualOrderById.getCustomerLastName());
        assertEquals("Jane", actualOrderById.getCustomerFirstName());
        assertEquals("Order Time", actualOrderById.getOrderTime());
        assertEquals(10.0d, actualOrderById.getTotalPriceInEur());
        assertEquals(1L, actualOrderById.getId().longValue());
        assertEquals(orders, actualOrderById.getProducts());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByCustomer(String)}
     */
    @Test
    void testGetOrdersByCustomer() {
        // Arrange
        when(orderRepository.findByCustomer_Company(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<OrderDto> actualOrdersByCustomer = orderService.getOrdersByCustomer("Customer Company");

        // Assert
        verify(orderRepository).findByCustomer_Company(eq("Customer Company"));
        assertTrue(actualOrdersByCustomer.isEmpty());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByCustomer(String)}
     */
    @Test
    void testGetOrdersByCustomer2() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        when(orderRepository.findByCustomer_Company(Mockito.<String>any())).thenReturn(orderList);

        // Act
        List<OrderDto> actualOrdersByCustomer = orderService.getOrdersByCustomer("Customer Company");

        // Assert
        verify(orderRepository).findByCustomer_Company(eq("Customer Company"));
        assertEquals(1, actualOrdersByCustomer.size());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByCustomer(String)}
     */
    @Test
    void testGetOrdersByCustomer3() {
        // Arrange
        when(orderRepository.findByCustomer_Company(Mockito.<String>any()))
                .thenThrow(new ConflictException("An error occurred"));

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.getOrdersByCustomer("Customer Company"));
        verify(orderRepository).findByCustomer_Company(eq("Customer Company"));
    }

    /**
     * Method under test: {@link OrderService#getOrdersByProduct(String)}
     */
    @Test
    void testGetOrdersByProduct() {
        // Arrange
        when(orderItemLineService.getOrderItemLinesByProduct(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<OrderDto> actualOrdersByProduct = orderService.getOrdersByProduct("Product Name");

        // Assert
        verify(orderItemLineService).getOrderItemLinesByProduct(eq("Product Name"));
        assertTrue(actualOrdersByProduct.isEmpty());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByProduct(String)}
     */
    @Test
    void testGetOrdersByProduct2() {
        // Arrange
        when(orderItemLineService.getOrderItemLinesByProduct(Mockito.<String>any()))
                .thenThrow(new ConflictException("An error occurred"));

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.getOrdersByProduct("Product Name"));
        verify(orderItemLineService).getOrderItemLinesByProduct(eq("Product Name"));
    }

    /**
     * Method under test: {@link OrderService#getOrdersByProduct(String)}
     */
    @Test
    void testGetOrdersByProduct3() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Optional<Order> ofResult = Optional.of(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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

        Customer customer5 = new Customer();
        customer5.setAddress("42 Main St");
        customer5.setBankAccount("3");
        customer5.setCompany("Company");
        customer5.setDob(LocalDate.of(1970, 1, 1));
        customer5.setEmailAddress("42 Main St");
        customer5.setFirstName("Jane");
        customer5.setId(1L);
        customer5.setLastName("Doe");
        customer5.setOrders(new ArrayList<>());
        customer5.setPhoneNumber("6625550144");

        Order order3 = new Order();
        order3.setCustomer(new Customer());
        order3.setId(1L);
        order3.setInvoice(new Invoice());
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderTime("Order Time");
        order3.setProducts(new ArrayList<>());
        order3.setTotalPriceInEur(10.0d);

        Invoice invoice3 = new Invoice();
        invoice3.setCustomer(customer5);
        invoice3.setCustomerCompany("Customer Name");
        invoice3.setId(1L);
        invoice3.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice3.setOrder(order3);
        invoice3.setOrderNumber(10);

        Order order4 = new Order();
        order4.setCustomer(customer4);
        order4.setId(1L);
        order4.setInvoice(invoice3);
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderTime("Order Time");
        order4.setProducts(new ArrayList<>());
        order4.setTotalPriceInEur(10.0d);
        Product product = mock(Product.class);
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
        orderItemLine.setId(1L);
        orderItemLine.setOrder(order4);
        orderItemLine.setProduct(product);
        orderItemLine.setQuantity(1);
        orderItemLine.setTotalPrice(10.0d);

        ArrayList<OrderItemLine> orderItemLineList = new ArrayList<>();
        orderItemLineList.add(orderItemLine);
        when(orderItemLineService.getOrderItemLinesByProduct(Mockito.<String>any())).thenReturn(orderItemLineList);

        // Act
        List<OrderDto> actualOrdersByProduct = orderService.getOrdersByProduct("Product Name");

        // Assert
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
        verify(orderRepository).findById(eq(1L));
        verify(orderItemLineService).getOrderItemLinesByProduct(eq("Product Name"));
        assertEquals(1, actualOrdersByProduct.size());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByOrderDate(LocalDate)}
     */
    @Test
    void testGetOrdersByOrderDate() {
        // Arrange
        when(orderRepository.findByOrderDate(Mockito.<LocalDate>any())).thenReturn(new ArrayList<>());

        // Act
        List<OrderDto> actualOrdersByOrderDate = orderService.getOrdersByOrderDate(LocalDate.of(1970, 1, 1));

        // Assert
        verify(orderRepository).findByOrderDate(isA(LocalDate.class));
        assertTrue(actualOrdersByOrderDate.isEmpty());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByOrderDate(LocalDate)}
     */
    @Test
    void testGetOrdersByOrderDate2() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        when(orderRepository.findByOrderDate(Mockito.<LocalDate>any())).thenReturn(orderList);

        // Act
        List<OrderDto> actualOrdersByOrderDate = orderService.getOrdersByOrderDate(LocalDate.of(1970, 1, 1));

        // Assert
        verify(orderRepository).findByOrderDate(isA(LocalDate.class));
        assertEquals(1, actualOrdersByOrderDate.size());
    }

    /**
     * Method under test: {@link OrderService#getOrdersByOrderDate(LocalDate)}
     */
    @Test
    void testGetOrdersByOrderDate3() {
        // Arrange
        when(orderRepository.findByOrderDate(Mockito.<LocalDate>any()))
                .thenThrow(new ConflictException("An error occurred"));

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.getOrdersByOrderDate(LocalDate.of(1970, 1, 1)));
        verify(orderRepository).findByOrderDate(isA(LocalDate.class));
    }

    /**
     * Method under test: {@link OrderService#updateOrder(Long, OrderDtoUpdate)}
     */
    @Test
    void testUpdateOrder() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Optional<Order> ofResult = Optional.of(order2);

        Customer customer4 = new Customer();
        customer4.setAddress("42 Main St");
        customer4.setBankAccount("3");
        customer4.setCompany("Company");
        customer4.setDob(LocalDate.of(1970, 1, 1));
        customer4.setEmailAddress("42 Main St");
        customer4.setFirstName("Jane");
        customer4.setId(1L);
        customer4.setLastName("Doe");
        ArrayList<Order> orders = new ArrayList<>();
        customer4.setOrders(orders);
        customer4.setPhoneNumber("6625550144");

        Customer customer5 = new Customer();
        customer5.setAddress("42 Main St");
        customer5.setBankAccount("3");
        customer5.setCompany("Company");
        customer5.setDob(LocalDate.of(1970, 1, 1));
        customer5.setEmailAddress("42 Main St");
        customer5.setFirstName("Jane");
        customer5.setId(1L);
        customer5.setLastName("Doe");
        customer5.setOrders(new ArrayList<>());
        customer5.setPhoneNumber("6625550144");

        Customer customer6 = new Customer();
        customer6.setAddress("42 Main St");
        customer6.setBankAccount("3");
        customer6.setCompany("Company");
        customer6.setDob(LocalDate.of(1970, 1, 1));
        customer6.setEmailAddress("42 Main St");
        customer6.setFirstName("Jane");
        customer6.setId(1L);
        customer6.setLastName("Doe");
        customer6.setOrders(new ArrayList<>());
        customer6.setPhoneNumber("6625550144");

        Customer customer7 = new Customer();
        customer7.setAddress("42 Main St");
        customer7.setBankAccount("3");
        customer7.setCompany("Company");
        customer7.setDob(LocalDate.of(1970, 1, 1));
        customer7.setEmailAddress("42 Main St");
        customer7.setFirstName("Jane");
        customer7.setId(1L);
        customer7.setLastName("Doe");
        customer7.setOrders(new ArrayList<>());
        customer7.setPhoneNumber("6625550144");

        Order order3 = new Order();
        order3.setCustomer(new Customer());
        order3.setId(1L);
        order3.setInvoice(new Invoice());
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderTime("Order Time");
        order3.setProducts(new ArrayList<>());
        order3.setTotalPriceInEur(10.0d);

        Invoice invoice3 = new Invoice();
        invoice3.setCustomer(customer7);
        invoice3.setCustomerCompany("Customer Name");
        invoice3.setId(1L);
        invoice3.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice3.setOrder(order3);
        invoice3.setOrderNumber(10);

        Order order4 = new Order();
        order4.setCustomer(customer6);
        order4.setId(1L);
        order4.setInvoice(invoice3);
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderTime("Order Time");
        order4.setProducts(new ArrayList<>());
        order4.setTotalPriceInEur(10.0d);

        Invoice invoice4 = new Invoice();
        invoice4.setCustomer(customer5);
        invoice4.setCustomerCompany("Customer Name");
        invoice4.setId(1L);
        invoice4.setInvoiceData("AXAXAXAX".getBytes("UTF-8"));
        invoice4.setOrder(order4);
        invoice4.setOrderNumber(10);

        Order order5 = new Order();
        order5.setCustomer(customer4);
        order5.setId(1L);
        order5.setInvoice(invoice4);
        order5.setOrderDate(LocalDate.of(1970, 1, 1));
        order5.setOrderTime("Order Time");
        order5.setProducts(new ArrayList<>());
        order5.setTotalPriceInEur(10.0d);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order5);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        OrderDtoUpdate newOdto = new OrderDtoUpdate();
        newOdto.products = new ArrayList<>();

        // Act
        OrderDto actualUpdateOrderResult = orderService.updateOrder(1L, newOdto);

        // Assert
        verify(orderRepository).findById(eq(1L));
        verify(orderRepository).save(isA(Order.class));
        assertEquals("1970-01-01", actualUpdateOrderResult.getOrderDate().toString());
        assertEquals("Company", actualUpdateOrderResult.getCustomerCompany());
        assertEquals("Doe", actualUpdateOrderResult.getCustomerLastName());
        assertEquals("Jane", actualUpdateOrderResult.getCustomerFirstName());
        assertEquals("Order Time", actualUpdateOrderResult.getOrderTime());
        assertEquals(10.0d, actualUpdateOrderResult.getTotalPriceInEur());
        assertEquals(1L, actualUpdateOrderResult.getId().longValue());
        assertEquals(orders, actualUpdateOrderResult.getProducts());
    }

    /**
     * Method under test: {@link OrderService#updateOrder(Long, OrderDtoUpdate)}
     */
    @Test
    void testUpdateOrder2() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Optional<Order> ofResult = Optional.of(order2);
        when(orderRepository.save(Mockito.<Order>any())).thenThrow(new ConflictException("An error occurred"));
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        OrderDtoUpdate newOdto = new OrderDtoUpdate();
        newOdto.products = new ArrayList<>();

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.updateOrder(1L, newOdto));
        verify(orderRepository).findById(eq(1L));
        verify(orderRepository).save(isA(Order.class));
    }

    /**
     * Method under test:
     * {@link OrderService#addOrderItemLineToOrder(Long, OrderDtoAddProduct)}
     */
    @Test
    void testAddOrderItemLineToOrder() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Order order = mock(Order.class);
        when(order.getProducts()).thenThrow(new ConflictException("An error occurred"));
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository repos = mock(OrderRepository.class);
        when(repos.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CustomerRepository cRepos = mock(CustomerRepository.class);
        OrderItemLineRepository repos2 = mock(OrderItemLineRepository.class);
        OrderRepository orderRepos = mock(OrderRepository.class);
        ProductRepository productRepos = mock(ProductRepository.class);
        OrderItemLineService ilService = new OrderItemLineService(repos2, orderRepos, productRepos,
                new ProductService(mock(ProductRepository.class)));

        OrderItemLineRepository ilRepos = mock(OrderItemLineRepository.class);
        EmailSender emailSender = new EmailSender();
        InvoiceRepository invoiceRepos = mock(InvoiceRepository.class);
        InvoiceRepository repos3 = mock(InvoiceRepository.class);
        OrderRepository oRepos = mock(OrderRepository.class);
        ImageDataService imageService = new ImageDataService(mock(ImageDataRepository.class));
        OrderService orderService = new OrderService(repos, cRepos, ilService, ilRepos, emailSender, invoiceRepos,
                new InvoiceService(repos3, oRepos, imageService, new InvoiceUtil()));
        OrderDtoAddProduct newOdto = new OrderDtoAddProduct();
        newOdto.products = new ArrayList<>();

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.addOrderItemLineToOrder(1L, newOdto));
        verify(order).getProducts();
        verify(repos).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link OrderService#addOrderItemLineToOrder(Long, OrderDtoAddProduct)}
     */
    @Test
    void testAddOrderItemLineToOrder2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderRepository repos = mock(OrderRepository.class);
        Optional<Order> emptyResult = Optional.empty();
        when(repos.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        CustomerRepository cRepos = mock(CustomerRepository.class);
        OrderItemLineRepository repos2 = mock(OrderItemLineRepository.class);
        OrderRepository orderRepos = mock(OrderRepository.class);
        ProductRepository productRepos = mock(ProductRepository.class);
        OrderItemLineService ilService = new OrderItemLineService(repos2, orderRepos, productRepos,
                new ProductService(mock(ProductRepository.class)));

        OrderItemLineRepository ilRepos = mock(OrderItemLineRepository.class);
        EmailSender emailSender = new EmailSender();
        InvoiceRepository invoiceRepos = mock(InvoiceRepository.class);
        InvoiceRepository repos3 = mock(InvoiceRepository.class);
        OrderRepository oRepos = mock(OrderRepository.class);
        ImageDataService imageService = new ImageDataService(mock(ImageDataRepository.class));
        OrderService orderService = new OrderService(repos, cRepos, ilService, ilRepos, emailSender, invoiceRepos,
                new InvoiceService(repos3, oRepos, imageService, new InvoiceUtil()));
        OrderDtoAddProduct newOdto = new OrderDtoAddProduct();
        newOdto.products = new ArrayList<>();

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderService.addOrderItemLineToOrder(1L, newOdto));
        verify(repos).findById(eq(1L));
    }


    /**
     * Method under test: {@link OrderService#deleteOrder(Long)}
     */
    @Test
    void testDeleteOrder() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Optional<Order> ofResult = Optional.of(order2);
        doNothing().when(orderRepository).deleteById(Mockito.<Long>any());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Long actualDeleteOrderResult = orderService.deleteOrder(1L);

        // Assert
        verify(orderRepository).findById(eq(1L));
        verify(orderRepository).deleteById(eq(1L));
        assertEquals(1L, actualDeleteOrderResult.longValue());
    }

    /**
     * Method under test: {@link OrderService#deleteOrder(Long)}
     */
    @Test
    void testDeleteOrder2() throws UnsupportedEncodingException {
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

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setId(1L);
        order2.setInvoice(invoice2);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderTime("Order Time");
        order2.setProducts(new ArrayList<>());
        order2.setTotalPriceInEur(10.0d);
        Optional<Order> ofResult = Optional.of(order2);
        doThrow(new ConflictException("An error occurred")).when(orderRepository).deleteById(Mockito.<Long>any());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ConflictException.class, () -> orderService.deleteOrder(1L));
        verify(orderRepository).findById(eq(1L));
        verify(orderRepository).deleteById(eq(1L));
    }
}
