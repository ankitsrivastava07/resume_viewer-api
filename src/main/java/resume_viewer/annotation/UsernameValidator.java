package resume_viewer.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<UsernameValidation, String> {
    private static final String USERNAME_PATTERN = "[a-zA-Z0-9]+";
    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (username == null || username.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Please enter username").addConstraintViolation();
            return false;
        }
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Please enter username alpha numeric " + "with min 5 and max 18 characters only").addConstraintViolation();
            return false;
        }
        return true;
    }
}
