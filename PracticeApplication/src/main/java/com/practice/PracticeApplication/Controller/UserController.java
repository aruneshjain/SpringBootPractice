package com.practice.PracticeApplication.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.PracticeApplication.Entity.Users;
import com.practice.PracticeApplication.Service.Impl.CacheInspectionService;
import com.practice.PracticeApplication.Service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.UUID;


@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Create / Read / Update / Delete - Users")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    CacheInspectionService cacheInspectionService;

    @GetMapping
    @Operation(summary = "get All users details.")
    public Page<Users> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "50") int size,
                                   @RequestParam(defaultValue = "firstName") String sortBy){
        return userService.getAllUsers(page,size,sortBy);
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Find User for given ID.")
    public ResponseEntity<Users> getUserById(@PathVariable UUID id){
        ResponseEntity<Users> userDetail = userService.getUserById(id);
        return userDetail;
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update User for given ID")
    public ResponseEntity<String> updateUser(@PathVariable UUID id,
                             @RequestBody Users users){
        return userService.updateUser(id, users);
    }

    @Operation(summary = "Add New User without Profile Image")
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody Users users){
        return userService.saveUser(users);
    }

    @Operation(summary = "Add / Update Profile photo.")
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> userProfileImage(@PathVariable("id") UUID id,
                                                   @RequestPart("image") MultipartFile file) {
        return userService.userProfileImage(id, file);
    }

    @Operation(summary = "Get Profile Image with ID")
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable("id") UUID id) {
        return userService.getUserProfileImage(id);

    }

    @Operation(summary = "Save New User with Profile Image")
    @PostMapping(value = "/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUserImage(@RequestPart("user") String userJson,
                                             @RequestPart("image") MultipartFile file) {

        try {
            Users user = new ObjectMapper().readValue(userJson, Users.class);
            return userService.saveUserImage(user, file);
        } catch (Exception e) {
            return new ResponseEntity<>("user Object did not parse correctly : " + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete User for given ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
    @Operation(summary = "Cache Data Details")
    @GetMapping("/cacheData")
    public void getCacheDate() {
        cacheInspectionService.printCacheContents("AllUsers");
    }

    @Operation(summary = "Update User Name and Email")
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchUpdateEmailAndName(@PathVariable UUID id,
                                                          @RequestBody HashMap<String,String> details){
        return userService.patchUpdateEmailAndName(id, details);
    }

}
