package resume_viewer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginRequestDto {
    @NotNull(message = "Please enter valid emailOrMobile")
    private String username;
    @NotNull(message = "Please enter valid password")
    private String password;
}
