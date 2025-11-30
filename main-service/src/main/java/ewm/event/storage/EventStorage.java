package ewm.event.storage;

import ewm.core.BaseStorage;
import ewm.event.domain.Event;
import ewm.event.dto.EventFilter;

import java.util.List;


public interface EventStorage extends BaseStorage<Long, Event> {

    List<Event> getEventsByUserAndFilter(Long userId,Integer from,Integer size);

    List<Event> findAll(List<Long> ids);

    List<Event> getEventsByFilter(EventFilter filter);
}
