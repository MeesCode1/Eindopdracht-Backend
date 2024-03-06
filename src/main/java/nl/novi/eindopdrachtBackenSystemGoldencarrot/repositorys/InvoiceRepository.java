package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
