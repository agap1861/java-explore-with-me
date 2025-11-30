package ewm.request.dto;

import ewm.event.domain.EventState;
import ewm.request.domain.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ParticipationRequestDto {
    private Long id;
    private LocalDateTime created;
    private Long event;
    private Long requester;
    private RequestStatus status;
}
