package resume_viewer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import resume_viewer.annotation.UsernameValidation;

@Setter
@Getter

public class CreateUserDto {
    @NotNull(message = "Enter valid firstname")
    private String firstName;
    @NotNull(message = "Enter valid lastname")
    private String lastName;
    @Email(message = "Enter valid email")
    private String email;
    @NotNull(message = "Enter valid password")
    private String password;
    @NotNull(message = "Enter valid mobile")
    private String mobile;
    //@NotNull(message = "Please enter valid username")
    @UsernameValidation
    private String username;
}
