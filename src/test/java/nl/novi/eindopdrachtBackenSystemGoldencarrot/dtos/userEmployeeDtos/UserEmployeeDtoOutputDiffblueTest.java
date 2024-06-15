package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class UserEmployeeDtoOutputDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link UserEmployeeDtoOutput#setAddress(String)}
     *   <li>{@link UserEmployeeDtoOutput#setBankAccount(String)}
     *   <li>{@link UserEmployeeDtoOutput#setDob(LocalDate)}
     *   <li>{@link UserEmployeeDtoOutput#setEmailAddress(String)}
     *   <li>{@link UserEmployeeDtoOutput#setEmployeeNumber(Long)}
     *   <li>{@link UserEmployeeDtoOutput#setFirstName(String)}
     *   <li>{@link UserEmployeeDtoOutput#setFunction(String)}
     *   <li>{@link UserEmployeeDtoOutput#setLastName(String)}
     *   <li>{@link UserEmployeeDtoOutput#setPhoneNumb(String)}
     *   <li>{@link UserEmployeeDtoOutput#setRoles(List)}
     *   <li>{@link UserEmployeeDtoOutput#setUsername(String)}
     *   <li>{@link UserEmployeeDtoOutput#getAddress()}
     *   <li>{@link UserEmployeeDtoOutput#getBankAccount()}
     *   <li>{@link UserEmployeeDtoOutput#getDob()}
     *   <li>{@link UserEmployeeDtoOutput#getEmailAddress()}
     *   <li>{@link UserEmployeeDtoOutput#getEmployeeNumber()}
     *   <li>{@link UserEmployeeDtoOutput#getFirstName()}
     *   <li>{@link UserEmployeeDtoOutput#getFunction()}
     *   <li>{@link UserEmployeeDtoOutput#getLastName()}
     *   <li>{@link UserEmployeeDtoOutput#getPhoneNumb()}
     *   <li>{@link UserEmployeeDtoOutput#getRoles()}
     *   <li>{@link UserEmployeeDtoOutput#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        UserEmployeeDtoOutput userEmployeeDtoOutput = new UserEmployeeDtoOutput();

        // Act
        userEmployeeDtoOutput.setAddress("42 Main St");
        userEmployeeDtoOutput.setBankAccount("3");
        LocalDate dob = LocalDate.of(1970, 1, 1);
        userEmployeeDtoOutput.setDob(dob);
        userEmployeeDtoOutput.setEmailAddress("42 Main St");
        userEmployeeDtoOutput.setEmployeeNumber(1L);
        userEmployeeDtoOutput.setFirstName("Jane");
        userEmployeeDtoOutput.setFunction("Function");
        userEmployeeDtoOutput.setLastName("Doe");
        userEmployeeDtoOutput.setPhoneNumb("6625550144");
        ArrayList<String> roles = new ArrayList<>();
        userEmployeeDtoOutput.setRoles(roles);
        userEmployeeDtoOutput.setUsername("janedoe");
        String actualAddress = userEmployeeDtoOutput.getAddress();
        String actualBankAccount = userEmployeeDtoOutput.getBankAccount();
        LocalDate actualDob = userEmployeeDtoOutput.getDob();
        String actualEmailAddress = userEmployeeDtoOutput.getEmailAddress();
        Long actualEmployeeNumber = userEmployeeDtoOutput.getEmployeeNumber();
        String actualFirstName = userEmployeeDtoOutput.getFirstName();
        String actualFunction = userEmployeeDtoOutput.getFunction();
        String actualLastName = userEmployeeDtoOutput.getLastName();
        String actualPhoneNumb = userEmployeeDtoOutput.getPhoneNumb();
        List<String> actualRoles = userEmployeeDtoOutput.getRoles();

        // Assert that nothing has changed
        assertEquals("3", actualBankAccount);
        assertEquals("42 Main St", actualAddress);
        assertEquals("42 Main St", actualEmailAddress);
        assertEquals("6625550144", actualPhoneNumb);
        assertEquals("Doe", actualLastName);
        assertEquals("Function", actualFunction);
        assertEquals("Jane", actualFirstName);
        assertEquals("janedoe", userEmployeeDtoOutput.getUsername());
        assertEquals(1L, actualEmployeeNumber.longValue());
        assertSame(roles, actualRoles);
        assertSame(dob, actualDob);
    }
}
