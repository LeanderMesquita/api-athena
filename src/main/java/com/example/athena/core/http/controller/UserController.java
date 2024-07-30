package com.example.athena.core.http.controller;

import com.example.athena.core.entity.User;
import com.example.athena.core.http.dto.UserRequestDTO;
import com.example.athena.core.http.service.UserService;
import com.example.athena.core.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/view")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getUserProfile(String id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<User> updateProfile(@PathVariable String id, @RequestBody @Valid UserRequestDTO request){
        User updatedUser = userService.updateUser(id, request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
