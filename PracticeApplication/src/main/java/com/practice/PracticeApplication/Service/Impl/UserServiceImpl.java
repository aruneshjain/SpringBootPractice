package com.practice.PracticeApplication.Service.Impl;

import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.Exceptions.EmployeeNotFoundException;
import com.practice.PracticeApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UserServiceImpl implements com.practice.PracticeApplication.Service.UserService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Cacheable("AllUsers")
    @Override
    public Page<Users> getAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<Users> users = userRepository.findAllUsers(pageable);
            users.stream().forEach(user -> user.setImage(null));
            return users;
    }


    public ResponseEntity<Users> getUserById(UUID id) {
        Users getUser = userRepository.findUserById(id)
                .orElseThrow(()->
                        new EmployeeNotFoundException("Fetching", id));
        System.out.println(getUser.getLogin().getPassword().substring(0,7));
        return new ResponseEntity<>(getUser,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateUser(UUID id, Users users) {

        Users getUser = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Updating", id));
        try {
            Login login = users.getLogin();
            if (!login.getPassword().startsWith("$2a$12$")) {
                login.setPassword(encoder.encode(login.getPassword()));
                users.setLogin(login);
            }
            Users newUser = userRepository.saveAndFlush(users);
            System.out.println(newUser);
        } catch (Exception e) {
            return new ResponseEntity<>("Employee didn't updated : " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Employee Updated Successfully for ID : " +id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveUser(Users users) {
        try {

            Login login = users.getLogin();
            if(!login.getPassword().startsWith("$2a$12$")){
                login.setPassword(encoder.encode(login.getPassword()));
                users.setLogin(login);
            }
            userRepository.save(users);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("User Didn't save : " + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User Added Successfully",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> userProfileImage(UUID id,MultipartFile file) {
        try {
            Users users = userRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee", id));
            Login login = users.getLogin();
            if(!login.getPassword().startsWith("$2a$12$")){
                login.setPassword(encoder.encode(login.getPassword()));
                users.setLogin(login);
            }
            users.setImage(file.getBytes());
            userRepository.save(users);
        } catch (IOException e) {
            return new ResponseEntity<>("Image did not work please check : " + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Profile Image uploaded successfully",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<byte[]> getUserProfileImage(UUID id) {
        try {
            byte[] user = userRepository.findImageByUid(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(user);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<String> saveUserImage(Users user, MultipartFile file) {
        try {
            Login login = user.getLogin();
            if(!login.getPassword().startsWith("$2a$12$")){
                login.setPassword(encoder.encode(login.getPassword()));
                user.setLogin(login);
            }
            user.setImage(file.getBytes());
            userRepository.save(user);
        } catch (RuntimeException | IOException e) {
            return new ResponseEntity<>("User Didn't save : " + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User Added Successfully",HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<String> deleteUser(UUID id) {
        Users getUser = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Deleting", id));
            userRepository.deleteById(id);
        return new ResponseEntity<>("Users with ID : " + id + " is Removed",HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> patchUpdateEmailAndName(UUID id, HashMap<String, String> details) {
        Users userDetails = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Updating", id));
        if(details.containsKey("lastName"))
            userDetails.setLastName(details.get("lastName"));

        if(details.containsKey("firstName"))
            userDetails.setFirstName(details.get("firstName"));

        if(details.containsKey("email"))
            userDetails.setEmail(details.get("email"));

        userRepository.save(userDetails);
        return new ResponseEntity<>("User Email / Name Updated Successfully", HttpStatus.OK);
    }

}
