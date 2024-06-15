package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class UserEmployeeDtoDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link UserEmployeeDto#setAddress(String)}
     *   <li>{@link UserEmployeeDto#setBankAccount(String)}
     *   <li>{@link UserEmployeeDto#setDob(LocalDate)}
     *   <li>{@link UserEmployeeDto#setEmailAddress(String)}
     *   <li>{@link UserEmployeeDto#setEmployeeNumber(Long)}
     *   <li>{@link UserEmployeeDto#setFirstName(String)}
     *   <li>{@link UserEmployeeDto#setFunction(String)}
     *   <li>{@link UserEmployeeDto#setLastName(String)}
     *   <li>{@link UserEmployeeDto#setPassword(String)}
     *   <li>{@link UserEmployeeDto#setPhoneNumb(String)}
     *   <li>{@link UserEmployeeDto#setRoles(List)}
     *   <li>{@link UserEmployeeDto#setUsername(String)}
     *   <li>{@link UserEmployeeDto#getAddress()}
     *   <li>{@link UserEmployeeDto#getBankAccount()}
     *   <li>{@link UserEmployeeDto#getDob()}
     *   <li>{@link UserEmployeeDto#getEmailAddress()}
     *   <li>{@link UserEmployeeDto#getEmployeeNumber()}
     *   <li>{@link UserEmployeeDto#getFirstName()}
     *   <li>{@link UserEmployeeDto#getFunction()}
     *   <li>{@link UserEmployeeDto#getLastName()}
     *   <li>{@link UserEmployeeDto#getPassword()}
     *   <li>{@link UserEmployeeDto#getPhoneNumb()}
     *   <li>{@link UserEmployeeDto#getRoles()}
     *   <li>{@link UserEmployeeDto#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        UserEmployeeDto userEmployeeDto = new UserEmployeeDto();

        // Act
        userEmployeeDto.setAddress("42 Main St");
        userEmployeeDto.setBankAccount("3");
        LocalDate dob = LocalDate.of(1970, 1, 1);
        userEmployeeDto.setDob(dob);
        userEmployeeDto.setEmailAddress("42 Main St");
        userEmployeeDto.setEmployeeNumber(1L);
        userEmployeeDto.setFirstName("Jane");
        userEmployeeDto.setFunction("Function");
        userEmployeeDto.setLastName("Doe");
        userEmployeeDto.setPassword("iloveyou");
        userEmployeeDto.setPhoneNumb("6625550144");
        ArrayList<String> roles = new ArrayList<>();
        userEmployeeDto.setRoles(roles);
        userEmployeeDto.setUsername("janedoe");
        String actualAddress = userEmployeeDto.getAddress();
        String actualBankAccount = userEmployeeDto.getBankAccount();
        LocalDate actualDob = userEmployeeDto.getDob();
        String actualEmailAddress = userEmployeeDto.getEmailAddress();
        Long actualEmployeeNumber = userEmployeeDto.getEmployeeNumber();
        String actualFirstName = userEmployeeDto.getFirstName();
        String actualFunction = userEmployeeDto.getFunction();
        String actualLastName = userEmployeeDto.getLastName();
        String actualPassword = userEmployeeDto.getPassword();
        String actualPhoneNumb = userEmployeeDto.getPhoneNumb();
        List<String> actualRoles = userEmployeeDto.getRoles();

        // Assert that nothing has changed
        assertEquals("3", actualBankAccount);
        assertEquals("42 Main St", actualAddress);
        assertEquals("42 Main St", actualEmailAddress);
        assertEquals("6625550144", actualPhoneNumb);
        assertEquals("Doe", actualLastName);
        assertEquals("Function", actualFunction);
        assertEquals("Jane", actualFirstName);
        assertEquals("iloveyou", actualPassword);
        assertEquals("janedoe", userEmployeeDto.getUsername());
        assertEquals(1L, actualEmployeeNumber.longValue());
        assertSame(roles, actualRoles);
        assertSame(dob, actualDob);
    }
}
