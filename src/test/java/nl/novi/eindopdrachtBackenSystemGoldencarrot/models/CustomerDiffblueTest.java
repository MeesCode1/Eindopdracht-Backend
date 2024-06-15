package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CustomerDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Customer#setAddress(String)}
     *   <li>{@link Customer#setBankAccount(String)}
     *   <li>{@link Customer#setCompany(String)}
     *   <li>{@link Customer#setDob(LocalDate)}
     *   <li>{@link Customer#setEmailAddress(String)}
     *   <li>{@link Customer#setFirstName(String)}
     *   <li>{@link Customer#setId(Long)}
     *   <li>{@link Customer#setLastName(String)}
     *   <li>{@link Customer#setOrders(List)}
     *   <li>{@link Customer#setPhoneNumber(String)}
     *   <li>{@link Customer#getAddress()}
     *   <li>{@link Customer#getBankAccount()}
     *   <li>{@link Customer#getCompany()}
     *   <li>{@link Customer#getDob()}
     *   <li>{@link Customer#getEmailAddress()}
     *   <li>{@link Customer#getFirstName()}
     *   <li>{@link Customer#getId()}
     *   <li>{@link Customer#getLastName()}
     *   <li>{@link Customer#getOrders()}
     *   <li>{@link Customer#getPhoneNumber()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        LocalDate dob = LocalDate.of(1970, 1, 1);
        customer.setDob(dob);
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        ArrayList<Order> orders = new ArrayList<>();
        customer.setOrders(orders);
        customer.setPhoneNumber("6625550144");
        String actualAddress = customer.getAddress();
        String actualBankAccount = customer.getBankAccount();
        String actualCompany = customer.getCompany();
        LocalDate actualDob = customer.getDob();
        String actualEmailAddress = customer.getEmailAddress();
        String actualFirstName = customer.getFirstName();
        Long actualId = customer.getId();
        String actualLastName = customer.getLastName();
        List<Order> actualOrders = customer.getOrders();

        // Assert that nothing has changed
        assertEquals("3", actualBankAccount);
        assertEquals("42 Main St", actualAddress);
        assertEquals("42 Main St", actualEmailAddress);
        assertEquals("6625550144", customer.getPhoneNumber());
        assertEquals("Company", actualCompany);
        assertEquals("Doe", actualLastName);
        assertEquals("Jane", actualFirstName);
        assertEquals(1L, actualId.longValue());
        assertSame(orders, actualOrders);
        assertSame(dob, actualDob);
    }
}
