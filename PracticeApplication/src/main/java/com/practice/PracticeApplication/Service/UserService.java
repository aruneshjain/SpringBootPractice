package com.practice.PracticeApplication.Service;

import com.practice.PracticeApplication.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.UUID;


public interface UserService {
    Page<Users> getAllUsers(int page, int size, String sortBy);

    ResponseEntity<Users> getUserById(UUID id);

    ResponseEntity<String> updateUser(UUID id, Users users);

    ResponseEntity<String> saveUser(Users users);

    ResponseEntity<String> deleteUser(UUID id);

    ResponseEntity<String> patchUpdateEmailAndName(UUID id, HashMap<String, String> details);

    ResponseEntity<String> userProfileImage(UUID id,MultipartFile file);

    ResponseEntity<byte[]> getUserProfileImage(UUID id);
//
    ResponseEntity<String> saveUserImage(Users user, MultipartFile file);
}
