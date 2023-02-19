package resume_viewer.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import resume_viewer.dto.FieldErrorDto;
import resume_viewer.dto.ValidationErrorDto;

import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationError(MethodArgumentNotValidException exception) {
        ValidationErrorDto dto = new ValidationErrorDto();
        List<FieldErrorDto> errors = exception.getFieldErrors().stream().map(e -> {
            FieldErrorDto fieldsError = new FieldErrorDto();
            fieldsError.setField(e.getField());
            fieldsError.setValue(e.getRejectedValue());
            fieldsError.setMessage(e.getDefaultMessage());
            return fieldsError;
        }).collect(Collectors.toList());

        dto.setError(errors);
        dto.setValidationFailed(Boolean.TRUE);
        dto.setMsg("Validation Error");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
