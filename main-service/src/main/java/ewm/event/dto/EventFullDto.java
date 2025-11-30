package ewm.event.dto;


import ewm.categories.dto.CategoryDto;

import ewm.event.domain.EventState;

import ewm.user.dto.UserShortDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventFullDto {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private Integer confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private LocationDto location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private EventState state;
    private String title;
    private Integer views;


}
