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
    String message() default "{jakarta.validation.constraints.Size.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min();
}
