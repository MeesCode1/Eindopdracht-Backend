package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MyUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class MyUserDetailsServiceDiffblueTest {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private UserEmployeeRepository userEmployeeRepository;

    /**
     * Method under test: {@link MyUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        when(userEmployeeRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        UserDetails actualLoadUserByUsernameResult = myUserDetailsService.loadUserByUsername("janedoe");

        // Assert
        verify(userEmployeeRepository).findByUsername(eq("janedoe"));
        assertEquals(roles, actualLoadUserByUsernameResult.getAuthorities());
    }

    /**
     * Method under test: {@link MyUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        // Arrange
        Optional<UserEmployee> emptyResult = Optional.empty();
        when(userEmployeeRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(userEmployeeRepository).findByUsername(eq("janedoe"));
    }

    /**
     * Method under test: {@link MyUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        // Arrange
        when(userEmployeeRepository.findByUsername(Mockito.<String>any())).thenThrow(new UsernameNotFoundException("Msg"));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(userEmployeeRepository).findByUsername(eq("janedoe"));
    }

    /**
     * Method under test:
     * {@link MyUserDetailsService#MyUserDetailsService(UserEmployeeRepository)}
     */
    @Test
    void testNewMyUserDetailsService() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     MyUserDetailsService.userRepos

        // Arrange and Act
        new MyUserDetailsService(mock(UserEmployeeRepository.class));
    }
}
