package com.practice.PracticeApplication.Controller;

import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("auth")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Login login){
        return loginService.signUp(login);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody HashMap<String,String> details)
    {
        System.out.println("login start");
        return loginService.loginUser(details);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateLoginDetails(@PathVariable UUID id, @RequestBody HashMap<String,String> login){
        return loginService.updateLoginDetails(id, login);
    }


}
