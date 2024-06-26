package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import jakarta.mail.MessagingException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.*;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderItemLineRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.SetTimeAndDate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailMessage;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository repos;

    @Mock
    OrderItemLineService ilService;

    @Mock
    OrderItemLineRepository ilRepos;

    @Mock
    CustomerRepository cRepos;

    @Mock
    EmailSender emailSender;

    @Mock
    InvoiceService invoiceService;


    @InjectMocks
    OrderService service;

    @Test
    void shouldSaveAndReturnCorrectOrder() throws MessagingException {


        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerCompany("Test Company");
        List<OrderItemLineDto> products = new ArrayList<>();
        orderDto.setProducts(products);

        Order order = new Order();
        order.setId(1L);
        order = SetTimeAndDate.SetOrderDateAndTime(order);

        Customer customer = new Customer();
        customer.setCompany("Test Company");

        List<OrderItemLine> orderitemlines = new ArrayList<>();
        OrderItemLine orderItemLine = new OrderItemLine();
        orderItemLine.setTotalPrice(100.0);
        orderitemlines.add(orderItemLine);

        Invoice invoice = new Invoice();
        invoice.setInvoiceData(new byte[]{1, 2, 3});

        when(repos.save(any(Order.class))).thenReturn(order);
        when(cRepos.findByCompany("Test Company")).thenReturn(Optional.of(customer));
        doNothing().when(emailSender).sendEmail(any(), any(), any(EmailMessage.class));
        when(invoiceService.generateInvoicePdf(anyLong())).thenReturn(invoice);


        OrderDto result = service.createOrder(orderDto);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repos, times(3)).save(any(Order.class));
        verify(emailSender, times(1)).sendEmail(any(), any(), any(EmailMessage.class));
    }


    @Test
    void shouldReturnOrdersByProduct() {

        Order order1 = new Order();
        order1.setId(1L);
        Order order2 = new Order();
        order2.setId(2L);

        List<OrderItemLine> itemLines = new ArrayList<>();

        Product product1 = new Product();
        product1.setName("Salmon side");
        product1.setPriceInEur(32.14);
        OrderItemLine itemLine1 = new OrderItemLine();
        itemLine1.setProduct(product1);
        itemLine1.setQuantity(3);
        itemLine1.setTotalPrice(96.42);

        Product product2 = new Product();
        product2.setName("Lobster");
        product2.setPriceInEur(22.84);
        OrderItemLine itemLine2 = new OrderItemLine();
        itemLine2.setProduct(product2);
        itemLine2.setQuantity(4);
        itemLine2.setTotalPrice(91.36);
        itemLine2.setOrder(order1);

        itemLine1.setOrder(order1);
        itemLines.add(itemLine1);

        itemLine2.setOrder(order1);
        itemLines.add(itemLine2);

        order1.setProducts(itemLines);

        Product product3 = new Product();
        product3.setName("beef");
        product3.setPriceInEur(34.00);
        OrderItemLine itemLine3 = new OrderItemLine();
        itemLine3.setProduct(product3);
        itemLine3.setQuantity(2);
        itemLine3.setTotalPrice(68.00);
        itemLine3.setOrder(order2);

        List<OrderItemLine> oiLines = new ArrayList<>();
        oiLines.add(itemLine1);

        String productName = "Salmon side";

        Mockito.when(ilService.getOrderItemLinesByProduct(productName)).thenReturn(oiLines);
        Mockito.when(repos.findById(1L)).thenReturn(Optional.of(order1));

        List<OrderDto> result = service.getOrdersByProduct(productName);


        assertEquals(1, result.size());

        OrderDto orderDto = result.get(0);
        assertEquals(order1.getId(), orderDto.getId());

        verify(ilService, times(1)).getOrderItemLinesByProduct(productName);
        verify(repos, times(1)).findById(1L);
        verify(repos, times(0)).findById(2L);
    }


    @Test
    void shouldReturnOrdersByCustomer() {

        Customer c = new Customer();
        c.setCompany("ExampleCompany");

        Order order1 = new Order();
        order1.setCustomer(c);

        Order order2 = new Order();
        order2.setCustomer(c);

        when(repos.findByCustomer_Company("ExampleCompany"))
                .thenReturn(List.of(order1, order2));

        List<OrderDto> result = service.getOrdersByCustomer("ExampleCompany");

        verify(repos, times(1)).findByCustomer_Company("ExampleCompany");

        assertEquals(2, result.size());

    }
}

