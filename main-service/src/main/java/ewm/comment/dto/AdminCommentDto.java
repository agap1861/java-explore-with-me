package ewm.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ewm.event.dto.EventShortDto;
import ewm.user.dto.UserShortDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AdminCommentDto {
    private Long id;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private UserShortDto author;
    private EventShortDto event;
}
