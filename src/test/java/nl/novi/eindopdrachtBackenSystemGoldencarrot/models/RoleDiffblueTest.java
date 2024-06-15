package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class RoleDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Role#setId(Long)}
     *   <li>{@link Role#setRoleName(String)}
     *   <li>{@link Role#setUsersEmployees(Collection)}
     *   <li>{@link Role#getId()}
     *   <li>{@link Role#getRoleName()}
     *   <li>{@link Role#getUsersEmployees()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Role role = new Role();

        // Act
        role.setId(1L);
        role.setRoleName("Role Name");
        ArrayList<UserEmployee> usersEmployees = new ArrayList<>();
        role.setUsersEmployees(usersEmployees);
        Long actualId = role.getId();
        String actualRoleName = role.getRoleName();
        Collection<UserEmployee> actualUsersEmployees = role.getUsersEmployees();

        // Assert that nothing has changed
        assertEquals("Role Name", actualRoleName);
        assertEquals(1L, actualId.longValue());
        assertSame(usersEmployees, actualUsersEmployees);
    }
}
