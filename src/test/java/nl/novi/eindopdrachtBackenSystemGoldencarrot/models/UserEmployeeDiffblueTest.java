package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class UserEmployeeDiffblueTest {


    /**
     * Methods under test:
     * <ul>
     *   <li>{@link UserEmployee#setAddress(String)}
     *   <li>{@link UserEmployee#setBankAccount(String)}
     *   <li>{@link UserEmployee#setDob(LocalDate)}
     *   <li>{@link UserEmployee#setEmailAddress(String)}
     *   <li>{@link UserEmployee#setEmployeeNumber(Long)}
     *   <li>{@link UserEmployee#setFirstName(String)}
     *   <li>{@link UserEmployee#setFunction(String)}
     *   <li>{@link UserEmployee#setLastName(String)}
     *   <li>{@link UserEmployee#setPassword(String)}
     *   <li>{@link UserEmployee#setPhoneNumb(String)}
     *   <li>{@link UserEmployee#setRoles(Collection)}
     *   <li>{@link UserEmployee#setUsername(String)}
     *   <li>{@link UserEmployee#getAddress()}
     *   <li>{@link UserEmployee#getBankAccount()}
     *   <li>{@link UserEmployee#getDob()}
     *   <li>{@link UserEmployee#getEmailAddress()}
     *   <li>{@link UserEmployee#getEmployeeNumber()}
     *   <li>{@link UserEmployee#getFirstName()}
     *   <li>{@link UserEmployee#getFunction()}
     *   <li>{@link UserEmployee#getLastName()}
     *   <li>{@link UserEmployee#getPassword()}
     *   <li>{@link UserEmployee#getPhoneNumb()}
     *   <li>{@link UserEmployee#getRoles()}
     *   <li>{@link UserEmployee#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();

        // Act
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        LocalDate dob = LocalDate.of(1970, 1, 1);
        userEmployee.setDob(dob);
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        ArrayList<Role> roles = new ArrayList<>();
        userEmployee.setRoles(roles);
        userEmployee.setUsername("janedoe");
        String actualAddress = userEmployee.getAddress();
        String actualBankAccount = userEmployee.getBankAccount();
        LocalDate actualDob = userEmployee.getDob();
        String actualEmailAddress = userEmployee.getEmailAddress();
        Long actualEmployeeNumber = userEmployee.getEmployeeNumber();
        String actualFirstName = userEmployee.getFirstName();
        String actualFunction = userEmployee.getFunction();
        String actualLastName = userEmployee.getLastName();
        String actualPassword = userEmployee.getPassword();
        String actualPhoneNumb = userEmployee.getPhoneNumb();
        Collection<Role> actualRoles = userEmployee.getRoles();

        // Assert that nothing has changed
        assertEquals("3", actualBankAccount);
        assertEquals("42 Main St", actualAddress);
        assertEquals("42 Main St", actualEmailAddress);
        assertEquals("6625550144", actualPhoneNumb);
        assertEquals("Doe", actualLastName);
        assertEquals("Function", actualFunction);
        assertEquals("Jane", actualFirstName);
        assertEquals("iloveyou", actualPassword);
        assertEquals("janedoe", userEmployee.getUsername());
        assertEquals(1L, actualEmployeeNumber.longValue());
        assertSame(roles, actualRoles);
        assertSame(dob, actualDob);
    }
}
