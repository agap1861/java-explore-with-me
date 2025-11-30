package ewm.event.service;

import ewm.categories.domain.Category;
import ewm.categories.service.CategoryService;
import ewm.event.domain.Event;
import ewm.event.domain.Location;
import ewm.event.domain.EventState;
import ewm.event.domain.StateAction;
import ewm.event.dto.EventFilter;
import ewm.event.dto.UpdateEventUserRequest;
import ewm.event.storage.EventStorage;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.user.domain.User;
import ewm.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserService userService;
    private final CategoryService categoryService;
    private final EventStorage storage;

    @Override
    public Event postEvent(Long userId, Event event) throws NotFoundException, ConditionsNotMetException {
        User user = userService.getUserById(userId);
        validateDateEvent(event.getEventDate());
        validateCategory(event);
        event.setInitiator(user);
        return storage.save(event);
    }

    @Override
    public List<Event> getEventsByUserId(Long userId, Integer from, Integer size) throws NotFoundException {
        if (!userService.existById(userId)) {
            throw new NotFoundException("user with id " + userId + "does not exist");
        }
        return storage.getEventsByUserAndFilter(userId, from, size);
    }

    @Override
    public Event getEventByIdAndOwnerId(Long userId, Long eventId) throws NotFoundException {

        return getUserEventOrThrow(userId, eventId);

    }

    @Override
    public Event patchEvent(Long userId, Long eventId, UpdateEventUserRequest patchEvent) throws NotFoundException, ConditionsNotMetException {
        Event event = getUserEventOrThrow(userId, eventId);
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsNotMetException("event must not be published");
        }
        if (patchEvent.getAnnotation() != null) {
            event.setAnnotation(patchEvent.getAnnotation());
        }
        Long catId = patchEvent.getCategory();
        if (catId != null) {
            Category category = categoryService.getCategoryById(catId);
            event.setCategory(category);
        }
        if (patchEvent.getDescription() != null) {
            event.setDescription(patchEvent.getDescription());
        }
        if (patchEvent.getEventDate() != null) {
            validateDateEvent(patchEvent.getEventDate());
            event.setEventDate(patchEvent.getEventDate());
        }
        if (patchEvent.getLocation() != null) {
            Location location = new Location();
            location.setLat(patchEvent.getLocation().getLat());
            location.setLon(patchEvent.getLocation().getLon());
            event.setLocation(location);
        }
        if (patchEvent.getPaid() != null) {
            event.setPaid(patchEvent.getPaid());
        }
        if (patchEvent.getParticipantLimit() != null) {
            event.setParticipantLimit(patchEvent.getParticipantLimit());
        }
        if (patchEvent.getRequestModeration() != null) {
            event.setRequestModeration(patchEvent.getRequestModeration());
        }
        if (patchEvent.getStateAction() != null) {
            if (patchEvent.getStateAction().equals(StateAction.CANCEL_REVIEW)) {
                event.setState(EventState.PENDING);
            } else {
                event.setState(EventState.CANCELED);
            }
        }
        if (patchEvent.getTitle() != null) {
            event.setTitle(patchEvent.getTitle());
        }
        return storage.save(event);

    }

    @Override
    public Event getEventById(Long eventId) throws NotFoundException {
        return storage.getById(eventId).orElseThrow(
                () -> new NotFoundException("event with id " + eventId + "does not exist")
        );
    }

    @Override
    public boolean existById(Long eventId) {
        return storage.existById(eventId);
    }

    @Override
    public boolean existEventsByIds(List<Long> ids) {
        List<Event> events = storage.findAll(ids);
        return events.size() == ids.size();
    }

    @Override
    public List<Event> getEventsByFilter(EventFilter filter) {
        //по зрителям сортировать
        return storage.getEventsByFilter(filter);

    }

    @Override
    public Event getPublishedEventById(Long eventId) throws NotFoundException {
        Event event = getEventById(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)){
            throw new NotFoundException("event forbidden for common use");
        }
        return event;

    }

    private void validateDateEvent(LocalDateTime eventDate) throws ConditionsNotMetException {

        if (eventDate.isBefore(LocalDateTime.now())) {
            throw new ConditionsNotMetException("time must be before current time");
        }
        LocalDateTime minAvailable = eventDate.plusHours(2);
        if (eventDate.isBefore(minAvailable)) {
            throw new ConditionsNotMetException("not available time, must me at least throw 2 hors");
        }
    }

    private void validateCategory(Event event) throws NotFoundException {
        if (!categoryService.existById(event.getCategory().getId())) {
            throw new NotFoundException("category dose not exist " + event.getCategory().getId());
        }
    }

    private Event getUserEventOrThrow(Long userId, Long eventId) throws NotFoundException {
        if (!userService.existById(userId)) {
            throw new NotFoundException("user with id " + userId + "does not exist");
        }
        Event event = storage.getById(eventId).orElseThrow(
                () -> new NotFoundException("event with id " + eventId + "does not exist")
        );
        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("this event does not belong this user");
        }
        return event;
    }
}
