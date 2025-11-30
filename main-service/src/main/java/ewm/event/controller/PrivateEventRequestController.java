package ewm.event.controller;


import ewm.event.dto.EventRequestStatusUpdateRequest;
import ewm.event.dto.EventRequestStatusUpdateResult;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.request.domain.Request;
import ewm.request.domain.RequestStatus;
import ewm.request.dto.ParticipationRequestDto;
import ewm.request.mapper.RequestDomainDto;
import ewm.request.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/requests")
public class PrivateEventRequestController {
    private final RequestService service;
    private final RequestDomainDto mapper;

    @GetMapping
    public List<ParticipationRequestDto> getRequestsByOwnerIdInEvent(@PathVariable Long userId,
                                                                     @PathVariable Long eventId) throws NotFoundException {
        List<Request> requests = service.getRequestsByEventId(userId, eventId);
        return requests.stream()
                .map(mapper::toDto)
                .toList();

    }

    @PatchMapping
    public EventRequestStatusUpdateResult patchRequest(@PathVariable Long userId, @PathVariable Long eventId,
                                                       @RequestBody @Valid EventRequestStatusUpdateRequest updateRequest) throws ConditionsNotMetException, NotFoundException {
        List<Request> requests = service.patchRequests(userId, eventId, updateRequest);

        List<ParticipationRequestDto> confirmed = requests.stream()
                .filter(r -> r.getStatus().equals(RequestStatus.CONFIRMED))
                .map(mapper::toDto)
                .toList();

        List<ParticipationRequestDto> rejected = requests.stream()
                .filter(r -> r.getStatus().equals(RequestStatus.REJECTED))
                .map(mapper::toDto)
                .toList();
        return new EventRequestStatusUpdateResult(confirmed, rejected);

    }
}
