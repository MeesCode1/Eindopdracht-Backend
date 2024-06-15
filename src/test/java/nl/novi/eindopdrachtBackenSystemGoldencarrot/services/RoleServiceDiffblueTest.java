package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.RoleDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RoleService.class})
@ExtendWith(SpringExtension.class)
class RoleServiceDiffblueTest {
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    /**
     * Method under test: {@link RoleService#createRole(RoleDto)}
     */
    @Test
    void testCreateRole() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Role Name");
        role.setUsersEmployees(new ArrayList<>());
        when(roleRepository.save(Mockito.<Role>any())).thenReturn(role);
        RoleDto roledto = new RoleDto();
        roledto.id = 1L;
        roledto.roleName = "Role Name";

        // Act
        Long actualCreateRoleResult = roleService.createRole(roledto);

        // Assert
        verify(roleRepository).save(isA(Role.class));
        assertEquals(1L, actualCreateRoleResult.longValue());
    }
}
