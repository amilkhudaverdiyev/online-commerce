package com.onlinefoodcommercems.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^5[1-5][0-9]{14}$")
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface CardNumberValidation {
    String message() default "Card number is provided incorrectly";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
