package ewm.event.dto;

import ewm.categories.dto.CategoryDto;
import ewm.user.dto.UserShortDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventShortDto {
    private Long id;
    private String description;
    private CategoryDto category;
    private Integer confirmedRequests;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}
