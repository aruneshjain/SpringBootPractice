package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.GlobalExceptions.EmployeeNotFoundException;
import com.practice.PracticeApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public String updateUser(long id, Users users) {
        Users userDetails = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Updating", id));
        userRepository.save(users);
        return "Employee Updated Successfully for ID : " +id;
    }

    @Override
    public ResponseEntity<String> saveUser(Users users) {
        try {
            userRepository.save(users);
        }
        catch(RuntimeException exp){
            return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Users Created Successfully", HttpStatus.CREATED);

    }

    @Override
    public String deleteUser(long id) {
        Optional<Users> getUser = userRepository.findById(id);
        if(getUser.isPresent()) {
            userRepository.deleteById(id);
        }
        else
            return "Users Not Found : Provide Correct Users ID";

        return "Users with ID : " + id + " is Removed";
    }
}
