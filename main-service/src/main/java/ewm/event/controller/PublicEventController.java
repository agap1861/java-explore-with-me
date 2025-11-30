package ewm.event.controller;

import ewm.event.domain.Event;
import ewm.event.dto.EventFilter;
import ewm.event.dto.EventFullDto;
import ewm.event.dto.EventShortDto;
import ewm.event.mapper.EventDomainDto;
import ewm.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class PublicEventController {
    private final EventService service;
    private final EventDomainDto mapper;


    @GetMapping
    public List<EventShortDto> getEventsByFilter(EventFilter filter) {
        List<Event> events = service.getEventsByFilter(filter);
        return events.stream()
                .map(mapper::toShortDto)
                .toList();
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable Long id){
        Event event = service.getEventById(id);
    }
}
