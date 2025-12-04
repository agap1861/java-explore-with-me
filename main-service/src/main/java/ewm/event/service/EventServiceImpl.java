package ewm.event.service;

import client.StatClient;
import dto.HitDto;
import dto.StatDto;
import ewm.categories.domain.Category;
import ewm.categories.service.CategoryService;
import ewm.event.domain.*;
import ewm.event.dto.AdminFilterEvent;
import ewm.event.dto.PublicEventFilter;
import ewm.event.dto.UpdateEventAdminRequest;
import ewm.event.dto.UpdateEventUserRequest;
import ewm.event.storage.EventStorage;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.exception.ValidateException;
import ewm.user.domain.User;
import ewm.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserService userService;
    private final CategoryService categoryService;
    private final EventStorage storage;
    private final StatClient client;

    @Override
    @Transactional
    public Event postEvent(Long userId, Event event) throws NotFoundException, ValidateException {
        User user = userService.getUserById(userId);
        validateDateEvent(event.getEventDate());
        Category category = getAndValidateCategory(event);
        event.setInitiator(user);
        event.setCategory(category);
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
    @Transactional
    public Event patchByUserEvent(Long userId, Long eventId, UpdateEventUserRequest patchEvent) throws NotFoundException, ConditionsNotMetException, ValidateException {
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
        if (patchEvent.getTitle() != null) {
            event.setTitle(patchEvent.getTitle());
        }

        if (patchEvent.getStateAction() != null) {
            StateAction action = patchEvent.getStateAction();

            switch (action) {
                case SEND_TO_REVIEW:
                    if (event.getState().equals(EventState.PUBLISHED)) {
                        throw new ConditionsNotMetException("Published event cannot be sent to review");
                    }
                    event.setState(EventState.PENDING);
                    break;

                case CANCEL_REVIEW:
                    if (!event.getState().equals(EventState.PENDING)) {
                        throw new ConditionsNotMetException("Only pending events can be canceled");
                    }
                    event.setState(EventState.CANCELED);
                    break;
            }
        }

        return storage.save(event);

    }

    @Override
    public Event getEventById(Long eventId) throws NotFoundException {
        Event event = storage.getById(eventId).orElseThrow(
                () -> new NotFoundException("event with id " + eventId + "does not exist")
        );
        return event;
    }

    @Override
    public boolean existById(Long eventId) {
        return storage.existById(eventId);
    }

    @Override
    public List<Event> getEventsByPublicFilter(PublicEventFilter filter, String ip) throws ValidateException {
        if (filter.getRangeStart() != null && filter.getRangeEnd() != null && filter.getRangeEnd().isBefore(filter.getRangeStart())) {
            throw new ValidateException("end must be after start");
        }
        List<Event> events = storage.getEventsByPublicFilter(filter);
        events.stream()
                .map(event -> {
                    HitDto dto = new HitDto();
                    dto.setApp("ewm-main-service");
                    dto.setIp(ip);
                    dto.setUri("/events/" + event.getId());
                    dto.setTimestamp(LocalDateTime.now());
                    return dto;
                })
                .forEach(client::postStat);
        List<String> uris = events.stream()
                .map(event -> "/events/" + event.getId())
                .toList();
        List<StatDto> stats = client.getStats(LocalDateTime.now().minusYears(1),
                LocalDateTime.now().plusYears(1), uris, false);
        Map<Long, Long> views = stats.stream()
                .collect(Collectors.toMap(
                        s -> s.extractIdFromUri(s.getUri()),
                        StatDto::getHits
                ));


        events.forEach(event ->
                event.setViews(
                        views.getOrDefault(event.getId(), 0L)
                )
        );
        if (filter.getSort() != null && filter.getSort().equals("VIEWS")) {
            return events.stream()
                    .sorted(Comparator.comparing(Event::getViews))
                    .toList();
        }
        return events;


    }

    @Override
    public Event getEventByIdWithView(Long eventId, String ip) throws NotFoundException {
        Event event = getEventById(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new NotFoundException("event must be published");
        }
        HitDto dto = new HitDto();
        dto.setIp(ip);
        dto.setApp("ewm-main-service");
        dto.setUri("/events/" + eventId);
        dto.setTimestamp(LocalDateTime.now());
        client.postStat(dto);
        List<StatDto> stat = client.getStats(LocalDateTime.now().minusYears(1),
                LocalDateTime.now().plusYears(1), List.of(dto.getUri()), true);
        event.setViews(stat.getFirst().getHits());
        return event;
    }

    @Override
    public List<Event> getEventsByAdminFilter(AdminFilterEvent filer) {
        return storage.getEventsByAdminFilter(filer);
    }

    @Override
    @Transactional
    public Event patchByAdminEvent(UpdateEventAdminRequest update, Long eventId) throws NotFoundException, ConditionsNotMetException, ValidateException {
        Event event = storage.getById(eventId).orElseThrow(
                () -> new NotFoundException("event with id " + eventId + "does not exist"));
        if (update.getAnnotation() != null) {
            event.setAnnotation(update.getAnnotation());
        }
        if (update.getDescription() != null) {
            event.setDescription(update.getDescription());
        }
        if (update.getTitle() != null) {
            event.setTitle(update.getTitle());
        }
        if (update.getCategory() != null) {
            Category category = categoryService.getCategoryById(update.getCategory());
            event.setCategory(category);
        }
        if (update.getEventDate() != null) {
            if (update.getEventDate().isBefore(LocalDateTime.now())) {
                throw new ValidateException("date can not be in past");
            }

            if (event.getState().equals(EventState.PUBLISHED)
                    && update.getEventDate().isBefore(event.getPublishedOn().plusHours(1))) {
                throw new ValidateException("event start date must be no earlier than an hour after publication");
            }
            event.setEventDate(update.getEventDate());
        }
        if (update.getPaid() != null) {
            event.setPaid(update.getPaid());
        }
        if (update.getParticipantLimit() != null) {
            event.setParticipantLimit(update.getParticipantLimit());
        }
        if (update.getRequestModeration() != null) {
            event.setRequestModeration(update.getRequestModeration());
        }
        if (update.getStateAction() != null) {
            if (!event.getState().equals(EventState.PENDING)) {
                throw new ConditionsNotMetException("status must be PENDING");
            }
            if (update.getStateAction().equals(AdminStateAction.PUBLISH_EVENT)) {
                event.setState(EventState.PUBLISHED);
            } else if (update.getStateAction().equals(AdminStateAction.REJECT_EVENT)) {
                event.setState(EventState.CANCELED);
            }
        }


        return storage.save(event);
    }

    @Override
    @Transactional
    public Event saveEvent(Event event) {
        return storage.save(event);
    }

    @Override
    public List<Event> getAllByIds(List<Long> ids) {
        return storage.findAll(ids);
    }

    private void validateDateEvent(LocalDateTime eventDate) throws ValidateException {

        if (eventDate.isBefore(LocalDateTime.now())) {
            throw new ValidateException("time must be before current time");
        }
        LocalDateTime minAvailable = LocalDateTime.now().plusHours(2);
        if (eventDate.isBefore(minAvailable)) {
            throw new ValidateException("not available time, must me at least throw 2 hours");
        }
    }

    private Category getAndValidateCategory(Event event) throws NotFoundException {
        return categoryService.getCategoryById(event.getCategory().getId());

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
