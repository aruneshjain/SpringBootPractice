package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserServiceImpl {
    List<Users> getAllUsers();

    Optional<Users> getUserById(long id);

    String updateUser(long id, Users users);

    ResponseEntity<String> saveUser(Users users);

    String deleteUser(long id);
}
