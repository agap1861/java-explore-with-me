package ewm.compilation.dto;

import ewm.event.dto.EventShortDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CompilationDto {
    private Long id;
    private List<EventShortDto> events;
    private Boolean pinned;
    private String title;

}
