package ewm.request.storage;

import ewm.request.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestJpaRepository extends JpaRepository<RequestEntity, Long> {

    List<RequestEntity> findAllByRequester_Id(Long userId);

    List<RequestEntity> findAllByEvent_Id(Long eventId);

    List<RequestEntity> findAllByIdIn(List<Long> ids);

}
