package ewm.event.storage;

import ewm.event.domain.EventState;
import ewm.event.dto.EventFilter;
import ewm.event.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventSpecification {

    public Specification<EventEntity> getEventsByFilter(EventFilter filter) {
        Specification<EventEntity> spec = Specification.where(isPublished());

        if (filter.getText() != null && !filter.getText().isBlank()) {
            spec = spec.and(hasText(filter.getText()));
        }
        if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
            spec = spec.and(hasCategory(filter.getCategories()));
        }
        if (filter.getPaid() != null) {
            spec = spec.and(isPaid(filter.getPaid()));
        }
        if (filter.getRangeStart() != null) {
            spec = spec.and(startTime(filter.getRangeStart()));
        }
        if (filter.getRangeEnd() != null) {
            spec = spec.and(endTime(filter.getRangeEnd()));
        }
        if (filter.getRangeStart() == null && filter.getRangeEnd() == null) {
            spec = spec.and(startTime(LocalDateTime.now()));
        }
        if (filter.getOnlyAvailable() != null) {
            spec = spec.and(onlyAvailable());
        }

        return spec;
    }

    private Specification<EventEntity> isPublished() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("state"), EventState.PUBLISHED));
    }

    private Specification<EventEntity> hasText(String text) {
        return (root, query, cb) -> {
            String pattern = "%" + text.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("annotation")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern)
            );
        };
    }

    private Specification<EventEntity> hasCategory(List<Long> ids) {
        return (root, query, cb) ->
                root.get("category").get("id").in(ids);


    }

    private Specification<EventEntity> isPaid(Boolean paid) {
        return (root, query, cb) ->
                cb.equal(root.get("paid"), paid);

    }

    private Specification<EventEntity> startTime(LocalDateTime start) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("eventDate"), start);

    }

    private Specification<EventEntity> endTime(LocalDateTime end) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("eventDate"), end);
    }

    private Specification<EventEntity> onlyAvailable() {
        return (root, query, cb) ->
                cb.lessThan(root.get("confirmedRequests"), root.get("participantLimit"));
    }

}
