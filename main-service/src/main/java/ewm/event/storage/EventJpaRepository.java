package ewm.event.storage;

import ewm.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    @Query(value = "SELECT * FROM events " +
            "WHERE initiator_id = ?1 " +
            "ORDER BY id ASC " +
            "LIMIT ?3 OFFSET ?2", nativeQuery = true)
    List<EventEntity> getEventsByUserIdAndFilters(Long userId, Integer from, Integer size);

    List<EventEntity> findAllByIdIn(List<Long> ids);

    boolean existsByCategoryId(Long catId);
}
