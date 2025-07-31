package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.UUID;


public interface LoginService {
    ResponseEntity<String> newLogin(Users user);

    ResponseEntity<String> updateLoginDetails(UUID id, HashMap<String, String> login);
}
