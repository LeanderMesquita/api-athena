package com.example.athena.core.http.dto;

import com.example.athena.core.entity.User;

public record UserResponseDTO(String id, String username, String email) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}
