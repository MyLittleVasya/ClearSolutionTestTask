package com.test.clearsolutionstesttask.util;

import com.test.clearsolutionstesttask.util.DateRangeValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateRangeValidatorTest {

  private final DateRangeValidator validator = new DateRangeValidator();

  @Test
  void testValidDateRange() {
    LocalDate startDate = LocalDate.of(2022, 1, 1);
    LocalDate endDate = LocalDate.of(2022, 1, 10);
    assertTrue(validator.isValid(new Object[]{startDate, endDate}, null));
  }

  @Test
  void testInvalidDateRange() {
    LocalDate startDate = LocalDate.of(2022, 1, 15);
    LocalDate endDate = LocalDate.of(2022, 1, 10);
    assertFalse(validator.isValid(new Object[]{startDate, endDate}, null));
  }
}

