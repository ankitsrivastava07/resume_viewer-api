package resume_viewer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto {
    private String msg;
    private Object data;
    private Boolean status;
    private Integer statusCode = 200;
}
