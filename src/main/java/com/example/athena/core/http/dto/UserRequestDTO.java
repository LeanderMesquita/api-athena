package com.example.athena.core.http.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String lastName
) {
}
