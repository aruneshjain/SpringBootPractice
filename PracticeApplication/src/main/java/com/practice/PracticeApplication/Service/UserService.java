package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.Exceptions.EmployeeNotFoundException;
import com.practice.PracticeApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl{

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    Address address = new Address();

    @Cacheable("AllUsers")
    @Override
    public Page<Users> getAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return userRepository.findAllWithAddress(pageable);
    }

    public ResponseEntity<Users> getUserById(long id) {
        Users getUser = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Fetching", id));
        return ResponseEntity.ok().body(getUser);
    }

    @Override
    public String updateUser(long id, Users users) {
        Users userDetails = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Updating", id));
//         Updating new values to Users object
        userDetails.setFirstName(users.getFirstName() == null ? userDetails.getFirstName() : users.getFirstName());
        userDetails.setLastName(users.getLastName() == null ? userDetails.getLastName() : users.getLastName());
        userDetails.setEmail(users.getEmail() == null ? userDetails.getEmail() : users.getEmail());
        userDetails.setContacts(users.getContacts() == null ? userDetails.getContacts() : users.getContacts());
        userDetails.setAddress(users.getAddress() == null ? userDetails.getAddress() : userDetails.getAddress().updateAddress(users.getAddress()));
        userRepository.save(userDetails);
        return "Employee Updated Successfully for ID : " +id;
    }

    @Override
    public ResponseEntity<String> saveUser(Users users) {
        try {
            userRepository.save(users);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("User Didn't save : " + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User Added Successfully",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> userProfileImage(long id,MultipartFile file) {
        try {
            Users users = userRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee", id));
            users.setImage(file.getBytes());
            userRepository.save(users);
        } catch (IOException e) {
            return new ResponseEntity<>("Image did not work please check : " + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Profile Image uploaded successfully",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<byte[]> getUserProfileImage(long id) {
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
            user.setImage(file.getBytes());
            userRepository.save(user);
        } catch (RuntimeException | IOException e) {
            return new ResponseEntity<>("User Didn't save : " + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User Added Successfully",HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<String> deleteUser(long id) {
        Users getUser = userRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Deleting", id));
            userRepository.deleteById(id);
        return new ResponseEntity<>("Users with ID : " + id + " is Removed",HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> patchUpdateEmailAndName(long id, HashMap<String, String> details) {
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
