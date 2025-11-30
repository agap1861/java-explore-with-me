package ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ewm.event.domain.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class NewEventDto {
    @Size(max = 2000, min = 20)
    @NotNull
    private String annotation;

    @NotNull
    private Long category;

    @NotNull
    @Size(max = 7000, min = 20)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime eventDate;

    @NotNull
    private LocationDto location;

    private Boolean paid = false;

    private Integer participantLimit = 0;

    private Boolean requestModeration = true;

    @NotNull
    @Size(max = 120, min = 3)
    private String title;


}
