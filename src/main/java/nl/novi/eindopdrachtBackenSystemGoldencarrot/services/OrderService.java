package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import jakarta.mail.MessagingException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDtoAddProduct;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDtoRemoveProduct;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ConflictException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Invoice;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.InvoiceRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderItemLineRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ModelMapperConfig;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.SetTimeAndDate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailMessage;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final OrderItemLineRepository ilRepos;
    private final EmailSender emailSender;
    private final InvoiceRepository invoiceRepos;
    private final InvoiceService invoiceService;

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    @Value("${mail.recipient}")
    private String mailRecipient;


    @Autowired
    public OrderService(OrderRepository repos, CustomerRepository cRepos, OrderItemLineService ilService,
                        OrderItemLineRepository ilRepos, EmailSender emailSender, InvoiceRepository invoiceRepos,
                        InvoiceService invoiceService) {

        this.repos = repos;
        this.cRepos = cRepos;
        this.ilService = ilService;
        this.ilRepos = ilRepos;
        this.emailSender = emailSender;
        this.invoiceRepos = invoiceRepos;
        this.invoiceService = invoiceService;
    }


    public OrderDto createOrder(OrderDto odto) throws MessagingException {

        Order order = new Order();
        order = SetTimeAndDate.SetOrderDateAndTime(order);
        order = repos.save(order);

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
        order = repos.save(order);

     Invoice invoice = invoiceService.generateInvoicePdf(order.getId());

     if (invoice.getInvoiceData() == null){
         throw new ConflictException("invoice is empty, problems generating invoice");
     }
              order.setInvoice(invoice);

        emailSender.sendEmail(mailUsername,
             mailPassword,
        new EmailMessage(
                mailRecipient,
                "New Invoice",
                "Hi best employee of finance," + "\n" +
                        "\n New invoice to send to customer!" +
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

    public OrderDto updateOrder(Long orderId, OrderDtoUpdate newOdto) {

        for (OrderItemLineDto ildto : newOdto.products) {
            ilService.updateOrderItemline(orderId, ildto);
        }

        Order order = repos.findById(orderId).orElseThrow
                (() -> new ResourceNotFoundException("Order not found"));

        List<OrderItemLine> orderItemLines = order.getProducts();

        double totalPriceOrder = 0;
        for (OrderItemLine il : orderItemLines) {
            totalPriceOrder += il.getTotalPrice();
        }

        order.setTotalPriceInEur(totalPriceOrder);
        order = repos.save(order);

        return ModelMapperConfig.mappingToDtoOrder(order);
    }


    public OrderDto addOrderItemLineToOrder(Long orderId, OrderDtoAddProduct newOdto) {

        Order order= repos.findById(orderId).orElseThrow(() -> new ResourceNotFoundException
                ("order not found"));
        List<OrderItemLine> orderItemLinesCheck = order.getProducts();

        for (OrderItemLine ilCheck : orderItemLinesCheck) {
            if (ilCheck.getProduct().getName().equals(newOdto.products.get(0).productName)) {
                throw new ConflictException
                        ("Dit product bevindt zich al in de order, pas het gewenste aantal aan via PUT order");
            }
        }

        OrderItemLineDto ilDto = newOdto.products.get(0);
        ilService.createOrderItemLine(orderId, ilDto);

        order = repos.save(order);
        List<OrderItemLine> orderItemLines = order.getProducts();

        double totalPriceOrder = 0;
        for (OrderItemLine il : orderItemLines) {
            totalPriceOrder += il.getTotalPrice();
        }

        order.setTotalPriceInEur(totalPriceOrder);
        repos.save(order);

        return ModelMapperConfig.mappingToDtoOrder(order);
    }

    public OrderDto deleteOrderItemLineFromOrder(Long orderId, OrderDtoRemoveProduct newOdto) {

        OrderItemLine ilToDelete = ilRepos.findByOrder_IdAndProduct_Name(orderId, newOdto.products.get(0)
                .getProductName()).orElseThrow(() -> new ResourceNotFoundException
                ("Order met id \"" + orderId + "\" bevat geen bestellijn met ingevoerde product"));

        ilService.deleteOrderItemLine(ilToDelete.getProduct().getName(), orderId);

        Order order = repos.findById(orderId).orElseThrow(() -> new ResourceNotFoundException
                ("order not found"));
        List<OrderItemLine> orderItemLines = order.getProducts();

        double totalPriceOrder = 0;
        for (OrderItemLine il : orderItemLines) {
            totalPriceOrder += il.getTotalPrice();
        }

        order.setTotalPriceInEur(totalPriceOrder);
        order = repos.save(order);

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

