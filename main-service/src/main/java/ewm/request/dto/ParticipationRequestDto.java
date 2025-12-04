package ewm.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ewm.request.domain.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ParticipationRequestDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime created;
    private Long event;
    private Long requester;
    private RequestStatus status;
}
