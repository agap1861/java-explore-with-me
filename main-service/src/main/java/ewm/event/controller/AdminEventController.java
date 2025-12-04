package ewm.event.controller;

import ewm.event.domain.Event;
import ewm.event.dto.AdminFilterEvent;
import ewm.event.dto.EventFullDto;
import ewm.event.dto.UpdateEventAdminRequest;
import ewm.event.mapper.EventDomainDto;
import ewm.event.service.EventService;
import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.exception.ValidateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {
    private final EventService service;
    private final EventDomainDto mapper;

    @GetMapping
    public List<EventFullDto> getEventsForAdmin(AdminFilterEvent filter) {
        List<Event> events = service.getEventsByAdminFilter(filter);
        return events.stream()
                .map(mapper::toDto)
                .toList();
    }

    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(@Valid @RequestBody UpdateEventAdminRequest update, @PathVariable Long eventId) throws ConditionsNotMetException, NotFoundException, ValidateException {
        Event event = service.patchByAdminEvent(update, eventId);
        return mapper.toDto(event);

    }
}
