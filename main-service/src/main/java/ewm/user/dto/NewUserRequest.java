package ewm.user.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewUserRequest {
    @Size(min = 6,max = 254,message = "wrong size of email")
    @Email(message = "email not correct")
    @NotBlank(message = "email contains blank")
    private String email;

    @Size(min = 2, max = 250, message = "wrong  size of name")
    @NotEmpty(message = "name is empty")
    private String name;
}
