package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.BindingValidator;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderDto odto,
                                              BindingResult br) throws MessagingException {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        odto = service.createOrder(odto);

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + odto.id).toUriString());

        return ResponseEntity.created(uri).body(odto);
    }


    @GetMapping()
    public List<OrderDto> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto odto = service.getOrderById(id);
        return ResponseEntity.ok(odto);
    }

    @GetMapping("/customer/{customerCompany}")
    public List<OrderDto> getOrdersFromCustomer(@PathVariable String customerCompany) {
        return service.getOrdersByCustomer(customerCompany);
    }

    @GetMapping("/product/{productName}")
    public List<OrderDto> getOrdersByProduct(@PathVariable String productName) {
        return service.getOrdersByProduct(productName);
    }

    @GetMapping("/date/{orderDate}")
    public List<OrderDto> getOrdersByDate(@PathVariable LocalDate orderDate) {
        return service.getOrdersByOrderDate(orderDate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable Long id, @RequestBody
    OrderDto newOdto, BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }

        OrderDto odto = service.updateOrder(id, newOdto);
        return ResponseEntity.ok(odto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Long deletedOrder = service.deleteOrder(id);
        return ResponseEntity.ok("Order with id: \"" + deletedOrder + "\"deleted from database");
    }
}
