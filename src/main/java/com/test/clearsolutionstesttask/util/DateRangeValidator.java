package com.test.clearsolutionstesttask.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;

/**
 * Class for validating date ranges, determining whether first date is before second date.
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Object[]> {

  @Override
  public boolean isValid(Object[] value, ConstraintValidatorContext context) {
   if (!(value[0] instanceof LocalDate) || !(value[1] instanceof LocalDate)) {
     throw new IllegalStateException("The passed objects arent LocalDate instances");
   }

    return ((LocalDate) value[0]).isBefore((LocalDate) value[1]);
  }
}
