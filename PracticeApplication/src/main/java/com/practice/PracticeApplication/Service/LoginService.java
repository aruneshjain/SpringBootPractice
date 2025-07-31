package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.UUID;


public interface LoginService {
    ResponseEntity<String> signUp(Login login);

    ResponseEntity<String> updateLoginDetails(UUID id, HashMap<String, String> login);

    ResponseEntity<String> loginUser(HashMap<String, String> details);
}
