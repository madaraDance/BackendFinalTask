package hourlyworkapplication.hourlyworkapp.model;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface WorkTypeRepository extends CrudRepository<WorkType, Long> {
    Optional<WorkType> findById(Long id);

    WorkType findByName(String name);
}
