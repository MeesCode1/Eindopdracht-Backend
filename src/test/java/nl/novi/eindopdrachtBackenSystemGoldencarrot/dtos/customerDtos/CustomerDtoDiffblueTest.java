package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CustomerDtoDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link CustomerDto#setAddress(String)}
     *   <li>{@link CustomerDto#setBankAccount(String)}
     *   <li>{@link CustomerDto#setCompany(String)}
     *   <li>{@link CustomerDto#setDob(LocalDate)}
     *   <li>{@link CustomerDto#setEmailAddress(String)}
     *   <li>{@link CustomerDto#setFirstName(String)}
     *   <li>{@link CustomerDto#setId(Long)}
     *   <li>{@link CustomerDto#setLastName(String)}
     *   <li>{@link CustomerDto#setPhoneNumber(String)}
     *   <li>{@link CustomerDto#getAddress()}
     *   <li>{@link CustomerDto#getBankAccount()}
     *   <li>{@link CustomerDto#getCompany()}
     *   <li>{@link CustomerDto#getDob()}
     *   <li>{@link CustomerDto#getEmailAddress()}
     *   <li>{@link CustomerDto#getFirstName()}
     *   <li>{@link CustomerDto#getId()}
     *   <li>{@link CustomerDto#getLastName()}
     *   <li>{@link CustomerDto#getPhoneNumber()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        CustomerDto customerDto = new CustomerDto();

        // Act
        customerDto.setAddress("42 Main St");
        customerDto.setBankAccount("3");
        customerDto.setCompany("Company");
        LocalDate dob = LocalDate.of(1970, 1, 1);
        customerDto.setDob(dob);
        customerDto.setEmailAddress("42 Main St");
        customerDto.setFirstName("Jane");
        customerDto.setId(1L);
        customerDto.setLastName("Doe");
        customerDto.setPhoneNumber("6625550144");
        String actualAddress = customerDto.getAddress();
        String actualBankAccount = customerDto.getBankAccount();
        String actualCompany = customerDto.getCompany();
        LocalDate actualDob = customerDto.getDob();
        String actualEmailAddress = customerDto.getEmailAddress();
        String actualFirstName = customerDto.getFirstName();
        Long actualId = customerDto.getId();
        String actualLastName = customerDto.getLastName();

        // Assert that nothing has changed
        assertEquals("3", actualBankAccount);
        assertEquals("42 Main St", actualAddress);
        assertEquals("42 Main St", actualEmailAddress);
        assertEquals("6625550144", customerDto.getPhoneNumber());
        assertEquals("Company", actualCompany);
        assertEquals("Doe", actualLastName);
        assertEquals("Jane", actualFirstName);
        assertEquals(1L, actualId.longValue());
        assertSame(dob, actualDob);
    }
}
