package ewm.event.controller;

import ewm.event.domain.Event;
import ewm.event.dto.EventFullDto;
import ewm.event.dto.EventShortDto;
import ewm.event.dto.NewEventDto;
import ewm.event.dto.UpdateEventUserRequest;
import ewm.event.mapper.EventDomainDto;
import ewm.event.service.EventService;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.exception.ValidateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class PrivateEventController {
    private final EventService service;
    private final EventDomainDto mapper;
    //Нужно еще второй сервис подключить


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto postEvent(@PathVariable Long userId, @RequestBody @Valid NewEventDto dto) throws NotFoundException, ValidateException {
        Event event = service.postEvent(userId, mapper.toDomain(dto));
        return mapper.toDto(event);
    }

    @GetMapping
    public List<EventFullDto> getEventsByUserId(@PathVariable Long userId,
                                                 @RequestParam(defaultValue = "0") Integer from,
                                                 @RequestParam(defaultValue = "10") Integer size) throws NotFoundException {
        List<Event> events = service.getEventsByUserId(userId, from, size);

        return events.stream()
                .map(mapper::toDto)
                .toList();

    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable Long userId, @PathVariable Long eventId) throws NotFoundException {
        Event event = service.getEventByIdAndOwnerId(userId, eventId);
        return mapper.toDto(event);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(@PathVariable Long userId,@PathVariable Long eventId, @RequestBody @Valid UpdateEventUserRequest dto) throws ConditionsNotMetException, NotFoundException, ValidateException {
        Event event = service.patchByUserEvent(userId, eventId, dto);
        return mapper.toDto(event);


    }

}
