package ewm.comment.domain;

import ewm.event.domain.Event;
import ewm.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class Comment {
    private Long id;
    private String text;
    private LocalDateTime created;
    private User author;
    private Event event;
}
