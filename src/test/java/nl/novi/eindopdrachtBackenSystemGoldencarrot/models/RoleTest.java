package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    private Role role;

    @BeforeEach
    void setUpRole() {
        role = new Role();
    }

//    @Test
//    void getId() {
//    }
//    @Test
//    void shouldSaveAndReturnCorrectId() {
//        Long idResult = role.getId();
//        assertEquals(1, idResult);
//    }

    @Test
    void shouldSaveAndReturnCorrectRoleName() {
        String roleName = "Admin";
        role.setRoleName(roleName);
        assertEquals(roleName, role.getRoleName());
    }

    @Test
    void shouldSaveAndReturnCorrectUsersEmployees() {
        Collection<UserEmployee> usersEmployees = new ArrayList<>();
        UserEmployee user1 = new UserEmployee();
        UserEmployee user2 = new UserEmployee();
        usersEmployees.add(user1);
        usersEmployees.add(user2);

        role.setUsersEmployees(usersEmployees);

        Collection<UserEmployee> resultUsersEmployees = role.getUsersEmployees();

        assertEquals(usersEmployees, resultUsersEmployees);
    }
}
