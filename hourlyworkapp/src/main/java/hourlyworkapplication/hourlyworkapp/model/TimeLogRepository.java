package hourlyworkapplication.hourlyworkapp.model;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TimeLogRepository extends CrudRepository<TimeLog, Long> {

    Optional<TimeLog> findById(Long id);

    List<TimeLog> findByUserId(Long userId);
    List<TimeLog> findByUser(AppUser user);

}
