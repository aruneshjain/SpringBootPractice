package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Optional;

public interface UserServiceImpl {
    Page<Users> getAllUsers(int page, int size, String sortBy);

    ResponseEntity<Users> getUserById(long id);

    String updateUser(long id, Users users);

    ResponseEntity<String> saveUser(Users users);

    ResponseEntity<String> deleteUser(long id);

    ResponseEntity<String> patchUpdateEmailAndName(long id, HashMap<String, String> details);

    ResponseEntity<String> userProfileImage(long id,MultipartFile file);

    ResponseEntity<byte[]> getUserProfileImage(long id);

    ResponseEntity<String> saveUserImage(Users user, MultipartFile file);
}
