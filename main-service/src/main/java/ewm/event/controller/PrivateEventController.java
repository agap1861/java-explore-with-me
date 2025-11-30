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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public EventFullDto postEvent(@PathVariable Long userId, @RequestBody @Valid NewEventDto dto) throws ConditionsNotMetException, NotFoundException {
        Event event = service.postEvent(userId, mapper.toDomain(dto));
        return mapper.toDto(event);
    }

    @GetMapping
    public List<EventShortDto> getEventsByUserId(@PathVariable Long userId,
                                                 @RequestParam(defaultValue = "0") Integer from,
                                                 @RequestParam(defaultValue = "10") Integer size) throws NotFoundException {
        List<Event> events = service.getEventsByUserId(userId, from, size);

        return events.stream()
                .map(mapper::toShortDto)
                .toList();

    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable Long userId, @PathVariable Long eventId) throws NotFoundException {
        Event event = service.getEventByIdAndOwnerId(userId, eventId);
        return mapper.toDto(event);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(@PathVariable Long userId, Long eventId, @RequestBody @Valid UpdateEventUserRequest dto) throws ConditionsNotMetException, NotFoundException {
        Event event = service.patchEvent(userId, eventId, dto);
        return mapper.toDto(event);


    }

}
