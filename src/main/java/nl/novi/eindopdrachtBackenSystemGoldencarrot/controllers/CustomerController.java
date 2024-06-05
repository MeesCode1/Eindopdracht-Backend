package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.BindingValidator;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.CustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerDto> showCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{company}")
    public ResponseEntity<CustomerDto> getCustomerByCompany(@PathVariable String company) {
        CustomerDto cdto = service.getCustomerByCompany(company);
        return ResponseEntity.ok(cdto);
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDto cdto,
                                                 BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        cdto = service.createCustomer(cdto);

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + cdto.id).toUriString());

        return ResponseEntity.created(uri).body(cdto);
    }

    @PutMapping("/{company}")
    public ResponseEntity<Object> updateCustomer(@PathVariable String company,
                                                 @Valid @RequestBody CustomerDtoUpdate cdto,
                                                 BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        CustomerDto updatedCustomerDto = service.updateCustomer(company, cdto);
        return ResponseEntity.ok(cdto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String deletedCustomer = service.deleteCustomer(id);
        return ResponseEntity.ok("Customer: \"" + deletedCustomer + "\" deleted from database");
    }

}
