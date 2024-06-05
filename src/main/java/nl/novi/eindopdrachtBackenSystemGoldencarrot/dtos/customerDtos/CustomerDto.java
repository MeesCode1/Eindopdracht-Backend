package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos;


import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CustomerDto {

    public Long id;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    public String company;
    @NotBlank
    public String address;
    @Past
    public LocalDate dob;
    @Email
    public String emailAddress;
    @NotBlank
    public String phoneNumber;
    @NotBlank
    @Size(min = 7, max = 20)
    public String bankAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}

