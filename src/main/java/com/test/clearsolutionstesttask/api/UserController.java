package com.test.clearsolutionstesttask.api;

import com.test.clearsolutionstesttask.entity.User;
import com.test.clearsolutionstesttask.handler.ErrorMessage;
import com.test.clearsolutionstesttask.service.UserService;
import com.test.clearsolutionstesttask.util.ValidDate;
import com.test.clearsolutionstesttask.util.ValidDateRange;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for user related endpoints.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

  private final UserService userService;

  @GetMapping("/{email}")
  @Operation(description = "Get user by email",
      summary = "Get user by email")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User is acquired.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User is not found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
  public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") final String email) {
    final var result = userService.getUserByEmail(email);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Get users with birthdate between dates",
      summary = "Get users with birthdate between dates")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Users are acquired.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "Date 1 is after Date 2",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
  @GetMapping("/birth/between")
  @ValidDateRange
  public ResponseEntity<List<User>> getUsersByBirthDateBetween(final LocalDate date1, final LocalDate date2) {
    final var result = userService.getUsersByBirthDateBetween(date1, date2);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Create user",
      summary = "Create user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User is created.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "User`s age is less than 18",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
  @PostMapping
  public ResponseEntity<User> createUser(@Validated @RequestBody final User user) {
    final var result = userService.createUser(user);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Update user",
      summary = "Update user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User is created.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "User`s age is less than 18 or entity is invalid",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "User is not found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
  @PutMapping()
  public ResponseEntity<User> updateUser(@RequestBody final User user) {
    final var result = userService.updateUser(user);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Update user fields",
      summary = "Update user fields")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User is created.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "User`s age is less than 18 or entity is invalid",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "User is not found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
  @PutMapping("/{email}/fields")
  public ResponseEntity<User> updateUserFields(
      @PathVariable(name = "email") final String email,
      @RequestParam(name = "firstName", required = false, defaultValue = "")
      final String firstName,
      @RequestParam(name = "lastName", required = false, defaultValue = "")
      final String lastName,
      @RequestParam(name = "birthDate") @ValidDate
      final LocalDate birthDate,
      @RequestParam(name = "address", required = false, defaultValue = "")
      final String address,
      @RequestParam(name = "phoneNumber", required = false, defaultValue = "")
      final String phoneNumber) {
    final var result = userService.updateUserFields(email, firstName, lastName, birthDate, address, phoneNumber);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Delete user",
      summary = "Delete user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User is created.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User is not found",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
  @DeleteMapping("/{email}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "email") final String email) {
    final var result = userService.deleteUser(email);
    return ResponseEntity.ok(result);
  }

}
