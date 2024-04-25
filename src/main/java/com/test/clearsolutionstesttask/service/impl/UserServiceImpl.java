package com.test.clearsolutionstesttask.service.impl;

import com.test.clearsolutionstesttask.entity.User;
import com.test.clearsolutionstesttask.handler.exception.NotFoundException;
import com.test.clearsolutionstesttask.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service with business logic related to users.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  @Value("${api.validation.user.min-age}")
  private int minAge;

  private List<User> users = new ArrayList<>();

  /**
   * Get user by email as search param.
   *
   * @param email identifier of user.
   * @return instance of user.
   */
  @Override
  public User getUserByEmail(final String email) {
    return Optional.of(users.stream().filter(user -> user.getEmail().equals(email)).findFirst())
        .get()
        .orElseThrow(() -> new NotFoundException(
            String.format("User with email %s is not found. Time: %s", email,
                LocalDateTime.now(ZoneOffset.UTC))));
  }

  /**
   * Get all users which birthdate is between two dates.
   *
   * @param date1 start of the date range.
   * @param date2 end of the date range.
   * @return list of users.
   */
  @Override
  public List<User> getUsersByBirthDateBetween(LocalDate date1, LocalDate date2) {
    return users.stream()
        .filter(user -> user.getBirthDate().isAfter(date1))
        .filter(user -> user.getBirthDate().isBefore(date2))
        .collect(Collectors.toList());
  }

  /**
   * Create user.
   *
   * @param user instance of user to save.
   * @return user.
   */
  @Override
  public User createUser(User user) {
    if (ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now()) < minAge) {
      throw new IllegalStateException(
          String.format("User`s age should be greater than 18. Time: %s.",
              LocalDateTime.now(ZoneOffset.UTC)));
    }
    final var usersSet = new HashSet<>(users);
    usersSet.add(user);
    users = new ArrayList<>(usersSet);
    return user;
  }

  /**
   * Update whole user.
   *
   * @param user updated user.
   * @return updated version of user.
   */
  @Override
  public User updateUser(User user) {
    final var userToUpdate = Optional.of(
            users.stream().filter(streamUser -> streamUser.getEmail().equals(user.getEmail()))
                .findFirst())
        .get()
        .orElseThrow(() -> new NotFoundException(
            String.format("User with email %s is not found. Time: %s", user.getEmail(),
                LocalDateTime.now(ZoneOffset.UTC))));
    users.remove(userToUpdate);
    users.add(user);
    return user;
  }

  /**
   * Update user one or some fields.
   *
   * @param email identifier of user.
   * @param firstName first name of user.
   * @param lastName last name of user.
   * @param birthDate date of birth for user.
   * @param address address of user.
   * @param phoneNumber phone number of user.
   * @return updated instance of user.
   */
  @Override
  public User updateUserFields(String email, String firstName, String lastName, LocalDate birthDate,
                               String address, String phoneNumber) {
    final var userToUpdate =
        Optional.of(users.stream().filter(user -> user.getEmail().equals(email)).findFirst())
            .get()
            .orElseThrow(() -> new NotFoundException(
                String.format("User with email %s is not found. Time: %s", email,
                    LocalDateTime.now(ZoneOffset.UTC))));
    final var updatedUser =
        updateUserFields(userToUpdate, firstName, lastName, birthDate, address, phoneNumber);
    users.remove(userToUpdate);
    users.add(updatedUser);
    return updatedUser;
  }

  /**
   * Delete user by email.
   *
   * @param email base identifier of the user.
   * @return boolean value of result.
   */
  @Override
  public boolean deleteUser(String email) {
    final var userDelete = Optional.of(
            users.stream().filter(user -> email.equals(user.getEmail()))
                .findFirst())
        .get()
        .orElseThrow(() -> new NotFoundException(
            String.format("User with email %s is not found. Time: %s", email,
                LocalDateTime.now(ZoneOffset.UTC))));
    users.remove(userDelete);
    return true;
  }

  /**
   * Long method to check which fields are needed to be updates and updating it.
   *
   * @param user user to update.
   * @param firstName first name of user.
   * @param lastName last name of user.
   * @param birthDate date of birth for user.
   * @param address address of user.
   * @param phoneNumber phone number of user.
   * @return updated instance of user.
   */
  private User updateUserFields(User user, String firstName, String lastName, LocalDate birthDate,
                                String address, String phoneNumber) {
    final var clone = user.clone();
    if (!"".equals(firstName) && !user.getFirstname().equals(firstName)) {
      clone.setFirstname(firstName);
    }
    if (!"".equals(lastName) && !user.getLastName().equals(lastName)) {
      clone.setLastName(lastName);
    }
    if (!user.getBirthDate().equals(birthDate) &&
        ChronoUnit.YEARS.between(birthDate, LocalDate.now()) >= minAge) {
      clone.setBirthDate(birthDate);
    }
    if (!"".equals(address) && !user.getAddress().equals(address)) {
      clone.setAddress(address);
    }
    if (!"".equals(phoneNumber) && !user.getPhoneNumber().equals(address)) {
      clone.setPhoneNumber(phoneNumber);
    }
    return clone;
  }
}
