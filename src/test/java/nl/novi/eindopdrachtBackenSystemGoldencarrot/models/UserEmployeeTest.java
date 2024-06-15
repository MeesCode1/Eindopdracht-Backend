package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEmployeeTest {

    private UserEmployee userEmployee;

    @BeforeEach
    void setUpUserEmployee() {
        userEmployee = new UserEmployee();
    }

    @Test
    void shouldSaveAndReturnCorrectAddress() {
        String address = "123 Main Street";
        userEmployee.setAddress(address);
        assertEquals(address, userEmployee.getAddress());
    }

    @Test
    void shouldSaveAndReturnCorrectEmployeeNumber() {
        Long employeeNumber = 12345L;
        userEmployee.setEmployeeNumber(employeeNumber);
        assertEquals(employeeNumber, userEmployee.getEmployeeNumber());
    }

    @Test
    void shouldSaveAndReturnCorrectUsername() {
        String username = "john.doe";
        userEmployee.setUsername(username);
        assertEquals(username, userEmployee.getUsername());
    }

    @Test
    void shouldSaveAndReturnCorrectPassword() {
        String password = "secret123";
        userEmployee.setPassword(password);
        assertEquals(password, userEmployee.getPassword());
    }

    @Test
    void shouldSaveAndReturnCorrectFirstName() {
        String firstName = "John";
        userEmployee.setFirstName(firstName);
        assertEquals(firstName, userEmployee.getFirstName());
    }

    @Test
    void shouldSaveAndReturnCorrectLastName() {
        String lastName = "Doe";
        userEmployee.setLastName(lastName);
        assertEquals(lastName, userEmployee.getLastName());
    }

    @Test
    void shouldSaveAndReturnCorrectDob() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        userEmployee.setDob(dob);
        assertEquals(dob, userEmployee.getDob());
    }

    @Test
    void shouldCalculateCorrectAge() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        userEmployee.setDob(dob);
        assertEquals(34, userEmployee.getAge());
    }

    @Test
    void shouldSaveAndReturnCorrectPhoneNumb() {
        String phoneNumb = "0688774455";
        userEmployee.setPhoneNumb(phoneNumb);
        assertEquals(phoneNumb, userEmployee.getPhoneNumb());
    }

    @Test
    void shouldSaveAndReturnCorrectEmailAddress() {
        String emailAddress = "john.doe@example.com";
        userEmployee.setEmailAddress(emailAddress);
        assertEquals(emailAddress, userEmployee.getEmailAddress());
    }

    @Test
    void shouldSaveAndReturnCorrectBankAccount() {
        String bankAccount = "NL12ABCD34567890";
        userEmployee.setBankAccount(bankAccount);
        assertEquals(bankAccount, userEmployee.getBankAccount());
    }

    @Test
    void shouldSaveAndReturnCorrectFunction() {
        String function = "Software Developer";
        userEmployee.setFunction(function);
        assertEquals(function, userEmployee.getFunction());
    }

    @Test
    void shouldSaveAndReturnCorrectRoles() {
        Collection<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        Role role2 = new Role();
        roles.add(role1);
        roles.add(role2);

        userEmployee.setRoles(roles);

        Collection<Role> resultRoles = userEmployee.getRoles();

        assertEquals(roles, resultRoles);
    }
}