package ewm.event.dto;


import ewm.event.domain.EventState;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AdminFilterEvent {
    private List<Long> users;
    private List<EventState> states;
    private List<Long> categories;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rangeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rangeEnd;
    private Integer from = 0;
    private Integer size = 10;

}
