package com.test.clearsolutionstesttask.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.test.clearsolutionstesttask.handler.exception.NotFoundException;
import com.test.clearsolutionstesttask.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.test.clearsolutionstesttask.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.test.context.TestPropertySource;


@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="classpath:/application.yml")
public class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void testGetUserByEmail() {
    User user = new User();
    user.setEmail("test@example.com");
    user.setBirthDate(LocalDate.of(2000, 1, 1));
    userService.createUser(user);

    assertEquals(user, userService.getUserByEmail("test@example.com"));
  }

  @Test
  void testGetUserByEmailNotFound() {
    assertThrows(NotFoundException.class,
        () -> userService.getUserByEmail("nonexistent@example.com"));
  }

  @Test
  void testCreateUser() {
    User user = new User();
    user.setEmail("newuser@example.com");
    user.setBirthDate(LocalDate.of(2000, 1, 1));

    User createdUser = userService.createUser(user);
    assertNotNull(createdUser);
    assertEquals(user, createdUser);
  }

  @Test
  void testUpdateUser() {
    User user = new User();
    user.setEmail("update@example.com");
    user.setBirthDate(LocalDate.of(2000, 1, 1));
    userService.createUser(user);

    user.setFirstname("UpdatedFirstName");
    user.setLastName("UpdatedLastName");
    user.setBirthDate(LocalDate.of(2001, 1, 1));
    user.setAddress("UpdatedAddress");
    user.setPhoneNumber("UpdatedPhoneNumber");

    User updatedUser = userService.updateUser(user);

    assertEquals("UpdatedFirstName", updatedUser.getFirstname());
    assertEquals("UpdatedLastName", updatedUser.getLastName());
    assertEquals(LocalDate.of(2001, 1, 1), updatedUser.getBirthDate());
    assertEquals("UpdatedAddress", updatedUser.getAddress());
    assertEquals("UpdatedPhoneNumber", updatedUser.getPhoneNumber());
  }

  @Test
  void testUpdateUserNotFound() {
    User user = new User();
    user.setEmail("update@example.com");
    user.setBirthDate(LocalDate.of(2000, 1, 1));

    assertThrows(NotFoundException.class, () -> userService.updateUser(user));
  }

  @Test
  void testUpdateUserFields() {
    User user = new User("update@example.com", "1", "2", LocalDate.now().minusYears(20), "4", "5");
    userService.createUser(user);

    String firstName = "UpdatedFirstName";
    String lastName = "UpdatedLastName";
    LocalDate birthDate = LocalDate.of(2001, 1, 1);
    String address = "UpdatedAddress";
    String phoneNumber = "UpdatedPhoneNumber";

    User updatedUser =
        userService.updateUserFields(user.getEmail(), firstName, lastName, birthDate, address,
            phoneNumber);

    assertEquals(firstName, updatedUser.getFirstname());
    assertEquals(lastName, updatedUser.getLastName());
    assertEquals(birthDate, updatedUser.getBirthDate());
    assertEquals(address, updatedUser.getAddress());
    assertEquals(phoneNumber, updatedUser.getPhoneNumber());
  }

  @Test
  void testDeleteUser() {
    User user = new User();
    user.setEmail("delete@example.com");
    user.setBirthDate(LocalDate.of(2000, 1, 1));
    userService.createUser(user);

    assertTrue(userService.deleteUser("delete@example.com"));
  }

  @Test
  void testDeleteUserNotFound() {
    assertThrows(NotFoundException.class, () -> userService.deleteUser("nonexistent@example.com"));
  }
}

