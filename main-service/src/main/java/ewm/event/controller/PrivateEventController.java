package ewm.event.controller;

import ewm.event.dto.EventFullDto;
import ewm.event.dto.NewEventDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class PrivateEventController {

    @PostMapping
    public EventFullDto postEvent(@PathVariable Long userId, @RequestBody @Valid NewEventDto dto) {

    }

}
