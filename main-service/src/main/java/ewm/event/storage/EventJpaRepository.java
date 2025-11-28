package ewm.event.storage;

import ewm.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<EventEntity,Long> {
}
