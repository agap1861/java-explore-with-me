package ewm.event.storage;

import ewm.event.domain.EventState;
import ewm.event.dto.AdminFilterEvent;
import ewm.event.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventAdminSpecification {
    public Specification<EventEntity> getEventsByFilter(AdminFilterEvent filter) {
        Specification<EventEntity> spec = Specification.where(null);

        if (filter.getUsers() != null && !filter.getUsers().isEmpty()) {
            spec = spec.and(hasUsers(filter.getUsers()));
        }
        if (filter.getStates() != null && !filter.getStates().isEmpty()){
            spec = spec.and(hasState(filter.getStates()));
        }

        if (filter.getCategories()!=null && !filter.getCategories().isEmpty()){
            spec = spec.and(hasCategories(filter.getCategories()));
        }
        if (filter.getRangeStart() != null) {
            spec = spec.and(hasStart(filter.getRangeStart()));
        }
        if (filter.getRangeEnd() != null) {
            spec = spec.and(hasEnd(filter.getRangeEnd()));
        }

        return spec;

    }

    private Specification<EventEntity> hasUsers(List<Long> users) {
        return (root, query, cb) ->
                root.get("initiator").get("id").in(users);
    }

    private Specification<EventEntity> hasState(List<EventState> states) {
        return (root, query, cb) ->
                root.get("state").in(states);
    }

    private Specification<EventEntity> hasCategories(List<Long> categories) {
        return (root, query, cb) ->
                root.get("category").get("id").in(categories);
    }

    private Specification<EventEntity> hasStart(LocalDateTime start) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("eventDate"), start);
    }

    private Specification<EventEntity> hasEnd(LocalDateTime end) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("eventDate"), end);
    }
}
