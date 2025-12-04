package ewm.request.controller;

import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.request.domain.Request;
import ewm.request.dto.ParticipationRequestDto;
import ewm.request.mapper.RequestDomainDto;
import ewm.request.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class PrivateRequestController {
    private final RequestService service;
    private final RequestDomainDto mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto postRequest(@PathVariable Long userId, @RequestParam Long eventId) throws ConditionsNotMetException, NotFoundException {
        Request request = service.postRequest(mapper.toNewRequest(userId, eventId));
        return mapper.toDto(request);

    }

    @GetMapping
    public List<ParticipationRequestDto> getRequestByUserId(@PathVariable Long userId) throws NotFoundException {
        List<Request> requests = service.getRequestsByUserId(userId);
        return requests.stream()
                .map(mapper::toDto)
                .toList();

    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto canselRequest(@PathVariable Long userId, @PathVariable Long requestId) throws ConditionsNotMetException, NotFoundException {
        Request request = service.cancelRequest(userId, requestId);
        return mapper.toDto(request);

    }
}
