package ewm.event.dto;

import ewm.event.domain.EventRequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private EventRequestStatus status;
}
