package dev.lvpq.CS502052.Validator;

import dev.lvpq.CS502052.Dto.Request.RegisterRequest;
import dev.lvpq.CS502052.Dto.Request.ResetPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordMatchesValidator implements ConstraintValidator <PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj instanceof ResetPassword request) {
            return request.getPassword().equals(request.getRepassword());
        } else if (obj instanceof RegisterRequest request) {
            return request.getPassword().equals(request.getRepassword());
        }
        return false;
    }
}
