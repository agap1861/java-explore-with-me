package ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ewm.event.domain.Location;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class NewEventDto {
    @Size(max = 2000, min = 20, message = "size of annotation does not correct")
    @NotBlank
    private String annotation;

    @NotNull
    private Long category;


    @Size(max = 7000, min = 20)
    @NotBlank
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime eventDate;

    @NotNull
    private LocationDto location;

    private Boolean paid = false;

    @PositiveOrZero
    private Integer participantLimit = 0;

    private Boolean requestModeration = true;

    @NotBlank
    @Size(max = 120, min = 3,message = "size of title does not correct")
    private String title;


}
