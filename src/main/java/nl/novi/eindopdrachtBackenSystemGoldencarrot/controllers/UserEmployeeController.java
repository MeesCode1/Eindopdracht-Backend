package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoOutput;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.UserEmployeeService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.BindingValidator;
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
    public ResponseEntity<Object> createUserEmployee(@Valid @RequestBody UserEmployeeDto userdto,
                                                     BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }

        UserEmployeeDtoOutput udtoToReturn = service.createUser(userdto);

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + udtoToReturn.employeeNumber).toUriString());

        return ResponseEntity.created(uri).body(udtoToReturn);
    }

    @GetMapping
    public List<UserEmployeeDtoOutput> getUsers() {
        return service.getAllUserEmployees();
    }

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<UserEmployeeDtoOutput> getCustomerByUsername(@PathVariable Long employeeNumber) {

        UserEmployeeDtoOutput udto = service.getUserEmployee(employeeNumber);

        return ResponseEntity.ok(udto);
    }

    @PutMapping("/{employeeNumber}")
    public ResponseEntity<Object> updateUserEmployee(@PathVariable Long employeeNumber,
                                                                    @Valid @RequestBody UserEmployeeDtoUpdate udto,
                                                                    BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        UserEmployeeDtoOutput updatedUdto = service.updateUserEmployee(employeeNumber, udto);
        return ResponseEntity.ok(updatedUdto);
    }

    @DeleteMapping("/{employeeNumber}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long employeeNumber) {
        String deletedUser = service.deleteUserEmployee(employeeNumber);
        return ResponseEntity.ok("UserEmployee: \"" + deletedUser + "\" deleted from database");
    }
}


