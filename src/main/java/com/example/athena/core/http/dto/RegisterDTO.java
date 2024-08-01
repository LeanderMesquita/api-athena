package com.example.athena.core.http.dto;

import com.example.athena.core.entity.enums.UserRole;

public record RegisterDTO(String name, String lastName, String password, UserRole role, String email) {
}
