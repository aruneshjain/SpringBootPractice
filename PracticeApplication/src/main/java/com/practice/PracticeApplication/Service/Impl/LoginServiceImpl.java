package com.practice.PracticeApplication.Service.Impl;

import com.practice.PracticeApplication.Controller.UserController;
import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.Repository.LoginRepository;
import com.practice.PracticeApplication.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UserController UC;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public ResponseEntity<String> newLogin(Users user) {
        try {
            user.getLogin().setPassword(
                    encoder.encode(user.getLogin().getPassword()));
            UC.saveUser(user);
        }
        catch (Exception e){
            return new ResponseEntity<>("New Login Details failed : " + e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateLoginDetails(UUID id, HashMap<String, String> details) {
        Login login = loginRepository.findByUser(id)
                .orElseThrow(() -> new RuntimeException("Login User Not Found"));
        if(details.containsKey("password"))
            login.setPassword(encoder.encode(details.get("password")));

        if(details.containsKey("username"))
            login.setUsername(details.get("username"));

        loginRepository.save(login);
        return new ResponseEntity<>("Login Details Updated : " , HttpStatus.OK);
    }
}
