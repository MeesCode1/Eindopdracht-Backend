package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUpCustomer() {
        customer = new Customer();
    }

    @Test
    void shouldSaveAndReturnCorrectAddress() {

        customer.setAddress("Amsterdamsestraatweg 400");

        String resultAdress = customer.getAddress();

        assertEquals("Amsterdamsestraatweg 400", resultAdress);
    }

    @Test
    void shouldSaveAndReturnCorrectFirstName() {

        customer.setFirstName("Jacob");

        String resultFirstName = customer.getFirstName();

        assertEquals("Jacob", resultFirstName);
    }

    @Test
    void shouldSaveAndReturnCorrectLastName() {
        customer.setLastName("Janssen");
        String resultLastName = customer.getLastName();
        assertEquals("Janssen", resultLastName);
    }

    @Test
    void shouldSaveAndReturnCorrectCompany() {
        customer.setCompany("Gebr. v Code");
        String resultCompany = customer.getCompany();
        assertEquals("Gebr. v Code", resultCompany);
    }

    @Test
    void shouldSaveAndReturnCorrectDob() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        customer.setDob(dob);
        LocalDate resultDob = customer.getDob();
        assertEquals(dob, resultDob);
    }

    @Test
    void shouldSaveAndReturnCorrectEmailAddress() {
        customer.setEmailAddress("jacob.Janssen@example.com");
        String resultEmailAddress = customer.getEmailAddress();
        assertEquals("jacob.Janssen@example.com", resultEmailAddress);
    }

    @Test
    void shouldSaveAndReturnCorrectPhoneNumber() {
        customer.setPhoneNumber("123-456-7890");
        String resultPhoneNumber = customer.getPhoneNumber();
        assertEquals("123-456-7890", resultPhoneNumber);
    }

    @Test
    void shouldSaveAndReturnCorrectBankAccount() {
        customer.setBankAccount("NL12ABCD34567890");
        String resultBankAccount = customer.getBankAccount();
        assertEquals("NL12ABCD34567890", resultBankAccount);
    }


    @Test
    void shouldSaveAndReturnCorrectOrders() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        Order order2 = new Order();
        orders.add(order1);
        orders.add(order2);

        customer.setOrders(orders);

        List<Order> resultOrders = customer.getOrders();

        assertEquals(orders, resultOrders);
    }
}