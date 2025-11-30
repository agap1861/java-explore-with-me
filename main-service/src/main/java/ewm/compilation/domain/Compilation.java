package ewm.compilation.domain;

import ewm.event.domain.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Compilation {
    private Long id;
    private List<Event> events;
    private Boolean pinned;
    private String title;
}
