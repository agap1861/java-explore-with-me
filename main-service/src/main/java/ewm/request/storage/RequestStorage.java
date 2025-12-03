package ewm.request.storage;

import ewm.core.BaseStorage;
import ewm.request.domain.Request;


import java.util.List;
import java.util.Optional;

public interface RequestStorage extends BaseStorage<Long, Request> {

    List<Request> getRequestsByUserId(Long userId);

    List<Request> getRequestsByEventId(Long eventId);

    List<Request> getRequestsByIds(List<Long> ids);

    List<Request> saveAll(List<Request> requests);

    Optional<Request> getByRequesterIdAndEventId(Long requesterId,Long eventId);



}
