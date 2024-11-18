package dev.lvpq.CS502052.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {DobValidator.class}
)
public @interface DobConstraint {
    String message() default "At least 11 years to Create Account";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min();
}
