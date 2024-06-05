package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserEmployeeDtoUpdate {

    public String username;

    public String firstName;

    public String lastName;
    @Past
    public LocalDate dob;

    public String address;

    public String phoneNumb;
    @Email
    public String emailAddress;

    @Size(min = 7, max = 20)
    public String bankAccount;

    public String function;

    public List<String> roles = new ArrayList<>();
}
