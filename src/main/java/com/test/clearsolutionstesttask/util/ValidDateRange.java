package com.test.clearsolutionstesttask.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constraint to validate two date params(range).
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface ValidDateRange {
  String message() default "Dates are invalid. Date 1 should be before Date 2.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
