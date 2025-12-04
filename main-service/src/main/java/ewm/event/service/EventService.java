package ewm.event.service;

import ewm.event.domain.Event;
import ewm.event.dto.AdminFilterEvent;
import ewm.event.dto.PublicEventFilter;
import ewm.event.dto.UpdateEventAdminRequest;
import ewm.event.dto.UpdateEventUserRequest;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.exception.ValidateException;

import java.util.List;

public interface EventService {

    Event postEvent(Long userId, Event event) throws NotFoundException, ValidateException;

    List<Event> getEventsByUserId(Long userId, Integer from, Integer size) throws NotFoundException;

    Event getEventByIdAndOwnerId(Long userId, Long eventId) throws NotFoundException;

    Event patchByUserEvent(Long userId, Long eventId, UpdateEventUserRequest event) throws NotFoundException, ConditionsNotMetException, ValidateException;

    Event getEventById(Long eventId) throws NotFoundException;

    boolean existById(Long eventId);

    List<Event> getEventsByPublicFilter(PublicEventFilter filter, String ip) throws ValidateException;

    Event getEventByIdWithView(Long eventId, String ip) throws NotFoundException;

    List<Event> getEventsByAdminFilter(AdminFilterEvent filer);

    Event patchByAdminEvent(UpdateEventAdminRequest update, Long eventId) throws NotFoundException, ConditionsNotMetException, ValidateException;

    Event saveEvent(Event event);

    List<Event> getAllByIds(List<Long> ids);
}
