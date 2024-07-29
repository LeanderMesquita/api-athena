package com.example.athena.core.http.service;

import com.example.athena.core.entity.User;
import com.example.athena.core.http.dto.UserRequestDTO;
import com.example.athena.core.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User>getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User>getUserById(String id){
        return userRepository.findById(id);
    }

    public User updateUser(String id, UserRequestDTO userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setName(userDetails.name());
        user.setLastName(userDetails.lastName());

        return userRepository.save(user);
    }

    public void deleteUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

}
