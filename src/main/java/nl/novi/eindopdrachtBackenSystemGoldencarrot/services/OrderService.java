package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import jakarta.mail.MessagingException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ModelMapperConfig;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.SetTimeAndDate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailMessage;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailSender;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repos;
    private final CustomerRepository cRepos;
    private final OrderItemLineService ilService;
    private final EmailSender emailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.recipient}")
    private String mailRecipient;


    public OrderService(OrderRepository repos, CustomerRepository cRepos, OrderItemLineService ilService, EmailSender emailSender) {

        this.repos = repos;
        this.cRepos = cRepos;
        this.ilService = ilService;
        this.emailSender = emailSender;
    }


    public OrderDto createOrder(OrderDto odto) throws MessagingException {

        Order order = new Order();
        order = SetTimeAndDate.SetOrderDateAndTime(order);
        repos.save(order);


        Long orderIdForIlines = order.getId();

        Customer c = cRepos.findByCompany((odto.customerCompany)).orElseThrow
                (() -> new ResourceNotFoundException("Customer not found"));

        order.setCustomer(c);

        List<OrderItemLine> orderItemLines = new ArrayList<>();

        for (OrderItemLineDto ildto : odto.products) {
            OrderItemLine il = ilService.createOrderItemLine(orderIdForIlines, ildto);
            orderItemLines.add(il);
        }
        order.setProducts(orderItemLines);
        repos.save(order);

        double totalPriceOrder = 0;

        for (OrderItemLine il : orderItemLines) {
            totalPriceOrder += il.getTotalPrice();
        }
        order.setTotalPriceInEur(totalPriceOrder);
        repos.save(order);


        emailSender.sendEmail(mailUsername,
                              mailPassword,
                new EmailMessage(
                mailRecipient,
                "New Invoice",
                "Hi best employee of finance," + "\n" +
                        "\n New invoice to download!" +
                        "\n InvoiceNumb: " + order.getId() +
                        "\n At: " + order.getOrderDate() +
                        "\n " + order.getOrderTime()));

        return ModelMapperConfig.mappingToDtoOrder(order);
    }

    public List<OrderDto> getOrders() {

        List<OrderDto> orderdtos = new ArrayList<>();
        Iterable<Order> orders = repos.findAll();

        for (Order order : orders) {

            OrderDto odto = ModelMapperConfig.mappingToDtoOrder(order);
            orderdtos.add(odto);
        }

        return orderdtos;
    }

    public OrderDto getOrderById(Long id) {

        Order order = repos.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("Order not found"));

        return ModelMapperConfig.mappingToDtoOrder(order);
    }

    public List<OrderDto> getOrdersByCustomer(String customerCompany) {

        List<OrderDto> orderdtos = new ArrayList<>();
        Iterable<Order> orders = repos.findByCustomer_Company(customerCompany);

        for (Order order : orders) {

            OrderDto odto = ModelMapperConfig.mappingToDtoOrder(order);
            orderdtos.add(odto);
        }

        return orderdtos;
    }

    public List<OrderDto> getOrdersByProduct(String productName) {

        List<OrderItemLine> itemlines = ilService.getOrderItemLinesByProduct(productName);
        List<Order> orders = new ArrayList<>();

        for (OrderItemLine il : itemlines) {
            Order order = repos.findById(il.getOrder().getId()).orElseThrow(()
                    -> new ResourceNotFoundException("Order not found"));
            orders.add(order);
        }

        List<OrderDto> odtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto odto = ModelMapperConfig.mappingToDtoOrder(order);
            odtos.add(odto);
        }
        return odtos;
    }


    public List<OrderDto> getOrdersByOrderDate(LocalDate orderDate) {

        List<Order> orders = repos.findByOrderDate(orderDate);

        List<OrderDto> odtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto odto = ModelMapperConfig.mappingToDtoOrder(order);
            odtos.add(odto);
        }
        return odtos;
    }

    public OrderDto updateOrder(Long id, OrderDto newOdto) {

        for (OrderItemLineDto ildto : newOdto.products) {
            ilService.updateOrderItemline(id, ildto);
        }

        Order order = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("order not found"));
        List<OrderItemLine> orderItemLines = order.getProducts();

        double totalPriceOrder = 0;
        for (OrderItemLine il : orderItemLines) {
            totalPriceOrder += il.getTotalPrice();
        }

        order.setTotalPriceInEur(totalPriceOrder);
        repos.save(order);

        return ModelMapperConfig.mappingToDtoOrder(order);
    }

    public Long deleteOrder(Long id) {

        Order order = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("order not found"));

        for (OrderItemLine il : order.getProducts()) {
            ilService.deleteOrderItemLine(il.getProduct().getName(), id);
        }
        repos.deleteById(id);
        return id;
    }

}

