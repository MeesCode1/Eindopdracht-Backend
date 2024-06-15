package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoOutput;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.IllegalArgumentException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.RoleRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserEmployeeService.class})
@ExtendWith(SpringExtension.class)
class UserEmployeeServiceDiffblueTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserEmployeeRepository userEmployeeRepository;

    @Autowired
    private UserEmployeeService userEmployeeService;

    /**
     * Method under test: {@link UserEmployeeService#createUser(UserEmployeeDto)}
     */
    @Test
    void testCreateUser() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        when(userEmployeeRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        UserEmployeeDto udto = new UserEmployeeDto();
        udto.setAddress("42 Main St");
        udto.setBankAccount("3");
        udto.setDob(LocalDate.of(1970, 1, 1));
        udto.setEmailAddress("42 Main St");
        udto.setEmployeeNumber(1L);
        udto.setFirstName("Jane");
        udto.setFunction("Function");
        udto.setLastName("Doe");
        udto.setPassword("iloveyou");
        udto.setPhoneNumb("6625550144");
        udto.setRoles(new ArrayList<>());
        udto.setUsername("janedoe");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.createUser(udto));
        verify(userEmployeeRepository).findByUsername(eq("janedoe"));
    }

    /**
     * Method under test: {@link UserEmployeeService#createUser(UserEmployeeDto)}
     */
    @Test
    void testCreateUser2() {
        // Arrange
        Optional<UserEmployee> emptyResult = Optional.empty();
        when(userEmployeeRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);

        UserEmployeeDto udto = new UserEmployeeDto();
        udto.setAddress("42 Main St");
        udto.setBankAccount("3");
        udto.setDob(LocalDate.of(1970, 1, 1));
        udto.setEmailAddress("42 Main St");
        udto.setEmployeeNumber(1L);
        udto.setFirstName("Jane");
        udto.setFunction("Function");
        udto.setLastName("Doe");
        udto.setPassword("iloveyou");
        udto.setPhoneNumb("6625550144");
        udto.setRoles(new ArrayList<>());
        udto.setUsername("janedoe");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.createUser(udto));
        verify(userEmployeeRepository).findByUsername(eq("janedoe"));
    }

    /**
     * Method under test: {@link UserEmployeeService#createUser(UserEmployeeDto)}
     */
    @Test
    void testCreateUser3() {
        // Arrange
        when(userEmployeeRepository.findByUsername(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("An error occurred"));

        UserEmployeeDto udto = new UserEmployeeDto();
        udto.setAddress("42 Main St");
        udto.setBankAccount("3");
        udto.setDob(LocalDate.of(1970, 1, 1));
        udto.setEmailAddress("42 Main St");
        udto.setEmployeeNumber(1L);
        udto.setFirstName("Jane");
        udto.setFunction("Function");
        udto.setLastName("Doe");
        udto.setPassword("iloveyou");
        udto.setPhoneNumb("6625550144");
        udto.setRoles(new ArrayList<>());
        udto.setUsername("janedoe");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.createUser(udto));
        verify(userEmployeeRepository).findByUsername(eq("janedoe"));
    }

    /**
     * Method under test: {@link UserEmployeeService#getUserEmployee(Long)}
     */
    @Test
    void testGetUserEmployee() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
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
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        UserEmployeeDtoOutput actualUserEmployee = userEmployeeService.getUserEmployee(1L);

        // Assert
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
        assertEquals("1970-01-01", actualUserEmployee.getDob().toString());
        assertEquals("3", actualUserEmployee.getBankAccount());
        assertEquals("42 Main St", actualUserEmployee.getAddress());
        assertEquals("42 Main St", actualUserEmployee.getEmailAddress());
        assertEquals("6625550144", actualUserEmployee.getPhoneNumb());
        assertEquals("Doe", actualUserEmployee.getLastName());
        assertEquals("Function", actualUserEmployee.getFunction());
        assertEquals("Jane", actualUserEmployee.getFirstName());
        assertEquals("janedoe", actualUserEmployee.getUsername());
        assertEquals(1L, actualUserEmployee.getEmployeeNumber().longValue());
        assertEquals(roles, actualUserEmployee.getRoles());
    }

    /**
     * Method under test: {@link UserEmployeeService#getUserEmployee(Long)}
     */
    @Test
    void testGetUserEmployee2() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Role Name");
        role.setUsersEmployees(new ArrayList<>());

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(roles);
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        UserEmployeeDtoOutput actualUserEmployee = userEmployeeService.getUserEmployee(1L);

        // Assert
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
        assertEquals("1970-01-01", actualUserEmployee.getDob().toString());
        assertEquals("3", actualUserEmployee.getBankAccount());
        assertEquals("42 Main St", actualUserEmployee.getAddress());
        assertEquals("42 Main St", actualUserEmployee.getEmailAddress());
        assertEquals("6625550144", actualUserEmployee.getPhoneNumb());
        assertEquals("Doe", actualUserEmployee.getLastName());
        assertEquals("Function", actualUserEmployee.getFunction());
        assertEquals("Jane", actualUserEmployee.getFirstName());
        List<String> roles2 = actualUserEmployee.getRoles();
        assertEquals(1, roles2.size());
        assertEquals("Role Name", roles2.get(0));
        assertEquals("janedoe", actualUserEmployee.getUsername());
        assertEquals(1L, actualUserEmployee.getEmployeeNumber().longValue());
    }

    /**
     * Method under test: {@link UserEmployeeService#getUserEmployee(Long)}
     */
    @Test
    void testGetUserEmployee3() {
        // Arrange
        Optional<UserEmployee> emptyResult = Optional.empty();
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userEmployeeService.getUserEmployee(1L));
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
    }

    /**
     * Method under test: {@link UserEmployeeService#getUserEmployee(Long)}
     */
    @Test
    void testGetUserEmployee4() {
        // Arrange
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any()))
                .thenThrow(new IllegalArgumentException("An error occurred"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.getUserEmployee(1L));
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
    }

    /**
     * Method under test: {@link UserEmployeeService#getAllUserEmployees()}
     */
    @Test
    void testGetAllUserEmployees() {
        // Arrange
        when(userEmployeeRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<UserEmployeeDtoOutput> actualAllUserEmployees = userEmployeeService.getAllUserEmployees();

        // Assert
        verify(userEmployeeRepository).findAll();
        assertTrue(actualAllUserEmployees.isEmpty());
    }

    /**
     * Method under test: {@link UserEmployeeService#getAllUserEmployees()}
     */
    @Test
    void testGetAllUserEmployees2() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");

        ArrayList<UserEmployee> userEmployeeList = new ArrayList<>();
        userEmployeeList.add(userEmployee);
        when(userEmployeeRepository.findAll()).thenReturn(userEmployeeList);

        // Act
        List<UserEmployeeDtoOutput> actualAllUserEmployees = userEmployeeService.getAllUserEmployees();

        // Assert
        verify(userEmployeeRepository).findAll();
        assertEquals(1, actualAllUserEmployees.size());
    }

    /**
     * Method under test: {@link UserEmployeeService#getAllUserEmployees()}
     */
    @Test
    void testGetAllUserEmployees3() {
        // Arrange
        when(userEmployeeRepository.findAll()).thenThrow(new IllegalArgumentException("An error occurred"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.getAllUserEmployees());
        verify(userEmployeeRepository).findAll();
    }

    /**
     * Method under test: {@link UserEmployeeService#getAllUserEmployees()}
     */
    @Test
    void testGetAllUserEmployees4() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Role Name");
        role.setUsersEmployees(new ArrayList<>());

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(roles);
        userEmployee.setUsername("janedoe");

        ArrayList<UserEmployee> userEmployeeList = new ArrayList<>();
        userEmployeeList.add(userEmployee);
        when(userEmployeeRepository.findAll()).thenReturn(userEmployeeList);

        // Act
        List<UserEmployeeDtoOutput> actualAllUserEmployees = userEmployeeService.getAllUserEmployees();

        // Assert
        verify(userEmployeeRepository).findAll();
        assertEquals(1, actualAllUserEmployees.size());
    }

    /**
     * Method under test:
     * {@link UserEmployeeService#updateUserEmployee(Long, UserEmployeeDtoUpdate)}
     */
    @Test
    void testUpdateUserEmployee() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(ofResult);
        UserEmployeeDtoUpdate udto = new UserEmployeeDtoUpdate();
        udto.address = "42 Main St";
        udto.bankAccount = "3";
        udto.dob = LocalDate.of(1970, 1, 1);
        udto.emailAddress = "42 Main St";
        udto.firstName = "Jane";
        udto.function = "Function";
        udto.lastName = "Doe";
        udto.phoneNumb = "6625550144";
        udto.roles = new ArrayList<>();
        udto.username = "janedoe";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.updateUserEmployee(1L, udto));
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
    }

    /**
     * Method under test:
     * {@link UserEmployeeService#updateUserEmployee(Long, UserEmployeeDtoUpdate)}
     */
    @Test
    void testUpdateUserEmployee2() {
        // Arrange
        Optional<UserEmployee> emptyResult = Optional.empty();
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(emptyResult);
        UserEmployeeDtoUpdate udto = new UserEmployeeDtoUpdate();
        udto.address = "42 Main St";
        udto.bankAccount = "3";
        udto.dob = LocalDate.of(1970, 1, 1);
        udto.emailAddress = "42 Main St";
        udto.firstName = "Jane";
        udto.function = "Function";
        udto.lastName = "Doe";
        udto.phoneNumb = "6625550144";
        udto.roles = new ArrayList<>();
        udto.username = "janedoe";

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userEmployeeService.updateUserEmployee(1L, udto));
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
    }

    /**
     * Method under test:
     * {@link UserEmployeeService#updateUserEmployee(Long, UserEmployeeDtoUpdate)}
     */
    @Test
    void testUpdateUserEmployee3() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(ofResult);
        UserEmployeeDtoUpdate udto = new UserEmployeeDtoUpdate();
        udto.address = "42 Main St";
        udto.bankAccount = "3";
        udto.dob = LocalDate.of(1970, 1, 1);
        udto.emailAddress = "42 Main St";
        udto.firstName = null;
        udto.function = "Function";
        udto.lastName = "Doe";
        udto.phoneNumb = "6625550144";
        udto.roles = new ArrayList<>();
        udto.username = "janedoe";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.updateUserEmployee(1L, udto));
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
    }

    /**
     * Method under test:
     * {@link UserEmployeeService#updateUserEmployee(Long, UserEmployeeDtoUpdate)}
     */
    @Test
    void testUpdateUserEmployee4() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);

        UserEmployee userEmployee2 = new UserEmployee();
        userEmployee2.setAddress("42 Main St");
        userEmployee2.setBankAccount("3");
        userEmployee2.setDob(LocalDate.of(1970, 1, 1));
        userEmployee2.setEmailAddress("42 Main St");
        userEmployee2.setEmployeeNumber(1L);
        userEmployee2.setFirstName("Jane");
        userEmployee2.setFunction("Function");
        userEmployee2.setLastName("Doe");
        userEmployee2.setPassword("iloveyou");
        userEmployee2.setPhoneNumb("6625550144");
        ArrayList<Role> roles = new ArrayList<>();
        userEmployee2.setRoles(roles);
        userEmployee2.setUsername("janedoe");
        when(userEmployeeRepository.save(Mockito.<UserEmployee>any())).thenReturn(userEmployee2);
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(ofResult);
        UserEmployeeDtoUpdate udto = new UserEmployeeDtoUpdate();
        udto.address = "42 Main St";
        udto.bankAccount = "3";
        udto.dob = LocalDate.of(1970, 1, 1);
        udto.emailAddress = "42 Main St";
        udto.firstName = "Jane";
        udto.function = "Function";
        udto.lastName = "Doe";
        udto.phoneNumb = "6625550144";
        udto.roles = new ArrayList<>();
        udto.username = "JaneDoe";

        // Act
        UserEmployeeDtoOutput actualUpdateUserEmployeeResult = userEmployeeService.updateUserEmployee(1L, udto);

        // Assert
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
        verify(userEmployeeRepository).save(isA(UserEmployee.class));
        assertEquals("1970-01-01", actualUpdateUserEmployeeResult.getDob().toString());
        assertEquals("3", actualUpdateUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualUpdateUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualUpdateUserEmployeeResult.getLastName());
        assertEquals("Function", actualUpdateUserEmployeeResult.getFunction());
        assertEquals("Jane", actualUpdateUserEmployeeResult.getFirstName());
        assertEquals("janedoe", actualUpdateUserEmployeeResult.getUsername());
        assertEquals(1L, actualUpdateUserEmployeeResult.getEmployeeNumber().longValue());
        assertEquals(roles, actualUpdateUserEmployeeResult.getRoles());
    }

    /**
     * Method under test:
     * {@link UserEmployeeService#updateUserEmployee(Long, UserEmployeeDtoUpdate)}
     */
    @Test
    void testUpdateUserEmployee5() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("JaneDoe");
        role.setUsersEmployees(new ArrayList<>());

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        UserEmployee userEmployee2 = new UserEmployee();
        userEmployee2.setAddress("42 Main St");
        userEmployee2.setBankAccount("3");
        userEmployee2.setDob(LocalDate.of(1970, 1, 1));
        userEmployee2.setEmailAddress("42 Main St");
        userEmployee2.setEmployeeNumber(1L);
        userEmployee2.setFirstName("Jane");
        userEmployee2.setFunction("Function");
        userEmployee2.setLastName("Doe");
        userEmployee2.setPassword("iloveyou");
        userEmployee2.setPhoneNumb("6625550144");
        userEmployee2.setRoles(roles);
        userEmployee2.setUsername("janedoe");
        when(userEmployeeRepository.save(Mockito.<UserEmployee>any())).thenReturn(userEmployee2);
        when(userEmployeeRepository.findByEmployeeNumber(Mockito.<Long>any())).thenReturn(ofResult);
        UserEmployeeDtoUpdate udto = new UserEmployeeDtoUpdate();
        udto.address = "42 Main St";
        udto.bankAccount = "3";
        udto.dob = LocalDate.of(1970, 1, 1);
        udto.emailAddress = "42 Main St";
        udto.firstName = "Jane";
        udto.function = "Function";
        udto.lastName = "Doe";
        udto.phoneNumb = "6625550144";
        udto.roles = new ArrayList<>();
        udto.username = "JaneDoe";

        // Act
        UserEmployeeDtoOutput actualUpdateUserEmployeeResult = userEmployeeService.updateUserEmployee(1L, udto);

        // Assert
        verify(userEmployeeRepository).findByEmployeeNumber(eq(1L));
        verify(userEmployeeRepository).save(isA(UserEmployee.class));
        assertEquals("1970-01-01", actualUpdateUserEmployeeResult.getDob().toString());
        assertEquals("3", actualUpdateUserEmployeeResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateUserEmployeeResult.getAddress());
        assertEquals("42 Main St", actualUpdateUserEmployeeResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateUserEmployeeResult.getPhoneNumb());
        assertEquals("Doe", actualUpdateUserEmployeeResult.getLastName());
        assertEquals("Function", actualUpdateUserEmployeeResult.getFunction());
        assertEquals("Jane", actualUpdateUserEmployeeResult.getFirstName());
        List<String> roles2 = actualUpdateUserEmployeeResult.getRoles();
        assertEquals(1, roles2.size());
        assertEquals("JaneDoe", roles2.get(0));
        assertEquals("janedoe", actualUpdateUserEmployeeResult.getUsername());
        assertEquals(1L, actualUpdateUserEmployeeResult.getEmployeeNumber().longValue());
    }

    /**
     * Method under test: {@link UserEmployeeService#deleteUserEmployee(Long)}
     */
    @Test
    void testDeleteUserEmployee() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        doNothing().when(userEmployeeRepository).delete(Mockito.<UserEmployee>any());
        when(userEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualDeleteUserEmployeeResult = userEmployeeService.deleteUserEmployee(1L);

        // Assert
        verify(userEmployeeRepository).delete(isA(UserEmployee.class));
        verify(userEmployeeRepository).findById(eq(1L));
        assertEquals("janedoe", actualDeleteUserEmployeeResult);
    }

    /**
     * Method under test: {@link UserEmployeeService#deleteUserEmployee(Long)}
     */
    @Test
    void testDeleteUserEmployee2() {
        // Arrange
        UserEmployee userEmployee = new UserEmployee();
        userEmployee.setAddress("42 Main St");
        userEmployee.setBankAccount("3");
        userEmployee.setDob(LocalDate.of(1970, 1, 1));
        userEmployee.setEmailAddress("42 Main St");
        userEmployee.setEmployeeNumber(1L);
        userEmployee.setFirstName("Jane");
        userEmployee.setFunction("Function");
        userEmployee.setLastName("Doe");
        userEmployee.setPassword("iloveyou");
        userEmployee.setPhoneNumb("6625550144");
        userEmployee.setRoles(new ArrayList<>());
        userEmployee.setUsername("janedoe");
        Optional<UserEmployee> ofResult = Optional.of(userEmployee);
        doThrow(new IllegalArgumentException("An error occurred")).when(userEmployeeRepository)
                .delete(Mockito.<UserEmployee>any());
        when(userEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userEmployeeService.deleteUserEmployee(1L));
        verify(userEmployeeRepository).delete(isA(UserEmployee.class));
        verify(userEmployeeRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link UserEmployeeService#deleteUserEmployee(Long)}
     */
    @Test
    void testDeleteUserEmployee3() {
        // Arrange
        Optional<UserEmployee> emptyResult = Optional.empty();
        when(userEmployeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userEmployeeService.deleteUserEmployee(1L));
        verify(userEmployeeRepository).findById(eq(1L));
    }
}
