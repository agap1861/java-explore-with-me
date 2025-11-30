package ewm.event.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EventFilter {
    private String text;
    private List<Long> categories;
    private Boolean paid;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private Boolean onlyAvailable = false;
    private String sort;
    private Integer from;
    private Integer size;
}
