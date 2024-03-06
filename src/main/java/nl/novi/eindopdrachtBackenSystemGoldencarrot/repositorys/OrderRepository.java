package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;


import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {


    Optional<Order> findById(Long id);
    List<Order> findByOrderDate(LocalDate orderDate);

    Iterable<Order> findByCustomer_Company(String company);

}
