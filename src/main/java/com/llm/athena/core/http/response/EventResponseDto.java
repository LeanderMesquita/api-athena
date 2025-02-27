package com.llm.athena.core.http.response;

import com.llm.athena.core.entity.Event;
import com.llm.athena.core.entity.User;

import java.time.Instant;
import java.time.LocalDate;

public record EventResponseDto(
        String id,
        Instant createdAt,
        Instant updatedAt,
        User author,
        String title,
        String rules,
        String description,
        LocalDate endDate
)
{
    public EventResponseDto (Event event) {
        this(
                event.getId(),
                event.getCreatedAt(),
                event.getUpdatedAt(),
                event.getAuthor(),
                event.getTitle(),
                event.getRules(),
                event.getDescription(),
                event.getEndDate()
        );
    }
}
