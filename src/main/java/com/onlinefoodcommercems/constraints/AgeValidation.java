package com.onlinefoodcommercems.constraints;

import com.onlinefoodcommercems.utils.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeValidation {
    String message() default "Age must be min 18 years old.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
