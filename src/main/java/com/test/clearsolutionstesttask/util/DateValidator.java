package com.test.clearsolutionstesttask.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Class for validating date to determine whether this date is before today.
 */
public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

  @Override
  public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
    return null != value && value.isBefore(LocalDate.now());
  }
}
