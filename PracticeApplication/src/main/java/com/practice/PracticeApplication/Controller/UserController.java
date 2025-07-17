package com.practice.PracticeApplication.Controller;

import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.GlobalExceptions.EmployeeNotFoundException;
import com.practice.PracticeApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getUserById(@PathVariable long id){
        Users user= userService.getUserById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Fetching",id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable long id, @RequestBody Users users){
        return userService.updateUser(id, users);
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody Users users){
        return userService.saveUser(users);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }
}
