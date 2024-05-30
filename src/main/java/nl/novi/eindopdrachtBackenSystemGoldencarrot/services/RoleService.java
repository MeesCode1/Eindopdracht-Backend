package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.RoleDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repos;

    public RoleService(RoleRepository repos) {
        this.repos = repos;
    }

    public Long createRole(RoleDto roledto) {

        Role role = new Role();
        role.setRoleName(roledto.roleName);
        role = repos.save(role);

        return role.getId();
    }
}
