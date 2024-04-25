package com.test.clearsolutionstesttask.util;

import com.test.clearsolutionstesttask.util.DateValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateValidatorTest {

  private final DateValidator validator = new DateValidator();

  @Test
  void testValidDate() {
    assertTrue(validator.isValid(LocalDate.of(2000, 1, 1), null));
  }

  @Test
  void testInvalidDate() {
    assertFalse(validator.isValid(LocalDate.now(), null));
  }
}

