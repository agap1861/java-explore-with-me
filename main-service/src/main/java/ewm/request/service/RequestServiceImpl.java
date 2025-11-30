package ewm.request.service;

import ewm.event.domain.Event;
import ewm.event.domain.EventRequestStatus;
import ewm.event.domain.EventState;
import ewm.event.dto.EventRequestStatusUpdateRequest;
import ewm.event.dto.EventRequestStatusUpdateResult;
import ewm.event.service.EventService;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.request.domain.Request;
import ewm.request.domain.RequestStatus;
import ewm.request.storage.RequestStorage;
import ewm.user.domain.User;
import ewm.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestStorage storage;
    private final UserService userService;
    private final EventService eventService;


    @Override
    public Request postRequest(Request request) throws NotFoundException, ConditionsNotMetException {
        //проверить был ли такой запрос уже
        Event event = eventService.getEventById(request.getEvent().getId());
        if (userService.existById(request.getRequester().getId())) {
            throw new NotFoundException("user with id " + request.getRequester().getId() + "does not exist");
        }
        if (event.getInitiator().getId().equals(request.getRequester().getId())) {
            throw new ConditionsNotMetException("the initiator cannot create a request for his event");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsNotMetException("user can not participate in unpublished event");
        }
        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new ConditionsNotMetException("already limit participate");

        }
        if (!event.getRequestModeration()) {
            request.setStatus(RequestStatus.CONFIRMED);
        }
        return storage.save(request);

    }

    @Override
    public List<Request> getRequestsByUserId(Long userId) throws NotFoundException {
        if (!userService.existById(userId)) {
            throw new NotFoundException("user with id " + userId + "does not exist");
        }
        return storage.getRequestsByUserId(userId);
    }

    @Override
    public Request cancelRequest(Long userId, Long requestId) throws NotFoundException, ConditionsNotMetException {
        if (!userService.existById(userId)) {
            throw new NotFoundException("user with id " + userId + "does not exist");
        }
        Request request = storage.getById(requestId).orElseThrow(
                () -> new NotFoundException("request with id " + requestId + "does not exist")
        );
        if (!request.getRequester().getId().equals(userId)) {
            throw new ConditionsNotMetException("cancel request can only owner request's");
        }
        request.setStatus(RequestStatus.CANCELED);
        return storage.save(request);
    }

    @Override
    public List<Request> getRequestsByEventId(Long userId, Long eventId) throws NotFoundException {
        Event event = eventService.getEventById(eventId);
        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("event does not belong to user");
        }
        return storage.getRequestsByEventId(eventId);


    }

    @Override
    public List<Request> patchRequests(Long userId, Long eventId, EventRequestStatusUpdateRequest updateRequest) throws NotFoundException, ConditionsNotMetException {
        Event event = eventService.getEventById(eventId);
        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("event does not belong to user");
        }
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            return storage.getRequestsByIds(updateRequest.getRequestIds());
        }
        if (event.getConfirmedRequests().equals(event.getParticipantLimit())) {
            throw new ConditionsNotMetException("event has already limit participant");
        }
        if (!event.getState().equals(EventState.PENDING)) {
            throw new ConditionsNotMetException("event  must have status PENDING ");
        }
        List<Request> requests = storage.getRequestsByIds(updateRequest.getRequestIds());
        for (Request request : requests) {
            if (!request.getEvent().getId().equals(eventId)) {
                throw new IllegalArgumentException("request with id " + request.getId() + "dose not belong to event");
            }
        }

        if (updateRequest.getStatus().equals(EventRequestStatus.CONFIRMED)) {
            for (Request request : requests) {
                if (event.getConfirmedRequests().equals(event.getParticipantLimit())) {
                    request.setStatus(RequestStatus.REJECTED);
                } else {
                    request.setStatus(RequestStatus.CONFIRMED);
                    event.setConfirmedRequests(event.getConfirmedRequests() + 1);
                }

            }

        } else {
            requests = requests.stream()
                    .peek(request -> request.setStatus(RequestStatus.REJECTED)).toList();

        }
        storage.saveAll(requests);
        return requests;


    }
}
