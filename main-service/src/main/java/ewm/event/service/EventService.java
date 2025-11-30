package ewm.event.service;

import ewm.event.domain.Event;
import ewm.event.dto.EventFilter;
import ewm.event.dto.UpdateEventUserRequest;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;

import java.util.List;

public interface EventService {

    Event postEvent(Long userId, Event event) throws NotFoundException, ConditionsNotMetException;

    List<Event> getEventsByUserId(Long userId, Integer from,Integer size) throws NotFoundException;

    Event getEventByIdAndOwnerId(Long userId, Long eventId) throws NotFoundException;

    Event patchEvent(Long userId, Long eventId, UpdateEventUserRequest event) throws NotFoundException, ConditionsNotMetException;

    Event getEventById(Long eventId) throws NotFoundException;

    boolean existById(Long eventId);

    boolean existEventsByIds(List<Long> ids);

    List<Event> getEventsByFilter(EventFilter filter);

    Event getPublishedEventById(Long eventId) throws NotFoundException;
}
