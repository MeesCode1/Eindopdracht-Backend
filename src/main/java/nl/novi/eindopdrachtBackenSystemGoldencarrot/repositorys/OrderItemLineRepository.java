package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;


import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemLineRepository extends JpaRepository<OrderItemLine, Long> {

    Optional<OrderItemLine> findByOrder(Order order);

    Optional<OrderItemLine> findByOrder_IdAndProduct_Name(Long orderId, String productName);

    List<OrderItemLine> findByProduct_Name(String productName);
}
