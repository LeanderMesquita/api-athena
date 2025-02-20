package com.llm.athena.core.http.resource;

import java.time.Instant;

public record UserResponseDto(
        String id,
        Instant createdAt,
        Instant updatedAt,
        String username,
        String email,
        
) {
}
