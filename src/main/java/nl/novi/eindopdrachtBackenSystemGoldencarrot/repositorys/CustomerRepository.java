package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCompany(String company);

}