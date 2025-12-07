package ewm.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCommentDto {
    @NotBlank
    @Size(max = 500, min = 3, message = "size of title does not correct")
    private String text;
}
