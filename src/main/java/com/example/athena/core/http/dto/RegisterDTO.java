package com.example.athena.core.http.dto;

import com.example.athena.core.entity.enums.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
