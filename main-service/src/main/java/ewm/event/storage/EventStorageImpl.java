package ewm.event.storage;

import ewm.event.domain.Event;
import ewm.event.entity.EventEntity;
import ewm.event.mapper.EventDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventStorageImpl implements EventStorage {
    private final EventJpaRepository storage;
    private final EventDomainEntity mapper;


    @Override
    public Event save(Event domain) {
        EventEntity entity = storage.save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        storage.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return storage.existsById(id);
    }

    @Override
    public Optional<Event> getById(Long id) {
        Optional<EventEntity> entity = storage.findById(id);
        return entity.map(mapper::toDomain);
    }
}
