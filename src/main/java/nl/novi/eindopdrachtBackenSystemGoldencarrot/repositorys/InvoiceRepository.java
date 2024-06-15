package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Optional<Invoice> findByOrderNumber(Long orderId);

    Iterable<Invoice> findByCustomer_Company(String company);
}
