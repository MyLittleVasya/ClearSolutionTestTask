package com.test.clearsolutionstesttask.handler.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 6281056665713119949L;

  public NotFoundException(String message) {
    super(message);
  }
}
