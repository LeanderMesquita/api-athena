package com.llm.athena.core.http.response;

import com.llm.athena.core.entity.User;
import com.llm.athena.core.entity.enums.JobRole;

import java.time.Instant;
import java.time.LocalDate;

public record UserResponseDto(
        String id,
        Instant createdAt,
        Instant updatedAt,
        String username,
        String email,
        String cpfCnpj,
        String jobPosition,
        JobRole role,
        String company,
        LocalDate birthdate
) {
    public UserResponseDto(User user) {
        this(
                user.getId(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getUsername(),
                user.getEmail(),
                user.getCpfCnpj(),
                user.getJobPosition(),
                user.getRole(),
                user.getCompany(),
                user.getBirthdate()
        );
    }
}
