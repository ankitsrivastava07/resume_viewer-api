package resume_viewer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorDto {
    private String msg;
    private Object error;
    private Boolean validationFailed;
}
