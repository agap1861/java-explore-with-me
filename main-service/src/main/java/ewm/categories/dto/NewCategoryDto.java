package ewm.categories.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCategoryDto {
    @Size(min = 1, max = 50, message = "Not correct size name of category")
    @NotBlank(message = "name is empty")
    private String name;
}
