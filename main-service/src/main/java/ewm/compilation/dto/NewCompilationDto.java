package ewm.compilation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class NewCompilationDto {
    private Set<Long> events;
    private Boolean pinned = false;
    @Size(max = 50,min = 1, message = "wrong size for title")
    @NotBlank
    private String title;
}
