package ewm.compilation.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UpdateCompilationRequest {
    private Set<Long> events;
    private Boolean pinned;
    @Size(max = 50, min = 1)
    private String title;
}
