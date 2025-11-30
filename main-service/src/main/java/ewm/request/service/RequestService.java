package ewm.request.service;

import ewm.event.dto.EventRequestStatusUpdateRequest;
import ewm.event.dto.EventRequestStatusUpdateResult;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.request.domain.Request;

import java.util.List;


public interface RequestService {
    Request postRequest(Request request) throws NotFoundException, ConditionsNotMetException;

    List<Request> getRequestsByUserId(Long userId) throws NotFoundException;

    Request cancelRequest(Long userId, Long requestId) throws NotFoundException, ConditionsNotMetException;

    List<Request> getRequestsByEventId(Long userId, Long eventId) throws NotFoundException;

    List<Request> patchRequests(Long userId, Long eventId, EventRequestStatusUpdateRequest updateRequest) throws NotFoundException, ConditionsNotMetException;

}
