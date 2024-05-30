package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.ImageDataFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageDataFile, Long> {

    Optional<ImageDataFile> findByName(String name);
}
