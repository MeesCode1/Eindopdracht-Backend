package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUserDetailsDiffblueTest {
    /**
     * Method under test: {@link MyUserDetails#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        // Arrange
        UserEmployee user = new UserEmployee();
        user.setRoles(new ArrayList<>());

        // Act and Assert
        assertTrue((new MyUserDetails(user)).getAuthorities().isEmpty());
    }

    /**
     * Method under test: {@link MyUserDetails#getAuthorities()}
     */
    @Test
    void testGetAuthorities2() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Role Name");
        role.setUsersEmployees(new ArrayList<>());

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        UserEmployee user = new UserEmployee();
        user.setRoles(roles);

        // Act
        Collection<? extends GrantedAuthority> actualAuthorities = (new MyUserDetails(user)).getAuthorities();

        // Assert
        assertEquals(1, actualAuthorities.size());
        assertEquals("Role Name", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    /**
     * Method under test: {@link MyUserDetails#getPassword()}
     */
    @Test
    void testGetPassword() {
        // Arrange, Act and Assert
        assertNull((new MyUserDetails(new UserEmployee())).getPassword());
    }

    /**
     * Method under test: {@link MyUserDetails#getUsername()}
     */
    @Test
    void testGetUsername() {
        // Arrange, Act and Assert
        assertNull((new MyUserDetails(new UserEmployee())).getUsername());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link MyUserDetails#MyUserDetails(UserEmployee)}
     *   <li>{@link MyUserDetails#isAccountNonExpired()}
     *   <li>{@link MyUserDetails#isAccountNonLocked()}
     *   <li>{@link MyUserDetails#isCredentialsNonExpired()}
     *   <li>{@link MyUserDetails#isEnabled()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        UserEmployee user = new UserEmployee();
        user.setAddress("42 Main St");
        user.setBankAccount("3");
        user.setDob(LocalDate.of(1970, 1, 1));
        user.setEmailAddress("42 Main St");
        user.setEmployeeNumber(1L);
        user.setFirstName("Jane");
        user.setFunction("Function");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumb("6625550144");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        // Act
        MyUserDetails actualMyUserDetails = new MyUserDetails(user);
        boolean actualIsAccountNonExpiredResult = actualMyUserDetails.isAccountNonExpired();
        boolean actualIsAccountNonLockedResult = actualMyUserDetails.isAccountNonLocked();
        boolean actualIsCredentialsNonExpiredResult = actualMyUserDetails.isCredentialsNonExpired();

        // Assert
        assertTrue(actualIsAccountNonExpiredResult);
        assertTrue(actualIsAccountNonLockedResult);
        assertTrue(actualIsCredentialsNonExpiredResult);
        assertTrue(actualMyUserDetails.isEnabled());
    }
}
