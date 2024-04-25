package com.test.clearsolutionstesttask.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constraint to validate date param of endpoint.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate {
  String message() default "BirthDate is invalid. It can`t be earlier than today.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

