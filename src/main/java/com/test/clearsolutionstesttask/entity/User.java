package com.test.clearsolutionstesttask.entity;

import com.test.clearsolutionstesttask.util.ValidDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Basic entity of this application.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Cloneable{

  @NotBlank
  @Email
  @Schema(description = "email of user", example = "example@com.ua")
  String email;

  @NotBlank
  @Schema(description = "first name of user", example = "Maksym")
  String firstname;

  @NotBlank
  @Schema(description = "first name of user", example = "Shevchuk")
  String lastName;

  @ValidDate
  @Schema(description = "birth date of user", example = "2003-04-26")
  LocalDate birthDate;

  @Schema(description = "address of user", example = "Kyiv")
  String address;

  @Schema(description = "phone number of user", example = "097-777-7777")
  String phoneNumber;

  @Override
  public User clone() {
    try {
      return (User) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

}
