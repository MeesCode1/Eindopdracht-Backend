package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserEmployeeRepository extends CrudRepository<UserEmployee, Long> {

    Optional<UserEmployee> findByUsername(String username);
    Optional<UserEmployee> findByEmployeeNumber(Long employeeNumber);
}
