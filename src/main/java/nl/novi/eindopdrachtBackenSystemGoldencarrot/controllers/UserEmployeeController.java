package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.BindingValidator;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.UserEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users_employees")
public class UserEmployeeController {

    private final UserEmployeeService service;

    public UserEmployeeController(UserEmployeeService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Object> createUserEmployee(@Valid
                                                     @RequestBody UserEmployeeDto userdto,
                                                     BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        userdto = service.createUser(userdto);
        userdto.password = "secret info";

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + userdto.employeeNumber).toUriString());

        return ResponseEntity.created(uri).body(userdto);
    }

    @GetMapping
    public List<UserEmployeeDto> getUsers() {
        return service.getAllUserEmployees();
    }

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<UserEmployeeDto> getCustomerByUsername(@PathVariable Long employeeNumber) {

        UserEmployeeDto udto = service.getUserEmployee(employeeNumber);

        return ResponseEntity.ok(udto);
    }

    @PutMapping("/{employeeNumber}")
    public ResponseEntity<UserEmployeeDto> updateUserEmployee(@PathVariable Long employeeNumber,
                                                              @RequestBody UserEmployeeDto udto) {

        udto = service.updateUserEmployee(employeeNumber, udto);
        return ResponseEntity.ok(udto);
    }
}


