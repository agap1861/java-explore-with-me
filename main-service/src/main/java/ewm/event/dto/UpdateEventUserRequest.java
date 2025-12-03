package ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ewm.event.domain.StateAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UpdateEventUserRequest {
    @Size(max = 2000, min = 20, message = "size of annotation does not correct")
    private String annotation;
    private Long category;
    @Size(max = 7000, min = 20, message = "size of description does not correct")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private LocationDto location;
    private Boolean paid;
    @Positive
    private Integer participantLimit;
    private Boolean requestModeration;
    private StateAction stateAction;
    @Size(max = 120, min = 3)
    private String title;

}
