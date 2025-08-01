package com.practice.PracticeApplication.Service.Impl;

import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Repository.LoginRepository;
import com.practice.PracticeApplication.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public ResponseEntity<String> signUp(Login login) {
        try {
            login.setPassword(
                    encoder.encode(login.getPassword()));
            loginRepository.save(login);
        }
        catch (Exception e){
            return new ResponseEntity<>("SignUp failed : " + e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Sign Up Successful : Welcome to this Application", HttpStatus.CREATED);
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

    @Override
    public ResponseEntity<String> loginUser(HashMap<String, String> details) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(details.get("username"), details.get("password")));
            if(authentication.isAuthenticated())
                return jwtService.generateToken(details.get("username"));
            return new ResponseEntity<>("User Log in fail : Please Try Again", HttpStatus.FORBIDDEN);
        }catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage() + " : Invalid Username or Password ",HttpStatus.FORBIDDEN);
        }
    }
}
