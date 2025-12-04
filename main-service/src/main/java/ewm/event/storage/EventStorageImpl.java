package ewm.event.storage;

import ewm.event.domain.Event;
import ewm.event.dto.AdminFilterEvent;
import ewm.event.dto.PublicEventFilter;
import ewm.event.entity.EventEntity;
import ewm.event.mapper.EventDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventStorageImpl implements EventStorage {
    private final EventJpaRepository storage;
    private final EventDomainEntity mapper;
    private final EventPublicSpecification publicSpecification;
    private final EventAdminSpecification adminSpecification;


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

    @Override
    public List<Event> getEventsByUserAndFilter(Long userId, Integer from, Integer size) {
        List<EventEntity> entities = storage.getEventsByUserIdAndFilters(userId, from, size);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Event> findAll(List<Long> ids) {
        List<EventEntity> eventEntities = storage.findAllByIdIn(ids);
        return eventEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Event> getEventsByPublicFilter(PublicEventFilter filter) {
        Specification<EventEntity> spec = publicSpecification.getEventsByFilter(filter);
        Sort sort = Sort.unsorted();
        if (filter.getSort() != null && filter.getSort().equals("EVENT_DATE")) {
            sort = Sort.by("eventDate").ascending();
        }
        Pageable pageable = PageRequest.of(filter.getFrom() / filter.getSize(),
                filter.getSize(),
                sort);
        List<EventEntity> events = storage.findAll(spec, pageable).getContent();
        return events.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCategoryId(Long catId) {
        return storage.existsByCategoryId(catId);
    }

    @Override
    public List<Event> getEventsByAdminFilter(AdminFilterEvent filterEvent) {
        List<Long> users = filterEvent.getUsers();
        List<Long> categories = filterEvent.getCategories();
        if (categories != null && categories.size() == 1 && categories.getFirst().equals(0L)) {
            filterEvent.setCategories(null);
        }
        if (users != null && users.size() == 1 && users.getFirst().equals(0L)) {
            filterEvent.setUsers(null);
        }
        Specification<EventEntity> specification = adminSpecification.getEventsByFilter(filterEvent);
        Pageable pageable = PageRequest.of(filterEvent.getFrom() / filterEvent.getSize(), filterEvent.getSize());
        List<EventEntity> eventEntities = storage.findAll(specification, pageable).getContent();
        return eventEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }


}
