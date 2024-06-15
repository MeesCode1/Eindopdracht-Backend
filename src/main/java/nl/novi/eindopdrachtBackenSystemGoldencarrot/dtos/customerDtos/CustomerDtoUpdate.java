package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CustomerDtoUpdate {


    public String firstName;

    public String lastName;

    public String company;

    public String address;
    @Past
    public LocalDate dob;
    @Email
    public String emailAddress;

    public String phoneNumber;

    @Size(min = 7, max = 20)
    public String bankAccount;
}
