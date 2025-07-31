package com.practice.PracticeApplication.Controller;

import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.Service.LoginService;
import com.practice.PracticeApplication.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginService loginService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @PostMapping
//    public void login(@RequestBody Login login){
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));
//            UserDetails userDetails = myUserDetailsService.loadUserByUsername(login.getUsername());
//
//        }catch (Exception e){
//
//        }
//    }

    @PostMapping("/new")
    public ResponseEntity<String> newLogin(@RequestBody Users user){
        return loginService.newLogin(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateLoginDetails(@PathVariable UUID id, @RequestBody HashMap<String,String> login){
        return loginService.updateLoginDetails(id, login);
    }
}
