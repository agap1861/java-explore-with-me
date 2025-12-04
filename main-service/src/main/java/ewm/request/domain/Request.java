package ewm.request.domain;

import ewm.event.domain.Event;
import ewm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Request {
    private Long id;
    private LocalDateTime created;
    private Event event;
    private User requester;
    private RequestStatus status;
}
