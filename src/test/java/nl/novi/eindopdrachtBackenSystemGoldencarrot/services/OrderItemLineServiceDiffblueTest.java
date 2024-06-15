package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.*;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderItemLineRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ProductRepository;
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

@ContextConfiguration(classes = {OrderItemLineService.class})
@ExtendWith(SpringExtension.class)
class OrderItemLineServiceDiffblueTest {
    @MockBean
    private OrderItemLineRepository orderItemLineRepository;

    @Autowired
    private OrderItemLineService orderItemLineService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    /**
     * Method under test:
     * {@link OrderItemLineService#createOrderItemLine(Long, OrderItemLineDto)}
     */
    @Test
    void testCreateOrderItemLine() throws UnsupportedEncodingException {
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
        when(productRepository.findByName(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        OrderItemLineDto ildto = new OrderItemLineDto();
        ildto.setId(1L);
        ildto.setProductName("Product Name");
        ildto.setProductPriceInEur(10.0d);
        ildto.setQuantity(1);
        ildto.setShortDescriptionProduct("Short Description Product");
        ildto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.createOrderItemLine(1L, ildto));
        verify(orderRepository).findById(eq(1L));
        verify(productRepository).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        Optional<OrderItemLine> ofResult = Optional.of(mock(OrderItemLine.class));
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        OrderRepository orderRepos = mock(OrderRepository.class);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, orderRepos, productRepos,
                new ProductService(mock(ProductRepository.class)));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(productRepos).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLine orderItemLine = mock(OrderItemLine.class);
        when(orderItemLine.calculateTotalPrice()).thenReturn(10.0d);
        doNothing().when(orderItemLine).setQuantity(anyInt());
        doNothing().when(orderItemLine).setTotalPrice(Mockito.<Double>any());
        when(orderItemLine.getQuantity()).thenReturn(1);
        Optional<OrderItemLine> ofResult = Optional.of(orderItemLine);
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        when(repos.save(Mockito.<OrderItemLine>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        Product product = mock(Product.class);
        when(product.getName()).thenReturn("Name");
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
        Optional<Product> ofResult2 = Optional.of(product);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getName()).thenReturn("Name");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult3 = Optional.of(product2);
        Product product3 = mock(Product.class);
        doNothing().when(product3).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product3).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product3).setCategory(Mockito.<String>any());
        doNothing().when(product3).setDescription(Mockito.<String>any());
        doNothing().when(product3).setId(Mockito.<Long>any());
        doNothing().when(product3).setInStock(anyInt());
        doNothing().when(product3).setName(Mockito.<String>any());
        doNothing().when(product3).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product3).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product3).setShortDescription(Mockito.<String>any());
        product3.lowerInStockForIncomingOrder(1);
        product3.restoreInStockForChangedOrder(1);
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setId(1L);
        product3.setInStock(1);
        product3.setName("Name");
        product3.setPackedPerUnit("Packed Per Unit");
        product3.setPriceInEur(10.0d);
        product3.setShortDescription("Short Description");
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.save(Mockito.<Product>any())).thenReturn(product3);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult3);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, mock(OrderRepository.class),
                productRepos, new ProductService(repos2));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(orderItemLine).calculateTotalPrice();
        verify(orderItemLine).getQuantity();
        verify(orderItemLine).setQuantity(eq(1));
        verify(orderItemLine).setTotalPrice(eq(10.0d));
        verify(product2).getInStock();
        verify(product, atLeast(1)).getName();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product3).lowerInStockForIncomingOrder(eq(1));
        verify(product2, atLeast(1)).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product3).restoreInStockForChangedOrder(eq(1));
        verify(product2, atLeast(1)).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product3).setCategory(eq("Category"));
        verify(product2).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product3).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product3).setId(eq(1L));
        verify(product2).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product3).setInStock(eq(1));
        verify(product2).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product3).setName(eq("Name"));
        verify(product2).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product3).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product3).setPriceInEur(eq(10.0d));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product3).setShortDescription(eq("Short Description"));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(repos2, atLeast(1)).findByName(eq("Name"));
        verify(productRepos).findByName(eq("Product Name"));
        verify(repos).save(isA(OrderItemLine.class));
        verify(repos2, atLeast(1)).save(isA(Product.class));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLine orderItemLine = mock(OrderItemLine.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(orderItemLine).setQuantity(anyInt());
        when(orderItemLine.getQuantity()).thenReturn(1);
        Optional<OrderItemLine> ofResult = Optional.of(orderItemLine);
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        when(repos.save(Mockito.<OrderItemLine>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        Product product = mock(Product.class);
        when(product.getName()).thenReturn("Name");
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
        Optional<Product> ofResult2 = Optional.of(product);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getName()).thenReturn("Name");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult3 = Optional.of(product2);
        Product product3 = mock(Product.class);
        doNothing().when(product3).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product3).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product3).setCategory(Mockito.<String>any());
        doNothing().when(product3).setDescription(Mockito.<String>any());
        doNothing().when(product3).setId(Mockito.<Long>any());
        doNothing().when(product3).setInStock(anyInt());
        doNothing().when(product3).setName(Mockito.<String>any());
        doNothing().when(product3).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product3).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product3).setShortDescription(Mockito.<String>any());
        product3.lowerInStockForIncomingOrder(1);
        product3.restoreInStockForChangedOrder(1);
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setId(1L);
        product3.setInStock(1);
        product3.setName("Name");
        product3.setPackedPerUnit("Packed Per Unit");
        product3.setPriceInEur(10.0d);
        product3.setShortDescription("Short Description");
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.save(Mockito.<Product>any())).thenReturn(product3);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult3);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, mock(OrderRepository.class),
                productRepos, new ProductService(repos2));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(orderItemLine).getQuantity();
        verify(orderItemLine).setQuantity(eq(1));
        verify(product2).getInStock();
        verify(product, atLeast(1)).getName();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product3).lowerInStockForIncomingOrder(eq(1));
        verify(product2, atLeast(1)).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product3).restoreInStockForChangedOrder(eq(1));
        verify(product2, atLeast(1)).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product3).setCategory(eq("Category"));
        verify(product2).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product3).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product3).setId(eq(1L));
        verify(product2).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product3).setInStock(eq(1));
        verify(product2).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product3).setName(eq("Name"));
        verify(product2).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product3).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product3).setPriceInEur(eq(10.0d));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product3).setShortDescription(eq("Short Description"));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(repos2, atLeast(1)).findByName(eq("Name"));
        verify(productRepos).findByName(eq("Product Name"));
        verify(repos2, atLeast(1)).save(isA(Product.class));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        when(repos.save(Mockito.<OrderItemLine>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        Optional<OrderItemLine> emptyResult = Optional.empty();
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(emptyResult);
        OrderRepository orderRepos = mock(OrderRepository.class);
        Optional<Order> ofResult = Optional.of(mock(Order.class));
        when(orderRepos.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Product product = mock(Product.class);
        when(product.getPriceInEur()).thenReturn(10.0d);
        when(product.getName()).thenReturn("Name");
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
        Optional<Product> ofResult2 = Optional.of(product);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getName()).thenReturn("Name");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult3 = Optional.of(product2);
        Product product3 = mock(Product.class);
        doNothing().when(product3).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product3).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product3).setCategory(Mockito.<String>any());
        doNothing().when(product3).setDescription(Mockito.<String>any());
        doNothing().when(product3).setId(Mockito.<Long>any());
        doNothing().when(product3).setInStock(anyInt());
        doNothing().when(product3).setName(Mockito.<String>any());
        doNothing().when(product3).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product3).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product3).setShortDescription(Mockito.<String>any());
        product3.lowerInStockForIncomingOrder(1);
        product3.restoreInStockForChangedOrder(1);
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setId(1L);
        product3.setInStock(1);
        product3.setName("Name");
        product3.setPackedPerUnit("Packed Per Unit");
        product3.setPriceInEur(10.0d);
        product3.setShortDescription("Short Description");
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.save(Mockito.<Product>any())).thenReturn(product3);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult3);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, orderRepos, productRepos,
                new ProductService(repos2));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(product2).getInStock();
        verify(product).getName();
        verify(product).getPriceInEur();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product3).lowerInStockForIncomingOrder(eq(1));
        verify(product2, atLeast(1)).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product3).restoreInStockForChangedOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product3).setCategory(eq("Category"));
        verify(product2).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product3).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product3).setId(eq(1L));
        verify(product2).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product3).setInStock(eq(1));
        verify(product2).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product3).setName(eq("Name"));
        verify(product2).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product3).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product3).setPriceInEur(eq(10.0d));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product3).setShortDescription(eq("Short Description"));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(orderRepos).findById(eq(1L));
        verify(repos2).findByName(eq("Name"));
        verify(productRepos, atLeast(1)).findByName(eq("Product Name"));
        verify(repos).save(isA(OrderItemLine.class));
        verify(repos2).save(isA(Product.class));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        when(repos.save(Mockito.<OrderItemLine>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        Optional<OrderItemLine> emptyResult = Optional.empty();
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(emptyResult);
        OrderRepository orderRepos = mock(OrderRepository.class);
        Optional<Order> ofResult = Optional.of(mock(Order.class));
        when(orderRepos.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Product product = mock(Product.class);
        when(product.getPriceInEur()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(product.getName()).thenReturn("Name");
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
        Optional<Product> ofResult2 = Optional.of(product);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        Product product2 = mock(Product.class);
        when(product2.getInStock()).thenReturn(1);
        when(product2.getName()).thenReturn("Name");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult3 = Optional.of(product2);
        Product product3 = mock(Product.class);
        doNothing().when(product3).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product3).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product3).setCategory(Mockito.<String>any());
        doNothing().when(product3).setDescription(Mockito.<String>any());
        doNothing().when(product3).setId(Mockito.<Long>any());
        doNothing().when(product3).setInStock(anyInt());
        doNothing().when(product3).setName(Mockito.<String>any());
        doNothing().when(product3).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product3).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product3).setShortDescription(Mockito.<String>any());
        product3.lowerInStockForIncomingOrder(1);
        product3.restoreInStockForChangedOrder(1);
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setId(1L);
        product3.setInStock(1);
        product3.setName("Name");
        product3.setPackedPerUnit("Packed Per Unit");
        product3.setPriceInEur(10.0d);
        product3.setShortDescription("Short Description");
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.save(Mockito.<Product>any())).thenReturn(product3);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult3);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, orderRepos, productRepos,
                new ProductService(repos2));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(product2).getInStock();
        verify(product).getName();
        verify(product).getPriceInEur();
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product3).lowerInStockForIncomingOrder(eq(1));
        verify(product2, atLeast(1)).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product3).restoreInStockForChangedOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product3).setCategory(eq("Category"));
        verify(product2).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product3).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product3).setId(eq(1L));
        verify(product2).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product3).setInStock(eq(1));
        verify(product2).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product3).setName(eq("Name"));
        verify(product2).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product3).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product3).setPriceInEur(eq(10.0d));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product3).setShortDescription(eq("Short Description"));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(orderRepos).findById(eq(1L));
        verify(repos2).findByName(eq("Name"));
        verify(productRepos, atLeast(1)).findByName(eq("Product Name"));
        verify(repos2).save(isA(Product.class));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
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
        OrderItemLine orderItemLine = mock(OrderItemLine.class);
        when(orderItemLine.getQuantity()).thenThrow(new ResourceNotFoundException("An error occurred"));
        Optional<OrderItemLine> ofResult = Optional.of(orderItemLine);
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        Product product2 = mock(Product.class);
        when(product2.getName()).thenReturn("Name");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult2 = Optional.of(product2);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        Product product3 = mock(Product.class);
        when(product3.getName()).thenReturn("Name");
        doNothing().when(product3).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product3).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product3).setCategory(Mockito.<String>any());
        doNothing().when(product3).setDescription(Mockito.<String>any());
        doNothing().when(product3).setId(Mockito.<Long>any());
        doNothing().when(product3).setInStock(anyInt());
        doNothing().when(product3).setName(Mockito.<String>any());
        doNothing().when(product3).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product3).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product3).setShortDescription(Mockito.<String>any());
        product3.lowerInStockForIncomingOrder(1);
        product3.restoreInStockForChangedOrder(1);
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setId(1L);
        product3.setInStock(1);
        product3.setName("Name");
        product3.setPackedPerUnit("Packed Per Unit");
        product3.setPriceInEur(10.0d);
        product3.setShortDescription("Short Description");
        Optional<Product> ofResult3 = Optional.of(product3);
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult3);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, mock(OrderRepository.class),
                productRepos, new ProductService(repos2));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(orderItemLine).getQuantity();
        verify(product2).getName();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product3).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product3).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product3).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product3).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product3).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product).setInStock(eq(1));
        verify(product3).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product3).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product3).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product3).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product3).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(productRepos).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline7() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
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
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        Optional<OrderItemLine> ofResult = Optional.of(mock(OrderItemLine.class));
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        ProductRepository productRepos = mock(ProductRepository.class);
        Optional<Product> emptyResult = Optional.empty();
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        Product product2 = mock(Product.class);
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult2 = Optional.of(product2);
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, mock(OrderRepository.class),
                productRepos, new ProductService(repos2));

        OrderItemLineDto newIldto = new OrderItemLineDto();
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product2).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product2).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product2).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product2).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(productRepos).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#updateOrderItemline(Long, OrderItemLineDto)}
     */
    @Test
    void testUpdateOrderItemline8() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
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
        OrderItemLine orderItemLine = mock(OrderItemLine.class);
        when(orderItemLine.getQuantity()).thenThrow(new ResourceNotFoundException("An error occurred"));
        Optional<OrderItemLine> ofResult = Optional.of(orderItemLine);
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        Product product2 = mock(Product.class);
        when(product2.getName()).thenReturn("Name");
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional<Product> ofResult2 = Optional.of(product2);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        Product product3 = mock(Product.class);
        when(product3.getName()).thenReturn("Name");
        doNothing().when(product3).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product3).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product3).setCategory(Mockito.<String>any());
        doNothing().when(product3).setDescription(Mockito.<String>any());
        doNothing().when(product3).setId(Mockito.<Long>any());
        doNothing().when(product3).setInStock(anyInt());
        doNothing().when(product3).setName(Mockito.<String>any());
        doNothing().when(product3).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product3).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product3).setShortDescription(Mockito.<String>any());
        product3.lowerInStockForIncomingOrder(1);
        product3.restoreInStockForChangedOrder(1);
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setId(1L);
        product3.setInStock(1);
        product3.setName("Name");
        product3.setPackedPerUnit("Packed Per Unit");
        product3.setPriceInEur(10.0d);
        product3.setShortDescription("Short Description");
        Optional<Product> ofResult3 = Optional.of(product3);
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult3);
        OrderItemLineService orderItemLineService = new OrderItemLineService(repos, mock(OrderRepository.class),
                productRepos, new ProductService(repos2));
        OrderItemLineDto newIldto = mock(OrderItemLineDto.class);
        doNothing().when(newIldto).setId(Mockito.<Long>any());
        doNothing().when(newIldto).setProductName(Mockito.<String>any());
        doNothing().when(newIldto).setProductPriceInEur(anyDouble());
        doNothing().when(newIldto).setQuantity(anyInt());
        doNothing().when(newIldto).setShortDescriptionProduct(Mockito.<String>any());
        doNothing().when(newIldto).setTotalPrice(anyDouble());
        newIldto.setId(1L);
        newIldto.setProductName("Product Name");
        newIldto.setProductPriceInEur(10.0d);
        newIldto.setQuantity(1);
        newIldto.setShortDescriptionProduct("Short Description Product");
        newIldto.setTotalPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> orderItemLineService.updateOrderItemline(1L, newIldto));
        verify(newIldto).setId(eq(1L));
        verify(newIldto).setProductName(eq("Product Name"));
        verify(newIldto).setProductPriceInEur(eq(10.0d));
        verify(newIldto).setQuantity(eq(1));
        verify(newIldto).setShortDescriptionProduct(eq("Short Description Product"));
        verify(newIldto).setTotalPrice(eq(10.0d));
        verify(orderItemLine).getQuantity();
        verify(product2, atLeast(1)).getName();
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product3).lowerInStockForIncomingOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product3).restoreInStockForChangedOrder(eq(1));
        verify(product2).setCategory(eq("Category"));
        verify(product).setCategory(eq("Category"));
        verify(product3).setCategory(eq("Category"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product3).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setId(eq(1L));
        verify(product).setId(eq(1L));
        verify(product3).setId(eq(1L));
        verify(product2).setInStock(eq(1));
        verify(product).setInStock(eq(1));
        verify(product3).setInStock(eq(1));
        verify(product2).setName(eq("Name"));
        verify(product).setName(eq("Name"));
        verify(product3).setName(eq("Name"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product3).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product3).setPriceInEur(eq(10.0d));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product3).setShortDescription(eq("Short Description"));
        verify(repos, atLeast(1)).findByOrder_IdAndProduct_Name(eq(1L), Mockito.<String>any());
        verify(productRepos, atLeast(1)).findByName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#deleteOrderItemLine(String, Long)}
     */
    @Test
    void testDeleteOrderItemLine() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        Optional<OrderItemLine> ofResult = Optional.of(mock(OrderItemLine.class));
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        OrderRepository orderRepos = mock(OrderRepository.class);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> (new OrderItemLineService(repos, orderRepos, productRepos,
                new ProductService(mock(ProductRepository.class)))).deleteOrderItemLine("Product Name", 1L));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(productRepos).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#deleteOrderItemLine(String, Long)}
     */
    @Test
    void testDeleteOrderItemLine2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
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
        Optional.of(product);
        Product product2 = mock(Product.class);
        doNothing().when(product2).lowerInStockForIncomingOrder(anyInt());
        doNothing().when(product2).restoreInStockForChangedOrder(anyInt());
        doNothing().when(product2).setCategory(Mockito.<String>any());
        doNothing().when(product2).setDescription(Mockito.<String>any());
        doNothing().when(product2).setId(Mockito.<Long>any());
        doNothing().when(product2).setInStock(anyInt());
        doNothing().when(product2).setName(Mockito.<String>any());
        doNothing().when(product2).setPackedPerUnit(Mockito.<String>any());
        doNothing().when(product2).setPriceInEur(Mockito.<Double>any());
        doNothing().when(product2).setShortDescription(Mockito.<String>any());
        product2.lowerInStockForIncomingOrder(1);
        product2.restoreInStockForChangedOrder(1);
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setInStock(1);
        product2.setName("Name");
        product2.setPackedPerUnit("Packed Per Unit");
        product2.setPriceInEur(10.0d);
        product2.setShortDescription("Short Description");
        Optional.of(product2);
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        Optional<OrderItemLine> emptyResult = Optional.empty();
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(emptyResult);
        OrderRepository orderRepos = mock(OrderRepository.class);
        ProductRepository productRepos = mock(ProductRepository.class);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> (new OrderItemLineService(repos, orderRepos, productRepos,
                new ProductService(mock(ProductRepository.class)))).deleteOrderItemLine("Product Name", 1L));
        verify(product).lowerInStockForIncomingOrder(eq(1));
        verify(product2).lowerInStockForIncomingOrder(eq(1));
        verify(product).restoreInStockForChangedOrder(eq(1));
        verify(product2).restoreInStockForChangedOrder(eq(1));
        verify(product).setCategory(eq("Category"));
        verify(product2).setCategory(eq("Category"));
        verify(product).setDescription(eq("The characteristics of someone or something"));
        verify(product2).setDescription(eq("The characteristics of someone or something"));
        verify(product).setId(eq(1L));
        verify(product2).setId(eq(1L));
        verify(product).setInStock(eq(1));
        verify(product2).setInStock(eq(1));
        verify(product).setName(eq("Name"));
        verify(product2).setName(eq("Name"));
        verify(product).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product2).setPackedPerUnit(eq("Packed Per Unit"));
        verify(product).setPriceInEur(eq(10.0d));
        verify(product2).setPriceInEur(eq(10.0d));
        verify(product).setShortDescription(eq("Short Description"));
        verify(product2).setShortDescription(eq("Short Description"));
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#deleteOrderItemLine(String, Long)}
     */
    @Test
    void testDeleteOrderItemLine3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        Optional<OrderItemLine> ofResult = Optional.of(mock(OrderItemLine.class));
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        ProductRepository productRepos = mock(ProductRepository.class);
        Optional<Product> emptyResult = Optional.empty();
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(emptyResult);
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
        Optional<Product> ofResult2 = Optional.of(product);
        ProductRepository repos2 = mock(ProductRepository.class);
        when(repos2.findByName(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> (new OrderItemLineService(repos, mock(OrderRepository.class), productRepos, new ProductService(repos2)))
                        .deleteOrderItemLine("Product Name", 1L));
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
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(productRepos).findByName(eq("Product Name"));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#deleteOrderItemLine(String, Long)}
     */
    @Test
    void testDeleteOrderItemLine4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        new ResourceNotFoundException("An error occurred");
        OrderItemLine orderItemLine = mock(OrderItemLine.class);
        when(orderItemLine.getQuantity()).thenReturn(1);
        Optional<OrderItemLine> ofResult = Optional.of(orderItemLine);
        OrderItemLineRepository repos = mock(OrderItemLineRepository.class);
        doNothing().when(repos).delete(Mockito.<OrderItemLine>any());
        when(repos.findByOrder_IdAndProduct_Name(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(ofResult);
        Product product = mock(Product.class);
        when(product.getName()).thenReturn("Name");
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
        Optional<Product> ofResult2 = Optional.of(product);
        ProductRepository productRepos = mock(ProductRepository.class);
        when(productRepos.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).restoreInStockForChangedOrder(Mockito.<String>any(), anyInt());

        // Act
        (new OrderItemLineService(repos, mock(OrderRepository.class), productRepos, productService))
                .deleteOrderItemLine("Product Name", 1L);

        // Assert
        verify(orderItemLine).getQuantity();
        verify(product).getName();
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
        verify(repos).findByOrder_IdAndProduct_Name(eq(1L), eq("Product Name"));
        verify(productRepos).findByName(eq("Product Name"));
        verify(productService).restoreInStockForChangedOrder(eq("Name"), eq(1));
        verify(repos).delete(isA(OrderItemLine.class));
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#getOrderItemLinesByProduct(String)}
     */
    @Test
    void testGetOrderItemLinesByProduct() {
        // Arrange
        ArrayList<OrderItemLine> orderItemLineList = new ArrayList<>();
        when(orderItemLineRepository.findByProduct_Name(Mockito.<String>any())).thenReturn(orderItemLineList);

        // Act
        List<OrderItemLine> actualOrderItemLinesByProduct = orderItemLineService.getOrderItemLinesByProduct("Product Name");

        // Assert
        verify(orderItemLineRepository).findByProduct_Name(eq("Product Name"));
        assertTrue(actualOrderItemLinesByProduct.isEmpty());
        assertSame(orderItemLineList, actualOrderItemLinesByProduct);
    }

    /**
     * Method under test:
     * {@link OrderItemLineService#getOrderItemLinesByProduct(String)}
     */
    @Test
    void testGetOrderItemLinesByProduct2() {
        // Arrange
        when(orderItemLineRepository.findByProduct_Name(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> orderItemLineService.getOrderItemLinesByProduct("Product Name"));
        verify(orderItemLineRepository).findByProduct_Name(eq("Product Name"));
    }
}
