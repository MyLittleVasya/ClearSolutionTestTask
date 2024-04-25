package com.test.clearsolutionstesttask.handler;

import com.test.clearsolutionstesttask.handler.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler that handles exception and sends
 * expected response body to client.
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleUserNotFoundException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .statusCode(HttpStatus.NOT_FOUND)
        .dateTime(LocalDateTime.now(
            ZoneOffset.UTC))
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    log.error(exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorMessage> handleValidationException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception
  ) {
    final var message = ErrorMessage.builder()
        .statusCode(HttpStatus.NOT_FOUND)
        .dateTime(LocalDateTime.now(
            ZoneOffset.UTC))
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    log.error(exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }
}
