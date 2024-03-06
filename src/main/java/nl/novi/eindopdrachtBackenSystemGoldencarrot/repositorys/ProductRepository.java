package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;


import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);
}
