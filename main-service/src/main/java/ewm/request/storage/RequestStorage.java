package ewm.request.storage;

import ewm.core.BaseStorage;
import ewm.request.domain.Request;
import ewm.request.entity.RequestEntity;

import java.util.List;

public interface RequestStorage extends BaseStorage<Long, Request> {

    List<Request> getRequestsByUserId(Long userId);

    List<Request> getRequestsByEventId(Long eventId);

    List<Request> getRequestsByIds(List<Long> ids);

    List<Request> saveAll(List<Request> requests);
}
