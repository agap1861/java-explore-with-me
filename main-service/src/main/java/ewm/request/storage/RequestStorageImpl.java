package ewm.request.storage;

import ewm.request.domain.Request;
import ewm.request.entity.RequestEntity;
import ewm.request.mapper.RequestDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RequestStorageImpl implements RequestStorage {
    private final RequestJpaRepository storage;
    private final RequestDomainEntity mapper;

    @Override
    public Request save(Request domain) {
        RequestEntity entity = storage.save(mapper.toEntity(domain));
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
    public Optional<Request> getById(Long id) {
        return storage.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Request> getRequestsByUserId(Long userId) {
        List<RequestEntity> entities = storage.findAllByRequester_Id(userId);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> getRequestsByEventId(Long eventId) {
        List<RequestEntity> requestEntities = storage.findAllByEvent_Id(eventId);
        return requestEntities.stream()
                .map(mapper::toDomain)
                .toList();

    }

    @Override
    public List<Request> getRequestsByIds(List<Long> ids) {
        List<RequestEntity> requestEntities = storage.findAllByIdIn(ids);
        return requestEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> saveAll(List<Request> requests) {
        List<RequestEntity> requestEntities = requests.stream().map(mapper::toEntity).toList();
        requestEntities = storage.saveAll(requestEntities);
        return requestEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Request> getByRequesterIdAndEventId(Long requesterId, Long eventId) {
        return storage.findByRequesterIdAndEventId(requesterId, eventId).map(mapper::toDomain);
    }
}
