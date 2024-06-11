package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Invoice;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Optional<Invoice> findByOrderNumber(Long orderNumber);

    Iterable<Invoice> findByCustomer_Company(String company);
}
