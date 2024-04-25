package com.test.clearsolutionstesttask.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

/**
 * Error message for handled exceptions that will be sent to client.
 */
@Value
@Builder
public class ErrorMessage {

  @Schema(
      description = "Status code of response",
      name = "statusCode",
      type = "String",
      example = "400")
  HttpStatus statusCode;

  LocalDateTime dateTime;

  String description;

  String url;

}
