package resume_viewer.dto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class FieldErrorDto {
    private String field;
    private Object value;
    private String message;
}