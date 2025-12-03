package ewm.event.controller;

import ewm.event.domain.Event;
import ewm.event.dto.PublicEventFilter;
import ewm.event.dto.EventFullDto;
import ewm.event.dto.EventShortDto;
import ewm.event.mapper.EventDomainDto;
import ewm.event.service.EventService;
import ewm.exception.NotFoundException;
import ewm.exception.ValidateException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class PublicEventController {
    private final EventService service;
    private final EventDomainDto mapper;


    @GetMapping
    public List<EventFullDto> getEventsByFilter(PublicEventFilter filter, HttpServletRequest request) throws ValidateException {


        List<Event> events = service.getEventsByPublicFilter(filter,request.getRemoteAddr());
        return events.stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable Long id,HttpServletRequest request) throws NotFoundException {
        Event event = service.getEventByIdWithView(id,request.getRemoteAddr());
        return mapper.toDto(event);
    }
}
