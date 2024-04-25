package com.test.clearsolutionstesttask.service;

import com.test.clearsolutionstesttask.entity.User;
import java.time.LocalDate;
import java.util.List;

public interface UserService {

  User getUserByEmail(String email);

  List<User> getUsersByBirthDateBetween(LocalDate date1, LocalDate date2);

  User createUser(User user);

  User updateUser(User user);

  User updateUserFields(String email, String firstName, String lastName, LocalDate birthDate,
                        String address, String phoneNumber);

  boolean deleteUser(String email);
}
